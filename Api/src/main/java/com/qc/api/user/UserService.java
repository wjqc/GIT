package com.qc.api.user;

import com.qc.common.bean.UserLoginBean;

public interface UserService {
    boolean login(UserLoginBean bean);

}
