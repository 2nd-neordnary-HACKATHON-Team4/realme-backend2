package com.example.demo.src.user.service;

import com.example.demo.config.BaseException;
import com.example.demo.src.user.entity.UserEntity;
import com.example.demo.src.user.DTO.Login;
import com.example.demo.src.user.repository.UserRepository;
import com.example.demo.utils.JwtService;
import com.example.demo.utils.SHA256;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;
import static com.example.demo.config.BaseResponseStatus.PASSWORD_ENCRYPTION_ERROR;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtService jwtService;


    public String signIn(Login login) throws BaseException {

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
                    .nickname("닉네임")
                    .build();

            UserEntity savedUser = this.userRepository.save(user);

            String jwt = jwtService.createJwt(savedUser.getId());

            return jwt;

        }catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }

    }
}
