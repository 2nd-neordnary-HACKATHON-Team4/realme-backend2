package com.example.demo.src.user.DTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Login {
    @ApiModelProperty(value = "이메일 주소", required = true, example = "abcd123@gmail.com")
    private String email;

    @ApiModelProperty(value = "닉네임", required = true, example = "소고기")
    private String nickName;

    @ApiModelProperty(value = "비밀번호", required = true, example = "abcd1234!")
    private String password;
}
