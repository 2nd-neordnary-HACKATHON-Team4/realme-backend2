package com.example.demo.src.feed.repository;

import com.example.demo.src.feed.entity.FeedEntity;
import com.example.demo.src.feed.entity.LikeEntity;
import com.example.demo.src.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<LikeEntity, Long> {
    LikeEntity findByUserAndFeed(Optional<UserEntity> user, Optional<FeedEntity> feed);

    int countByUserAndFeed(UserEntity user, FeedEntity feed);
}
