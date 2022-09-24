package com.example.demo.src.user.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@AllArgsConstructor
public class SignRes {
    private String jwt;
    private long userId;
}
