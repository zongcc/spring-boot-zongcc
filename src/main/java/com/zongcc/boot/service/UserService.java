package com.zongcc.boot.service;

import com.zongcc.boot.entity.JdbcUser;

import java.util.List;

/**
 * @author chunchengzong
 * @date 2018-06-08 19:34
 **/
public interface UserService {
    /**
     * 获取所有user
     * @return
     */
    List<JdbcUser> selectAll();
}