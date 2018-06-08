package com.zongcc.boot.repository;

import com.zongcc.boot.entity.JdbcUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserRepository {

    @Insert("INSERT INTO T_USER(username, age) VALUES(#{userName}, #{age})")
    int save(JdbcUser user);

    @Select("SELECT * FROM T_USER WHERE username = #{userName}")
    JdbcUser selectByName(Integer id);

    @Select("SELECT * FROM T_USER")
    List<JdbcUser> selectAll();
}
