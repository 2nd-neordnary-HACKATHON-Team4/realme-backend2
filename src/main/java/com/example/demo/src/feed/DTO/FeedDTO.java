package com.example.demo.src.feed.DTO;

import com.example.demo.src.category.DTO.CategoryDTO;
import com.example.demo.src.category.Entity.CategoryEntity;
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
        private String contents;
        private String imgUrl;
        private Long categoryIdx;

    }
}