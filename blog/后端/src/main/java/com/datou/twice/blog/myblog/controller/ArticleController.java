package com.datou.twice.blog.myblog.controller;

import com.datou.twice.blog.myblog.common.aop.LogAnnotation;
import com.datou.twice.blog.myblog.common.cache.Cache;
import com.datou.twice.blog.myblog.service.ArticleService;
import com.datou.twice.blog.myblog.vo.ArticleVo;
import com.datou.twice.blog.myblog.vo.Result;
import com.datou.twice.blog.myblog.vo.params.ArticleParam;
import com.datou.twice.blog.myblog.vo.params.PageParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping
    @LogAnnotation(module = "文章", operation = "获取文章列表")
    public Result listArticle(@RequestBody PageParams pageParams){
//        System.out.println(pageParams.toString());
//        return Result.success(articleService.listArticlesPage(pageParams));
        return articleService.listArticle(pageParams);
    }

    @PostMapping("hot")
    @Cache(expire = 5 * 60 * 1000, name = "hot_article")
    public Result hotArticle(){
        int limit = 5;
        return articleService.hotArticle(limit);
    }

    @PostMapping("new")
    public Result newArticle(){
        int limit = 5;
        return articleService.newArticle(limit);
    }

    @PostMapping("listArchives")
    public Result listArchives(){
        return articleService.listArchives();
    }

    @PostMapping("view/{id}")
    public Result findArticleById(@PathVariable("id") Long id) {
        ArticleVo articleVo = articleService.findArticleById(id);
        return Result.success(articleVo);
    }

    @PostMapping("publish")
    public Result publish(@RequestBody ArticleParam articleParam){
        return articleService.publish(articleParam);
    }


}
