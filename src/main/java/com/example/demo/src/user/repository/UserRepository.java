package com.example.demo.src.user.repository;

import com.example.demo.src.feed.DTO.FeedDTO;
import com.example.demo.src.user.DTO.UserDto;
import com.example.demo.src.user.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findById(Long id);
    Boolean existsByNickname(String nickName);
    Boolean existsByEmail(String email);

    UserEntity findByEmail(String email);

}