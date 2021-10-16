package com.datou.twice.blog.myblog.service;

import com.datou.twice.blog.myblog.vo.Result;
import com.datou.twice.blog.myblog.vo.params.CommentParam;
import org.springframework.stereotype.Service;

public interface CommentsService {
    Result commentsByArticleId(Long articleId);

    Result comment(CommentParam commentParam);
}
