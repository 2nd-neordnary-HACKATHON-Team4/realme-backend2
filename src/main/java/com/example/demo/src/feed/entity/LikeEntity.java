package com.example.demo.src.feed.entity;

import com.example.demo.config.BaseEntity;
import com.example.demo.src.category.Entity.CategoryEntity;
import com.example.demo.src.feed.entity.FeedEntity;
import com.example.demo.src.user.entity.UserEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "love")
@NoArgsConstructor
public class LikeEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="userIdx")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name="feedIdx")
    private FeedEntity feed;

    @Builder
    public LikeEntity(UserEntity user, FeedEntity feed){
        this.user = user;
        this.feed = feed;
    }

}
