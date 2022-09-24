package com.example.demo.src.feed.controller;

import com.example.demo.src.feed.service.FeedService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/home")
public class FeedController {
    private FeedService feedService;
    public FeedController(FeedService feedService){
        this.feedService = feedService;
    }
}
