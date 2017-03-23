package com.zongcc.boot.redis;

import com.zongcc.boot.entity.JdbcUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
//@ConfigurationProperties(prefix = "spring.redis")
public class RedisConfig {

    @Value("${spring.redis.host}")
    private String hostName;
    @Value("${spring.redis.password}")
    private String password;
    @Value("${spring.redis.port}")
    private Integer port;
    @Value("${spring.redis.database}")
    private Integer database;
    @Value("${spring.redis.timeout}")
    private Integer timeout;
    @Value("${spring.redis.pool.max-active}")
    private Integer maxActive;
    @Value("${spring.redis.pool.max-wait}")
    private Integer maxWait;
    @Value("${spring.redis.pool.max-idle}")
    private Integer maxIdle;
    @Value("${spring.redis.pool.min-idle}")
    private Integer minIdle;


    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setHostName(hostName);
        jedisConnectionFactory.setPort(port);
        jedisConnectionFactory.setPassword(password);
        jedisConnectionFactory.setDatabase(database);
        jedisConnectionFactory.setTimeout(timeout);
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(maxActive);
        jedisPoolConfig.setMaxWaitMillis(maxWait);
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMinIdle(minIdle);
        jedisConnectionFactory.setPoolConfig(jedisPoolConfig);
        return jedisConnectionFactory;
    }


    @Bean
    public RedisTemplate<String, JdbcUser> jdbcUserRedisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, JdbcUser> template = new RedisTemplate<String, JdbcUser>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new RedisObjectSerializer());
        return template;
    }
}
