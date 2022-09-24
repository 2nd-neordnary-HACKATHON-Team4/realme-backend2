package com.example.demo.src.feed.controller;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.feed.DTO.FeedDTO;
import com.example.demo.src.feed.service.FeedService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
@RequestMapping(value = "/home")
public class FeedController {
    private final FeedService feedService;
    public FeedController(FeedService feedService){
        this.feedService = feedService;
    }

    @ResponseBody
    @PostMapping("/feeds")
    public BaseResponse<String> postFeed(@RequestBody FeedDTO.PostFeed feed){
        try{
            feedService.postFeed(feed);
            return new BaseResponse<>("피드 작성을 완료했습니다.");
        }catch (BaseException e){
            return new BaseResponse<>(e.getStatus());
        }
    }
}
