package com.qc.cicada.user;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.qc.cicada.service.user.UserService;
import com.qc.common.bean.UserLoginBean;
import com.qc.common.bean.UserRegisterBean;
import com.qc.common.message.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import lombok.extern.slf4j.Slf4j;

/**
 * 
 *
 * @author WJ
 * @datetime 2020-12-01 18:20:48
 */
@Slf4j
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/hello")
    public String hello(){
        return "Hello Wwold!";
    }

    @RequestMapping("/login")
    @HystrixCommand(fallbackMethod = "fallback")
    @ResponseBody
    public BaseResponse login(@RequestBody UserLoginBean request){
        return userService.login(request);
    }

    @RequestMapping("/register")
    @ResponseBody
    public BaseResponse register(@RequestBody UserRegisterBean bean){
        return userService.register(bean);
    }


    String fallback(){
        return "服务繁忙,请稍后重试";
    }

}