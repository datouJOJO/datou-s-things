package com.datou.twice.blog.myblog.service;

import com.datou.twice.blog.myblog.dao.pojo.SysUser;
import com.datou.twice.blog.myblog.vo.Result;
import com.datou.twice.blog.myblog.vo.params.LoginParam;

public interface LoginService {
    Result login(LoginParam loginParam);

    Result logout(String token);

    Result register(LoginParam loginParam);

    SysUser checkToken(String token);
}
