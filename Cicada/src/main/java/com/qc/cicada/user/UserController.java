package com.qc.cicada.user;

import com.qc.api.user.UserService;
import com.qc.common.annotation.RequestParameter;
import com.qc.common.bean.UserLoginBean;
import com.qc.common.exception.ErrorCode;
import com.qc.common.message.ApiResponse;
import com.qc.common.message.SimpleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
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
    private UserService userService;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/hello")
    public String hello(){
        return "Hello Wwold!";
        //第一种调用方式
        //String forObject = new RestTemplate().getForObject("http://localhost:8081/Hello/World?s=" + s, String.class);

        //第二种调用方式
        //根据服务名 获取服务列表 根据算法选取某个服务 并访问某个服务的网络位置。
        //ServiceInstance serviceInstance = loadBalancerClient.choose("EUREKA-SERVICE");
        //String forObject = new RestTemplate().getForObject("http://"+serviceInstance.getHost()+":"+serviceInstance.getPort()+"/Hello/World?s="+s,String.class);

        //第三种调用方式 需要restTemplate注入的方式
//        String forObject = restTemplate.getForObject("http://EUREKA-SERVICE/Hello/World?s=" + s, String.class);
//        return forObject;
    }

    @RequestMapping("/login")
    @ResponseBody
    public ApiResponse login(@RequestParameter SimpleRequest request){
        log.info("登录");
        String loginName = request.getString("loginName");
        String password = request.getString("password");
        UserLoginBean bean = new UserLoginBean();
        bean.setLoginName(loginName);
        bean.setPassword(password);
        boolean login = userService.login(bean);
        if (login){
            return ApiResponse.OK;
        }
        return ApiResponse.error(ErrorCode.USER_OR_PASSWD_ERROR);
    }

}