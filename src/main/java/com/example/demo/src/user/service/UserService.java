package com.example.demo.src.user.service;

import com.example.demo.config.BaseException;
import com.example.demo.src.user.DTO.UserDto;
import com.example.demo.src.user.entity.UserEntity;
import com.example.demo.src.user.repository.UserRepository;
import com.example.demo.utils.JwtService;
import com.example.demo.utils.SHA256;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


import javax.mail.internet.MimeMessage;
import java.util.Random;

import static com.example.demo.config.BaseResponseStatus.*;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final JavaMailSender sender;

    public String createUser(UserDto.SignUp signUp) throws BaseException {
        //이메일 중복 체크
        if (userRepository.existsByEmail(signUp.getEmail())) {
            throw new BaseException(DUPLICATED_EMAIL);
        }


        //닉네임 중복 체크
        if (userRepository.existsByNickname(signUp.getNickName())) {
            throw new BaseException(DUPLICATED_NICKNAME);
        }

        //비밀 번호 암호화
        try {
            String pwd = new SHA256().encrypt(signUp.getPassword());
            signUp.setPassword(pwd);
        } catch (Exception exception) {
            throw new BaseException(PASSWORD_ENCRYPTION_ERROR);
        }

        try {
            UserEntity user = UserEntity.builder()
                    .email(signUp.getEmail())
                    .password(signUp.getPassword())
                    .nickname(signUp.getNickName())
                    .build();

            UserEntity savedUser = this.userRepository.save(user);

            String jwt = jwtService.createJwt(savedUser.getId());

            return jwt;

        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }

    }

    public UserDto.Page getUsersPage(Long userId) throws BaseException {
        if (userId == null) {
            throw new BaseException(USERS_EMPTY_USER_ID);
        }
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new BaseException(USERS_INVALID_USER_ID));
        return UserDto.Page.builder()
                .nickname(userEntity.getNickname())
                .introduce(userEntity.getIntroduce())
                .profileImgUrl(userEntity.getProfileImgUrl())
                .build();
    }

    public UserDto.Page getCurrentUserPage() throws BaseException {
        long id = jwtService.getUserIdx();
        return getUsersPage(id);
    }

    public void checkNickName(String nickName) throws BaseException {
        if (this.userRepository.existsByNickname(nickName)) {
            throw new BaseException(DUPLICATED_NICKNAME);
        }
    }

    public String logIn(UserDto.LogIn login) throws BaseException {

        UserEntity user = userRepository.findByEmail(login.getEmail());

        // 암호화된 비밀번호
        String encryptedPassword;

        try {
            encryptedPassword = new SHA256().encrypt(login.getPassword());
        } catch (Exception exception) {
            throw new BaseException(PASSWORD_ENCRYPTION_ERROR);
        }

        // 비밀 번호 체크
        if (user.getPassword().equals(encryptedPassword)) {
            long userId = user.getId();
            String jwt = jwtService.createJwt(userId);
            return jwt;
        } else {
            throw new BaseException(FAILED_TO_LOGIN);
        }

    }

    public String generateCertificationNumberAndSend(String email) throws BaseException {
        // 이메일 중복 체크
        if (userRepository.existsByEmail(email)) {
            throw new BaseException(DUPLICATED_EMAIL);
        }

        Random random = new Random();
        String certificationNumber = String.valueOf(random.nextInt(888888) + 111111);
        sendCertificationNumberToEmail(certificationNumber, email);
        return certificationNumber;
    }

    private void sendCertificationNumberToEmail(String certificationNumber, String email) throws BaseException {
        String subject = "Real Me 회원가입 인증번호";
        String text = "Real Me 회원가입을 위한 인증번호는 <h2>" + certificationNumber + "</h2>입니다. <br />";

        try {
            MimeMessage message = sender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
            messageHelper.setTo(email);
            messageHelper.setSubject(subject);
            messageHelper.setText(text, true);
            sender.send(message);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new BaseException(USERS_EMAIL_SEND_FAIL);
        }
    }
}
