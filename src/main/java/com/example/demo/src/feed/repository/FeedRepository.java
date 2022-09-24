package com.example.demo.src.feed.repository;


import com.example.demo.src.feed.entity.FeedEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedRepository extends JpaRepository<FeedEntity, Long> {
}
