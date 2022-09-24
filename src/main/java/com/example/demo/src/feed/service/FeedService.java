package com.example.demo.src.feed.service;

import com.example.demo.config.BaseException;
import com.example.demo.src.category.Repository.CategoryRepository;
import com.example.demo.src.feed.entity.FeedEntity;
import com.example.demo.src.feed.entity.LikeEntity;
import com.example.demo.src.feed.repository.FeedRepository;
import com.example.demo.src.feed.repository.LikeRepository;
import com.example.demo.src.user.entity.UserEntity;
import com.example.demo.src.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.demo.config.BaseResponseStatus.PASSWORD_ENCRYPTION_ERROR;

@Service
@RequiredArgsConstructor
public class FeedService {
    private final FeedRepository feedRepository;
    private final CategoryRepository categoryRepository;
    private final LikeRepository likeRepository;
    private final UserRepository userRepository;

    public UserEntity userlike(Long userIdx, Long feedIdx) throws BaseException {
        Optional<UserEntity> user = this.userRepository.findById(userIdx);
        Optional<FeedEntity> feed = this.feedRepository.findById(feedIdx);
        if(user.isEmpty() || feed.isEmpty()){
            throw new BaseException(PASSWORD_ENCRYPTION_ERROR);
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
}