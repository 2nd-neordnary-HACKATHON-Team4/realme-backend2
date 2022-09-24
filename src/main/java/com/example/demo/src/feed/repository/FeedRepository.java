package com.example.demo.src.feed.repository;


import com.example.demo.src.feed.DTO.FeedDTO;
import com.example.demo.src.feed.entity.FeedEntity;
import com.example.demo.src.user.DTO.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface FeedRepository extends JpaRepository<FeedEntity, Long> {

}
