package com.example.demo.src.feed.repository;

import com.example.demo.src.feed.entity.LikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<LikeEntity, Long> {
}
