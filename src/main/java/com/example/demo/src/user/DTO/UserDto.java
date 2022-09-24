package com.example.demo.src.user.DTO;


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
}
