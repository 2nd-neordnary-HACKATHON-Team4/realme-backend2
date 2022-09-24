package com.example.demo.src.feed.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

public class CategoryDTO {

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class GetCategory{
        private Long categoryIdx;
        private String categoryName;

    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class UserProtectedList{
        private Long id;
        private String contents;
        private String imgUrl;
        private LocalDateTime createdDate;
        private CategoryProtected categoryProtected;
        private UserProtected userProtected;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class CategoryProtected{
        private Long categoryidx;
        private String categoryName;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class UserProtected{
        private Long id;
        private String nickname;
        private String profileImgUrl;
    }

}
