package com.example.demo.src.category.Entity;

import com.example.demo.config.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "category")
@NoArgsConstructor
@DynamicInsert
public class CategoryEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryidx;

    @Column(nullable = false, length = 45)
    private String categoryName;

    @Builder
    public CategoryEntity(String categoryName){
        this.categoryName = categoryName;
    }
}
