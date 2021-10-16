package com.datou.twice.blog.myblog.service;

import com.datou.twice.blog.myblog.vo.CategoryVo;
import com.datou.twice.blog.myblog.vo.Result;

public interface CategoryService {

    CategoryVo findCategoryById(Long categoryId);

    Result findAll();

    Result findAllDetail();

    Result categoriesDetailById(Long id);
}
