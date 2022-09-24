package com.example.demo.src.feed.controller;

import com.example.demo.src.feed.service.FeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/categories")
public class CategoryController {
    private final FeedService feedService;

}
