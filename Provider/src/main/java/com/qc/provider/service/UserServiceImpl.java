package com.qc.provider.service;

import com.qc.common.bean.UserRegisterBean;
import com.qc.common.exception.ErrorCode;
import com.qc.provider.entity.UserEntity;
import com.qc.provider.mapper.UserMapper;
import com.qc.provider.user.UserService;
import com.qc.common.bean.UserLoginBean;
import com.qc.provider.entity.UserLoginEntity;
import com.qc.provider.mapper.UserLoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserLoginMapper loginMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean login(UserLoginBean bean) {
        UserLoginEntity entity = new UserLoginEntity(bean);
        entity = loginMapper.selectOne(entity);
        if (entity!=null && bean.getLoginName().equals(entity.getLoginName()) && bean.getPassword().equals(entity.getPassword())){
            return true;
        }
        return false;
    }

    public boolean register(UserRegisterBean bean) throws ErrorCode {
        UserEntity entity = new UserEntity();
        entity.setAddress(bean.getAddress());
        entity.setEmail(bean.getEmail());
        entity.setIdentNo(bean.getIdentNo());
        entity.setPhone(bean.getPhone());
        entity.setUserName(bean.getUserName());
        entity.setLevel(bean.getLevel());
        if (userMapper.insertSelective(entity)<1){
            throw ErrorCode.REGISTER_FAIL;
        }
        entity = userMapper.selectOne(entity);
        UserLoginEntity loginEntity = new UserLoginEntity();
        loginEntity.setLoginName(bean.getLoginName());
        loginEntity.setPassword(bean.getPassword());
        loginEntity.setUserId(entity.getId());
        if (loginMapper.insertSelective(loginEntity)<1)
            throw ErrorCode.REGISTER_FAIL;
        return true;
    }
}
