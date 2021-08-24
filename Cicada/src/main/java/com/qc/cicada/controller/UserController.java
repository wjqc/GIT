package com.qc.cicada.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.qc.cicada.service.user.UserService;
import com.qc.common.bean.UserLoginBean;
import com.qc.common.bean.UserRegisterBean;
import com.qc.common.message.BaseResponse;
import com.qc.common.utils.LogbackUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author WJ
 * @datetime 2020-12-01 18:20:48
 */
@Controller
@RequestMapping("/api")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

    @RequestMapping("/login")
    @HystrixCommand(fallbackMethod = "fallback")
    public BaseResponse login(@RequestBody UserLoginBean request) {
        LogbackUtils.debug("login","登录-->{}",request.toString(),log);
        return userService.login(request);
    }

    @RequestMapping("/register")
    public BaseResponse register(@RequestBody UserRegisterBean bean) {
        return userService.register(bean);
    }

    @RequestMapping("/logout")
    public BaseResponse logout() {
        return BaseResponse.Success();
    }

    String fallback() {
        return "服务繁忙,请稍后重试";
    }

}