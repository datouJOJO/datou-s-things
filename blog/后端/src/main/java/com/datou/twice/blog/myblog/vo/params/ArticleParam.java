package com.datou.twice.blog.myblog.vo.params;

import com.datou.twice.blog.myblog.vo.CategoryVo;
import com.datou.twice.blog.myblog.vo.TagVo;
import lombok.Data;

import java.util.List;

@Data
public class ArticleParam {
    private Long id;
    private ArticleBodyParam body;
    private CategoryVo category;
    private String summary;
    private List<TagVo> tags;
    private String title;
}
