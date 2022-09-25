package com.example.demo.src.feed.DTO;

import io.swagger.annotations.ApiModelProperty;
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
        @ApiModelProperty(value = "카테고리 PK", required = true, example = "1")
        private Long categoryIdx;
        @ApiModelProperty(value = "카테고리 이름", required = true, example = "대학생")
        private String categoryName;

    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class UserProtectedList{
        @ApiModelProperty(value = "피드 PK", required = true, example = "1")
        private Long id;
        @ApiModelProperty(value = "좋아요 수", required = true, example = "2")
        private int likeCount;
        @ApiModelProperty(value = "피드 제목", required = true, example = "개발자, 프론트앤드")
        private String title;
        @ApiModelProperty(value = "피드 내용", required = true, example = "#공부중")
        private String contents;
        @ApiModelProperty(value = "피드 이미지 Url", required = true, example = "abc.jpg")
        private String imgUrl;
        @ApiModelProperty(value = "생성 시간", required = true, example = "2022-09-25")
        private LocalDateTime createdDate;
        @ApiModelProperty(value = "필요없는 속성 값 제외", required = true)
        private CategoryProtected categoryProtected;
        @ApiModelProperty(value = "사용자 정보 보호", required = true)
        private UserProtected userProtected;
        @ApiModelProperty(value = "좋아요 여부", required = true, example = "true")
        private boolean isHeartedPressed;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class CategoryProtected{
        @ApiModelProperty(value = "카테고리 PK", required = true, example = "1")
        private Long categoryidx;
        @ApiModelProperty(value = "카테고리 이름", required = true, example = "대학생")
        private String categoryName;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class UserProtected{
        @ApiModelProperty(value = "사용자 PK", required = true, example = "1")
        private Long id;
        @ApiModelProperty(value = "사용자 닉네임", required = true, example = "userNIck")
        private String nickname;
        @ApiModelProperty(value = "사용자 프로필 이미지", required = true, example = "adsf.jpg")
        private String profileImgUrl;
    }

}
