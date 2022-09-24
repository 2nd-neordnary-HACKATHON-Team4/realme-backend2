package com.example.demo.src.feed.controller;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.feed.DTO.CategoryDTO;
import com.example.demo.src.feed.service.FeedService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/categories")
public class CategoryController {
    private final FeedService feedService;

    @ResponseBody
    @ApiOperation(value = "카테고리 조회 API")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "요청에 성공하였습니다.")
    })
    @GetMapping("/list")
    public BaseResponse<List<CategoryDTO.GetCategory>> getCategories(){
        try{
            List<CategoryDTO.GetCategory> getCategories = this.feedService.getCategoryAll();
            return new BaseResponse<>(getCategories);
        }catch (BaseException e){
            return new BaseResponse<>(e.getStatus());
        }
    }
}
