package com.qc.cicada.service.user;

import com.qc.common.bean.UserLoginBean;
import com.qc.common.message.BaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "Provider")
public interface UserLoginService {

    @RequestMapping("/login")
    BaseResponse login(@RequestBody UserLoginBean request);

}
