package com.qc.common.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel
public class UserRegisterBean implements Serializable {

    private static final long serialVersionUID = -6738120887999565904L;
    @ApiModelProperty(value = "登录名")
    private String loginName;

    @ApiModelProperty(value = "登录密码")
    private String password;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value ="邮箱")
    private String email;

    @ApiModelProperty(value = "联系地址")
    private String address;

    @ApiModelProperty(value = "身份证号")
    private String identNo;

    /** 用户级别，0:系统管理员，1：管理员,2：普通用户 */
    @ApiModelProperty(value = "用户级别")
    private String level;
}
