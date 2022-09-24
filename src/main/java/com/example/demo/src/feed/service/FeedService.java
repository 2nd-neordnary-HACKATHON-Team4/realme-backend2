package com.example.demo.src.feed.service;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.feed.DTO.CategoryDTO;
import com.example.demo.src.feed.controller.CategoryController;
import com.example.demo.src.feed.entity.CategoryEntity;
import com.example.demo.src.feed.repository.CategoryRepository;
import com.example.demo.src.feed.DTO.FeedDTO;
import com.example.demo.src.feed.entity.FeedEntity;
import com.example.demo.src.feed.repository.FeedRepository;
import com.example.demo.src.user.entity.UserEntity;
import com.example.demo.src.user.repository.UserRepository;
import com.example.demo.utils.JwtService;
import com.example.demo.src.feed.entity.LikeEntity;
import com.example.demo.src.feed.repository.LikeRepository;
import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.demo.config.BaseResponseStatus.*;

@Service
@RequiredArgsConstructor
public class FeedService {
    private final FeedRepository feedRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final LikeRepository likeRepository;

    public void postFeed(FeedDTO.PostFeed postFeed) throws BaseException {
        Optional<UserEntity> writer = userRepository.findById(this.jwtService.getUserIdx());
        if (writer.isEmpty()) {
            throw new BaseException(BaseResponseStatus.INVALID_USER_JWT);
        }
        if(postFeed.getContents() == null){
            throw new BaseException(BaseResponseStatus.EMPTY_CONTENTS);
        }
        if(postFeed.getCategoryIdx() == null){
            throw new BaseException(EMPTY_CATEGORY);
        }
        System.out.println(writer.get());
        Optional<CategoryEntity> category = this.categoryRepository.findById(postFeed.getCategoryIdx());
        if (category.isEmpty()) {
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


    public UserEntity userlike(Long userIdx, Long feedIdx) throws BaseException {
        Optional<UserEntity> user = this.userRepository.findById(userIdx);
        Optional<FeedEntity> feed = this.feedRepository.findById(feedIdx);
        if(user.isEmpty()){
            throw new BaseException(INVALID_USER_JWT);
        }
        if(feed.isEmpty()){
            throw new BaseException(INVALID_FEED_NUM);
        }
        LikeEntity like = this.likeRepository.findByUserAndFeed(user, feed);
        if(like != null){
            this.likeRepository.delete(like);
        }else{
            LikeEntity likeEntity = LikeEntity.builder()
                    .user(user.get())
                    .feed(feed.get())
                    .build();
            this.likeRepository.save(likeEntity);
        }
        //exception
        return this.userRepository.findById(userIdx).get();
    }
    public int userLikeFeed(UserEntity user, FeedEntity feed){
        return this.likeRepository.countByUserAndFeed(user,feed);
    }

    public List<CategoryDTO.GetCategory> getCategoryAll() throws BaseException{
        List<CategoryEntity> categoryEntity;
        categoryEntity = categoryRepository.findAll();

        List<CategoryDTO.GetCategory> categoryList = new ArrayList<>();

        for(CategoryEntity i :  categoryEntity){
            CategoryDTO.GetCategory category = new CategoryDTO.GetCategory();
            category.setCategoryIdx(i.getCategoryidx());
            category.setCategoryName(i.getCategoryName());
            categoryList.add(category);
        }
        return categoryList;
    }

}