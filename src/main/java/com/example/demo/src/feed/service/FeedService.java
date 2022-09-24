package com.example.demo.src.feed.service;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.category.Entity.CategoryEntity;
import com.example.demo.src.category.Repository.CategoryRepository;
import com.example.demo.src.feed.DTO.FeedDTO;
import com.example.demo.src.feed.entity.FeedEntity;
import com.example.demo.src.feed.repository.FeedRepository;
import com.example.demo.src.user.entity.UserEntity;
import com.example.demo.src.user.repository.UserRepository;
import com.example.demo.utils.JwtService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FeedService {
    private final FeedRepository feedRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public FeedService(FeedRepository feedRepository, CategoryRepository categoryRepository, UserRepository userRepository,
                       JwtService jwtService){
        this.feedRepository = feedRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    public void postFeed(FeedDTO.PostFeed postFeed) throws BaseException{
        Optional<UserEntity> writer = userRepository.findById(this.jwtService.getUserIdx());
        if(writer.isEmpty()){
            throw new BaseException(BaseResponseStatus.INVALID_USER_JWT);
        }
        System.out.println(writer.get());
        Optional<CategoryEntity> category = this.categoryRepository.findById(postFeed.getCategoryIdx());
        if(category.isEmpty()){
            throw new BaseException(BaseResponseStatus.INVALID_CATEGORY_TYPE);
        }

        FeedEntity feedEntity = FeedEntity.builder()
                .user(writer.get())
                .contents(postFeed.getContents())
                .imgUrl(postFeed.getImgUrl())
                .category(category.get())
                .build();

        feedRepository.save(feedEntity);


    }
}