package com.example.demo.src.feed.service;

import com.example.demo.src.category.Repository.CategoryRepository;
import com.example.demo.src.feed.repository.FeedRepository;
import com.example.demo.src.feed.repository.LikeRepository;
import org.springframework.stereotype.Service;

@Service
public class FeedService {
    private FeedRepository feedRepository;
    private CategoryRepository categoryRepository;
    private LikeRepository likeRepository;
    public FeedService(FeedRepository feedRepository, CategoryRepository categoryRepository, LikeRepository likeRepository){
        this.feedRepository = feedRepository;
        this.categoryRepository = categoryRepository;
        this.likeRepository = likeRepository;
    }
}