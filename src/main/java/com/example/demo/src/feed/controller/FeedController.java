package com.example.demo.src.feed.controller;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.feed.DTO.FeedDTO;
import com.example.demo.src.feed.service.FeedService;
import com.example.demo.src.user.entity.UserEntity;
import com.example.demo.utils.JwtService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/home")
@CrossOrigin(origins="*", allowedHeaders = "*")
public class FeedController {
    private final FeedService feedService;
    private final JwtService jwtService;


    @ResponseBody
    @ApiOperation(value = "피드 좋아요 API")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "요청에 성공하였습니다."),
            @ApiResponse(code = 2003, message = "권한이 없는 유저의 접근입니다."),
            @ApiResponse(code = 2004, message = "잘못된 feedidx 정보 입니다.")
    })
    @PostMapping("/feed/{feedIdx}/like")
    public BaseResponse<UserEntity> userlike(@PathVariable("feedIdx") Long feedIdx){
        try {
            return new BaseResponse<>(this.feedService.userlike(this.jwtService.getUserIdx(),feedIdx));
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

    @ResponseBody
    @ApiOperation(value = "피드 작성 API")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "요청에 성공하였습니다."),
            @ApiResponse(code = 2003, message = "권한이 없는 유저의 접근입니다."),
            @ApiResponse(code = 2300, message = "존재하지 않는 카테고리입니다.")
    })
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
