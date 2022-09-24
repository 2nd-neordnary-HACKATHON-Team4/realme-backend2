package com.example.demo.src.user.controller;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.user.service.UserService;
import com.example.demo.src.user.DTO.Login;
import com.example.demo.utils.JwtService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
    @ApiOperation(value = "회원 가입 API")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "요청에 성공하였습니다."),
            @ApiResponse(code = 2020, message = "잘못된 이메일 형식입니다."),
            @ApiResponse(code = 2030, message = "비밀 번호를 입력해주세요."),
            @ApiResponse(code = 2031, message = "비밀 번호는 특수문자 포함 6자 이상 20자리 이하입니다."),
            @ApiResponse(code = 2080, message = "닉네임을 입력해주세요."),
            @ApiResponse(code = 2081, message = "닉네임은 최대 8자까지 입력해주세요."),
            @ApiResponse(code = 2082, message = "닉네임은 한글만 사용 가능합니다."),
            @ApiResponse(code = 4000, message = "데이터베이스 연결에 실패하였습니다."),
            @ApiResponse(code = 4011, message = "비밀번호 암호화에 실패하였습니다.")
    })
    @PostMapping("/signup")
    public BaseResponse<String> createUser(@ApiParam(value = "회원가입 Request Body") @RequestBody Login login){

        String emailPattern = "^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-z]+$";
        if(!Pattern.matches(emailPattern, login.getEmail())) {
            return new BaseResponse<>(POST_USERS_INVALID_EMAIL);
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

        if(login.getPassword() == null) {
            return new BaseResponse<>(POST_USERS_EMPTY_PASSWORD);
        }

        String passwordPattern = "^(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{8,20}";
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
    @ApiOperation(value = "닉네임 중복 체크 API")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "요청에 성공하였습니다."),
            @ApiResponse(code = 2081, message = "닉네임은 최대 8자까지 입력해주세요."),
            @ApiResponse(code = 2082, message = "닉네임은 한글만 사용 가능합니다."),
            @ApiResponse(code = 3030, message = "중복된 사용자 이름입니다.")
    })
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
