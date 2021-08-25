package com.qc.cicada.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.qc.cicada.service.user.UserService;
import com.qc.common.bean.UserLoginBean;
import com.qc.common.bean.UserRegisterBean;
import com.qc.common.message.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author WJ
 * @datetime 2020-12-01 18:20:48
 */
@Slf4j
@Controller
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

    @RequestMapping("/login")
    @HystrixCommand(fallbackMethod = "fallback")
    public BaseResponse login(@RequestBody UserLoginBean request) {
        log.debug("登录-->{}",request.toString());
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