package com.zongcc.boot;

import com.zongcc.boot.entity.JdbcUser;
import com.zongcc.boot.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate<String, JdbcUser> jdbcUserRedisTemplate;
    @Autowired
    private UserService userService;

    @Test
    public void test() throws Exception {
        // 保存字符串
        System.out.println("=================1111111111111========================");
        stringRedisTemplate.opsForValue().set("aaa", "111");
        Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));
    }

    @Test
    public void testJdbcUser() throws Exception {
        // 保存对象
        JdbcUser user = new JdbcUser();
        user.setAge(10);
        user.setUserName("超人");
        jdbcUserRedisTemplate.opsForValue().set(user.getUserName(), user);
        Assert.assertEquals(10, jdbcUserRedisTemplate.opsForValue().get("超人").getAge().longValue());
    }

    @Test
    public void testSelectAll() {
        List<JdbcUser> userList = userService.selectAll();
        System.out.println("=====testSelectAll==========>" + userList.size());
        Assert.assertEquals(6, userList.size());
    }
}
