package com.example.demo.src.user.controller;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.user.service.UserService;
import com.example.demo.src.user.DTO.Login;
import com.example.demo.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Pattern;

import static com.example.demo.config.BaseResponseStatus.*;
import static com.example.demo.config.BaseResponseStatus.POST_USERS_INVALID_EMAIL;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/users")
public class UserController {
    private final UserService userService;
    private final JwtService jwtService;

    //회원 가입
    @ResponseBody
    @PostMapping("/signup")
    public BaseResponse<String> createUser(@RequestBody Login login){

        String emailPattern = "^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-z]+$";
        if(!Pattern.matches(emailPattern, login.getEmail())) {
            return new BaseResponse<>(POST_USERS_INVALID_EMAIL);
        }

        if(login.getPassword() == null) {
            return new BaseResponse<>(POST_USERS_EMPTY_PASSWORD);
        }

        if(login.getNickName() == null)  {
            return new BaseResponse<>(POST_USERS_EMPTY_NICKNAME);
        }

        if(login.getNickName().length() > 8 ||login.getNickName().length() < 2) {
            return new BaseResponse<>(POST_USERS_OVER_LENGTH_NICKNAME);
        }

        String nickNamePattern = "^[가-힣]*$";
        if(!Pattern.matches(nickNamePattern, login.getNickName())) {
            return new BaseResponse<>(POST_USERS_INVALID_NICKNAME);
        }


        String passwordPattern = "^(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{6,20}";
        if(!Pattern.matches(passwordPattern, login.getPassword())) {
            return new BaseResponse<>(POST_USERS_INVALID_PASSWORD);
        }

        try {

            String jwt = userService.signIn(login);

            return new BaseResponse<>(jwt);
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

    // 닉네임 중복 체크
    @ResponseBody
    @GetMapping("/nickname/{nickName}/duplication")
    public BaseResponse<String> checkNickName(@PathVariable("nickName") String nickName) {

        if(nickName.length() > 8 ||nickName.length() < 2) {
            return new BaseResponse<>(POST_USERS_OVER_LENGTH_NICKNAME);
        }

        String nickNamePattern = "^[가-힣]*$";
        if(!Pattern.matches(nickNamePattern, nickName)) {
            return new BaseResponse<>(POST_USERS_INVALID_NICKNAME);
        }

        try {
            userService.checkNickName(nickName);

            String result = "닉네임 중복 체크 성공!";
            return new BaseResponse<>(result);
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }


}
