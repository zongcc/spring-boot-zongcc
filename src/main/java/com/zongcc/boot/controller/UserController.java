package com.zongcc.boot.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.github.pagehelper.PageHelper;
import com.zongcc.boot.entity.JdbcUser;
import com.zongcc.boot.entity.User;
import com.zongcc.boot.entity.Worker;
import com.zongcc.boot.repository.UserRepository;
import com.zongcc.boot.utils.JacksonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.CountDownLatch;

/**
 * Created by chunchengzong on 2017-01-06.
 */
//@EnableAutoConfiguration
@RestController
@RequestMapping("/user")
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    //如果name没有设置，默认值为world
    @Value("${name:world}")
    private String name;
    @Value("${my.secret}")
    private String secret;
    @Value("${my.number}")
    private String number;
    @Value("${my.bignumber}")
    private String bignumber;
    @Value("${my.number.less.than.ten}")
    private String less;
    @Value("${my.number.in.range}")
    private String range;

    @Resource
    private UserRepository userRepository;

    // 创建线程安全的Map
    static Map<Integer, User> users = Collections.synchronizedMap(new HashMap<Integer, User>());

    static {
        User user1 = new User(1, "zongcc");
        User user2 = new User(2, "fhy");
        users.put(1, user1);
        users.put(2, user2);
    }


    @GetMapping("/")
    public List<User> getUserList() {
        List<User> r = new ArrayList<User>(users.values());
        User u = new User(1, "呵呵哒");
        r.add(u);
        return r;
    }

    @PostMapping("/")
    public String postUser(@ModelAttribute User user) {
        users.put(user.getId(), user);
        return "success";
    }

    @GetMapping(value = "/{id}")
    public User getUser(@PathVariable Integer id) {
        return users.get(id);
    }

    @PutMapping(value = "/{id}")
    public String putUser(@PathVariable Integer id, @ModelAttribute User user) {
        // 处理"/users/{id}"的PUT请求，用来更新User信息
        users.put(id, user);
        return "success";
    }

    @DeleteMapping(value = "/{id}")
    public String deleteUser(@PathVariable Integer id) {
        // 处理"/users/{id}"的DELETE请求，用来删除User
        users.remove(id);
        return "success";
    }


    @RequestMapping("/value")
    public String value() {
        StringBuffer sb = new StringBuffer();
        sb.append(name).append(" ").append(secret).append(" ").append(number).append(" ")
                .append(bignumber).append(" ").append(less).append(" ").append(range);
        return sb.toString();
    }

    @RequestMapping("/countDownLatch")
    public String countDownLatch() {
        CountDownLatch ct = new CountDownLatch(2);
        new Thread(new Worker(ct, "zcc")).start();
        new Thread(new Worker(ct, "fhy")).start();
        try {
            ct.await();
            System.out.println("All jobs have been finished!");
            return "success";
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
            return "error";
        }
    }


    @RequestMapping("/jdbc/{age}/{name}")
    @ResponseBody
    public String jdbc(@PathVariable Integer age, @PathVariable String name) {
        JdbcUser jdbcUser = new JdbcUser();
        jdbcUser.setAge(age);
        jdbcUser.setUserName(name);
        userRepository.save(jdbcUser);
        return jdbcUser.toString();
    }

    @RequestMapping("/thymeleaf")
    public ModelAndView thymeleaf() {
        ModelAndView mvc = new ModelAndView();
        mvc.setViewName("/index");
        List<JdbcUser> userList = userRepository.selectAll();
        mvc.addObject("userList", userList);
        logger.info("userController=========>thymeleaf====>userList:{}", new Object[]{JacksonUtil.toJson(userList)});
        return mvc;
    }

    @RequestMapping("/page/{page}/{size}")
    public ModelAndView page(@PathVariable Integer page,@PathVariable Integer size) {
        ModelAndView mvc = new ModelAndView();
        mvc.setViewName("/index");
        PageHelper.startPage(page,size);
        List<JdbcUser> userList = userRepository.selectAll();
        mvc.addObject("userList", userList);
        logger.info("userController=========>thymeleaf====>page:{}", new Object[]{JacksonUtil.toJson(userList)});
        return mvc;
    }


}