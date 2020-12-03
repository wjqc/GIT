package com.qc.provider.mapper;

import com.qc.common.entity.UserLoginEntity;
import tk.mybatis.mapper.common.Mapper;

/**
 * 
 * 
 * @author WJ
 * @datetime 2020-12-01 18:20:48
 */
public interface UserLoginMapper extends Mapper<UserLoginEntity> {

    UserLoginEntity selectbyName(String loginName);
}
