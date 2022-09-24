package com.example.demo.src.feed.entity;

import com.example.demo.config.BaseEntity;
import com.example.demo.src.user.entity.UserEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "feed")
@NoArgsConstructor
public class FeedEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String contents;

    @Column
    private String imgUrl;

    @ManyToOne
    @JoinColumn(name="categoryIdx")
    private CategoryEntity category;

    @ManyToOne
    @JoinColumn(name="userIdx")
    private UserEntity user;

    @Builder
    public FeedEntity(String contents, String imgUrl, CategoryEntity category,
                      UserEntity user){
        this.contents = contents;
        this.imgUrl = imgUrl;
        this.category = category;
        this.user = user;
    }
}
