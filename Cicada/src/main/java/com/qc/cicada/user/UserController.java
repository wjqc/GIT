package com.qc.cicada.user;

import com.qc.common.bean.UserLoginBean;
import com.qc.common.exception.ErrorCode;
import com.qc.common.message.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

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
    private RestTemplate restTemplate;

    @Value("${server.url}")
    private String url;

    @RequestMapping("/hello")
    public String hello(){
        return "Hello Wwold!";
    }

    @RequestMapping("/login")
    @ResponseBody
    public BaseResponse login(@RequestBody UserLoginBean request){
        log.info("登录");
        ResponseEntity<BaseResponse> login = restTemplate.postForEntity(url+"/login",request,BaseResponse.class);
        if ("000000".equals(login.getBody().getCode())){
            return BaseResponse.Success();
        }
        return BaseResponse.Fail();
    }

}