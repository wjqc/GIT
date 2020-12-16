package com.qc.common.bean;

import java.io.Serializable;

import lombok.Data;

/**
 * 
 *
 * @author WJ
 * @datetime 2020-12-01 18:20:48
 */
@Data
@SuppressWarnings("serial")
public class UserLoginBean implements Serializable {

	private Integer userId;
	
	/**  */
	private String loginName;
	
	/**  */
	private String password;
	
}