package com.example.demo.src.feed.repository;


import com.example.demo.src.feed.DTO.FeedDTO;
import com.example.demo.src.feed.entity.CategoryEntity;
import com.example.demo.src.feed.entity.FeedEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedRepository extends JpaRepository<FeedEntity, Long> {

    List<FeedEntity> findAllByOrderByCreatedDateDesc();
    List<FeedEntity> findAllByCategoryOrderByCreatedDateDesc(CategoryEntity categoryEntity);
}
