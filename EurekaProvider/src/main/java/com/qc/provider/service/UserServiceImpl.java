package com.qc.provider.service;

import com.qc.api.user.UserService;
import com.qc.common.bean.UserLoginBean;
import com.qc.common.entity.UserLoginEntity;
import com.qc.provider.mapper.UserLoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserLoginMapper loginMapper;

    @Override
    public boolean login(UserLoginBean bean) {
        UserLoginEntity entity = loginMapper.selectbyName(bean.getLoginName());
        if (entity!=null && bean.getLoginName().equals(entity.getLoginName()) && bean.getPassword().equals(entity.getPassword())){
            return true;
        }
        return false;
    }
}
