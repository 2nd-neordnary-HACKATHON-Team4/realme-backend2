package com.example.demo.src.entity;

import com.example.demo.config.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.management.relation.Role;
import javax.persistence.*;

@Entity
@Getter
@Table(name = "user")
@NoArgsConstructor
@DynamicInsert
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Column
    private String phoneNumber;

    @Column
    private String profileImgUrl;


    @Builder
    public User(String email, String nickname, String password, String phoneNumber, String profileImgUrl){
        this.email = email;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
        this.profileImgUrl = profileImgUrl;
        this.password = password;
    }


}