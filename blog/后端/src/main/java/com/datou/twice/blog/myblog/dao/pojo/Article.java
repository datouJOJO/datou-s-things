package com.datou.twice.blog.myblog.dao.pojo;

import lombok.Data;

@Data
public class Article {

    public static final int Article_TOP = 1;
    public static final int Article_Common = 0;

    private Long id;
    private Integer commentCounts;
    private Long createDate;
    private String summary;
    private String title;
    private Integer viewCounts;
    private Integer weight = Article_Common;
    private Long authorId;
    private Long bodyId;
    private Long categoryId;
}
