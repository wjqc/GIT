package com.qc.cicada.user;

import com.qc.api.user.UserService;
import com.qc.common.annotation.RequestParameter;
import com.qc.common.bean.UserLoginBean;
import com.qc.common.exception.ErrorCode;
import com.qc.common.message.ApiResponse;
import com.qc.common.message.SimpleRequest;
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
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
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