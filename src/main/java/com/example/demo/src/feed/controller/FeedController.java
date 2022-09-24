package com.example.demo.src.feed.controller;

import com.example.demo.config.BaseResponse;
import com.example.demo.src.feed.service.FeedService;
import com.example.demo.src.user.DTO.Login;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/home")
public class FeedController {
    private FeedService feedService;
    public FeedController(FeedService feedService){
        this.feedService = feedService;
    }

    @ResponseBody
    @PostMapping("/feed/{feedId}/like")
    public BaseResponse<String> userlike(){
        return null;
    }
}
