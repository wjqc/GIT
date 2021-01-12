package com.qc.provider.user;

import com.qc.common.bean.UserLoginBean;
import com.qc.common.bean.UserRegisterBean;
import com.qc.common.exception.ErrorCode;

public interface UserService {
    boolean login(UserLoginBean bean);

    boolean register(UserRegisterBean bean) throws ErrorCode;

}
