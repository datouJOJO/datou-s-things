package com.datou.twice.blog.myblog.service;

import com.datou.twice.blog.myblog.dao.pojo.SysUser;
import com.datou.twice.blog.myblog.vo.Result;
import com.datou.twice.blog.myblog.vo.UserVo;

public interface SysUserService {

    SysUser findUserById(Long sysUserId);

    SysUser findUser(String account, String pwd);

    Result getUserInfoByToken(String token);

    SysUser findUserByAccount(String account);

    void save(SysUser sysUser);

    UserVo findUserVoById(Long authorId);
}
