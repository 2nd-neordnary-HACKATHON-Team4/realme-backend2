package com.example.demo.src.user.repository;

import com.example.demo.src.user.entity.UserEntity;
import com.example.demo.src.user.repository.mapping.UserPageMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findById(Long id);
}