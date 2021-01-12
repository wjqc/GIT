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
@ApiModel
public class UserBean implements Serializable {

	private static final long serialVersionUID = -4973315024746785900L;
	@ApiModelProperty(value = "用户ID")
	private Integer id;

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