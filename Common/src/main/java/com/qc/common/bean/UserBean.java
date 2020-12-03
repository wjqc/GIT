package com.qc.common.bean;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 
 *
 * @author WJ
 * @datetime 2020-12-01 18:20:48
 */
@Data
@SuppressWarnings("serial")
public class UserBean implements Serializable {

	private Integer id;
	
	/**  */
	private String userName;
	
	/**  */
	private String phone;
	
	/**  */
	private String email;
	
	/**  */
	private String adress;
	
	/**  */
	private String identno;
	
	/** 用户级别，0:系统管理员，1：管理员,2：普通用户 */
	private String level;
	
}