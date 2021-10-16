package com.datou.twice.blog.myblog.controller;

import com.datou.twice.blog.myblog.vo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class test {

    @GetMapping
    public Result test(){

        return Result.success(null);
    }
}
