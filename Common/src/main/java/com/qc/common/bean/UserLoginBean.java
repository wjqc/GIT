package com.qc.common.bean;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 *
 * @author WJ
 * @datetime 2020-12-01 18:20:48
 */
@Data
@ApiModel(value = "登录对象")
public class UserLoginBean implements Serializable {

	private static final long serialVersionUID = -6225861708112122841L;
	@ApiModelProperty(value = "用户ID")
	private Integer userId;
	
	@ApiModelProperty(value = "登录名")
	private String loginName;
	
	@ApiModelProperty(value = "登录密码")
	private String password;
	
}