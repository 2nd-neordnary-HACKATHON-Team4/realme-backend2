package com.example.demo.src.user.entity;

import com.example.demo.config.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "user")
@NoArgsConstructor
@DynamicInsert
public class UserEntity extends BaseEntity {
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
    private String introduce;

    @Column
    private String phoneNumber;

    @Column
    private String profileImgUrl;


    @Builder
    public UserEntity(String email, String nickname, String introduce, String password, String phoneNumber, String profileImgUrl){
        this.email = email;
        this.nickname = nickname;
        this.introduce = introduce;
        this.phoneNumber = phoneNumber;
        this.profileImgUrl = profileImgUrl;
        this.password = password;
    }


}