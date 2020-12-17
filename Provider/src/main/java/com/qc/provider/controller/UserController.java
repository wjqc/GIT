package com.qc.provider.controller;

import com.qc.common.bean.UserLoginBean;
import com.qc.common.message.BaseResponse;
import com.qc.provider.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @ResponseBody
    public BaseResponse login(@RequestBody UserLoginBean request){
        String loginName = request.getLoginName();
        String password = request.getPassword();
        UserLoginBean bean = new UserLoginBean();
        bean.setLoginName(loginName);
        bean.setPassword(password);
        boolean login = userService.login(bean);
        if (login){
            return BaseResponse.Success();
        }
        return BaseResponse.Fail();
    }
}
