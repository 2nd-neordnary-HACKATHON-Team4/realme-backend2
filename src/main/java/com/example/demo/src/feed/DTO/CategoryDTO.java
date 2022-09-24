package com.example.demo.src.feed.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class CategoryDTO {

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class GetCategory{
        private Long categoryIdx;
        private String categoryName;

    }
}
