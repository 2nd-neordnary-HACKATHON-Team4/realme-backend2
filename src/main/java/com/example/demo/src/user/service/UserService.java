package com.example.demo.src.user.service;

import com.example.demo.config.BaseException;
import com.example.demo.src.user.entity.UserEntity;
import com.example.demo.src.user.DTO.Login;
import com.example.demo.src.user.repository.UserRepository;
import com.example.demo.utils.JwtService;
import com.example.demo.utils.SHA256;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.*;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtService jwtService;


    public String signIn(Login login) throws BaseException {
        //닉네임 중복 체크는 따로!

        //비밀 번호 암호화
        try {
            String pwd = new SHA256().encrypt(login.getPassword());
            login.setPassword(pwd);
        } catch (Exception exception) {
            throw new BaseException(PASSWORD_ENCRYPTION_ERROR);
        }

        try {
            UserEntity user = UserEntity.builder()
                    .email(login.getEmail())
                    .password(login.getPassword())
                    .nickname(login.getNickName())
                    .build();

            UserEntity savedUser = this.userRepository.save(user);

            String jwt = jwtService.createJwt(savedUser.getId());

            return jwt;

        }catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }

    }

    public void checkNickName(String nickName) throws BaseException {
        if(this.userRepository.existsByNickname(nickName)) {
            throw new BaseException(DUPLICATED_NICKNAME);
        }
    }
}
