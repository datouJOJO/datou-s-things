package com.datou.twice.blog.myblog.service;

import com.datou.twice.blog.myblog.vo.Result;
import com.datou.twice.blog.myblog.vo.TagVo;

import java.util.List;

public interface TagService {
    List<TagVo> findTagsByArticleId(Long articleId);

    List<TagVo> hot(int limit);

    Result findAll();

    Result findAllDetail();

    Result findDetailById(Long id);
}
