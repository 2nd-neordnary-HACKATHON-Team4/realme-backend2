package com.example.demo.src.feed.controller;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.feed.service.FeedService;
import com.example.demo.src.user.DTO.Login;
import com.example.demo.src.user.entity.UserEntity;
import com.example.demo.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/home")
public class FeedController {
    private final FeedService feedService;
    private final JwtService jwtService;

    @ResponseBody
    @PostMapping("/feed/{feedIdx}/like")
    public BaseResponse<UserEntity> userlike(@PathVariable("feedIdx") Long feedIdx){
        try {
            return new BaseResponse<>(this.feedService.userlike(this.jwtService.getUserIdx(),feedIdx));
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }
}
