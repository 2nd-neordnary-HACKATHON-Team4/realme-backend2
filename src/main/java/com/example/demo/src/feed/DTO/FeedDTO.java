package com.example.demo.src.feed.DTO;

import lombok.*;

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
}