package com.zongcc.boot.service.impl;

import com.zongcc.boot.entity.JdbcUser;
import com.zongcc.boot.repository.UserRepository;
import com.zongcc.boot.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author chunchengzong
 * @date 2018-06-08 19:35
 **/
@Service("userService")
public class UserServiceImpl implements UserService {
   private static Logger logger =  LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    UserRepository userRepository;

    @Override
    public List<JdbcUser> selectAll() {
        logger.info("userServiceImpl======selectAll===========");
        return userRepository.selectAll();
    }
}