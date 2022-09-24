package com.example.demo.src.feed.service;

import com.example.demo.src.category.Repository.CategoryRepository;
import com.example.demo.src.feed.repository.FeedRepository;
import org.springframework.stereotype.Service;

@Service
public class FeedService {
    private FeedRepository feedRepository;
    private CategoryRepository categoryRepository;
    public FeedService(FeedRepository feedRepository, CategoryRepository categoryRepository){
        this.feedRepository = feedRepository;
        this.categoryRepository = categoryRepository;
    }
}