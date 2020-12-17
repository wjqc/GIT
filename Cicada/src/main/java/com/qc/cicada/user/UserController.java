package com.qc.cicada.user;

import com.qc.cicada.service.user.UserLoginService;
import com.qc.common.bean.UserLoginBean;
import com.qc.common.exception.ErrorCode;
import com.qc.common.message.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
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
    private UserLoginService userLoginService;

    @RequestMapping("/hello")
    public String hello(){
        return "Hello Wwold!";
    }

    @RequestMapping("/login")
    @ResponseBody
    public BaseResponse login(@RequestBody UserLoginBean request){
        BaseResponse login = userLoginService.login(request);
        if ("000000".equals(login.getCode())){
            return BaseResponse.Success();
        }
        return BaseResponse.Fail();
    }

}