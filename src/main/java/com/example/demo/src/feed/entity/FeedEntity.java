package com.example.demo.src.feed.entity;

import com.example.demo.config.BaseEntity;
import com.example.demo.src.category.Entity.CategoryEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

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


    @Builder
    public FeedEntity(String contents, String imgUrl, CategoryEntity category){
        this.contents = contents;
        this.imgUrl = imgUrl;
        this.category = category;
    }
}
