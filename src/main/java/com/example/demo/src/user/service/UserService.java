package com.example.demo.src.user.service;

import com.example.demo.config.BaseException;
import com.example.demo.src.user.DTO.UserDto;
import com.example.demo.src.user.entity.UserEntity;
import com.example.demo.src.user.repository.UserRepository;
import com.example.demo.utils.JwtService;
import com.example.demo.utils.SHA256;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtService jwtService;


    public String createUser(UserDto.SignUp signUp) throws BaseException {
        //이메일 중복 체크
        if(userRepository.existsByEmail(signUp.getEmail())) {
            throw new BaseException(DUPLICATED_EMAIL);
        }


        //닉네임 중복 체크
        if(userRepository.existsByNickname(signUp.getNickName())) {
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
        if(this.userRepository.existsByNickname(nickName)) {
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
        if(user.getPassword().equals(encryptedPassword)) {
            long userId = user.getId();
            String jwt = jwtService.createJwt(userId);
            return jwt;
        }
        else {
            throw new BaseException(FAILED_TO_LOGIN);
        }

    }

}
