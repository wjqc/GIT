package com.qc.provider.controller;

import com.qc.common.bean.UserLoginBean;
import com.qc.common.bean.UserRegisterBean;
import com.qc.common.exception.ErrorCode;
import com.qc.common.message.BaseResponse;
import com.qc.provider.user.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @ResponseBody
    @ApiOperation(value = "UserLoginBean")
    public BaseResponse login(@RequestBody UserLoginBean request){
        log.debug("Provider-->{}",request.toString());
        String loginName = request.getLoginName();
        String password = request.getPassword();
        UserLoginBean bean = new UserLoginBean();
        bean.setLoginName(loginName);
        bean.setPassword(password);
        boolean login = userService.login(bean);
        if (login){
            log.debug("<---login is success!!!--->");
            return BaseResponse.Success();
        }
        return BaseResponse.error(ErrorCode.USER_OR_PASSWD_ERROR,null);
    }

    @PostMapping("/register")
    @ResponseBody
    @ApiOperation(value = "UserRegisterBean")
    public BaseResponse register(@RequestBody UserRegisterBean bean){

        try {
            boolean register = userService.register(bean);
            if(register){
               return BaseResponse.Success();
            }
            return BaseResponse.error(ErrorCode.REGISTER_FAIL,null);
        } catch (ErrorCode errorCode) {
            errorCode.printStackTrace();
            return BaseResponse.error(ErrorCode.REGISTER_FAIL,null);
        }
    }
}
