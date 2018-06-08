package com.zongcc.boot;

import com.zongcc.boot.entity.JdbcUser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class ApplicationTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate<String, JdbcUser> jdbcUserRedisTemplate;
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
}
