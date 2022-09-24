package com.example.demo.src.user;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.user.model.Login;
import com.example.demo.src.user.model.SignRes;
import com.example.demo.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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


}
