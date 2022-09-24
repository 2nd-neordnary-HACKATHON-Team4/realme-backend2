package com.example.demo.src.user.DTO;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    @AllArgsConstructor
    @Getter
    @Builder
    public static class Page {
        private String nickname;
        private String introduce;
        private String profileImgUrl;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class SignUp {
        @ApiModelProperty(value = "이메일 주소", required = true, example = "abcd123@gmail.com")
        private String email;

        @ApiModelProperty(value = "닉네임", required = true, example = "소고기")
        private String nickName;

        @ApiModelProperty(value = "비밀번호", required = true, example = "abcd1234!")
        private String password;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class LogIn {
        @ApiModelProperty(value = "이메일 주소", required = true, example = "abcd123@gmail.com")
        private String email;

        @ApiModelProperty(value = "비밀번호", required = true, example = "abcd1234!")
        private String password;
    }



}
