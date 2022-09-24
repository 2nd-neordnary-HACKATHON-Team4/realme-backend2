package com.example.demo.src.user.controller;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.user.DTO.UserDto;
import com.example.demo.src.user.service.UserService;
import com.example.demo.src.user.DTO.Login;
import com.example.demo.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Pattern;

import static com.example.demo.config.BaseResponseStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/users")
public class UserController {
    private final UserService userService;
    private final JwtService jwtService;

    @ResponseBody
    @PostMapping("/sign-in")
    public BaseResponse<String> signIn(@RequestBody Login login){

        if(login.getPassword() == null) {
            return new BaseResponse<>(POST_USERS_EMPTY_PASSWORD);
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

    @ResponseBody
    @GetMapping("/{userId}")
    public BaseResponse<UserDto.Page> getUsersPage(@PathVariable("userId") Long userId) {
        try {
            return new BaseResponse<>(userService.getUsersPage(userId));
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

    @ResponseBody
    @GetMapping("/")
    public BaseResponse<UserDto.Page> getMyPage() {
        try {
            return new BaseResponse<>(userService.getCurrentUserPage());
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

}
