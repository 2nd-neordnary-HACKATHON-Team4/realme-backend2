package com.example.demo.src.feed.DTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.stereotype.Service;

@Getter
@Setter
public class FeedDTO {
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class PostFeed{
        private String title;
        private String contents;
        private String imgUrl;
        private Long categoryIdx;

    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class calendarFeed{
        @ApiModelProperty(value = "피드 Id", required = true, example = "1")
        private Long feedId;

        @ApiModelProperty(value = "피드 작성일", required = true, example = "2022-09")
        private String creatAt;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class isHeartedPressed{
        @ApiModelProperty(value = "좋아요 여부", required = true, example = "1")
        private boolean isHeartedPressed;
    }
}