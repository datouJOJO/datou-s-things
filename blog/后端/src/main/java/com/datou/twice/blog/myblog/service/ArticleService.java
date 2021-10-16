package com.datou.twice.blog.myblog.service;

import com.datou.twice.blog.myblog.vo.ArticleVo;
import com.datou.twice.blog.myblog.vo.Result;
import com.datou.twice.blog.myblog.vo.params.ArticleParam;
import com.datou.twice.blog.myblog.vo.params.PageParams;

import java.util.List;

public interface ArticleService {
    List<ArticleVo> listArticlesPage(PageParams pageParams);

    Result hotArticle(int limit);

    Result newArticle(int limit);

    Result listArchives();

    ArticleVo findArticleById(Long id);

    Result publish(ArticleParam articleParam);

    Result listArticle(PageParams pageParams);
}
