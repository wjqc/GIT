package com.qc.provider.entity;

import javax.persistence.*;


import com.qc.common.base.BaseEntity;
import com.qc.common.bean.UserBean;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 *
 * @author WJ
 * @datetime 2020-12-01 18:20:48
 */
@Data
@Table(name = "user")
@EqualsAndHashCode(callSuper=false)
public class UserEntity extends BaseEntity {

	public UserEntity() {}
	
	public UserEntity(UserBean bean) {
		
		super(bean);
	}
	
	@Override
	public <T> T toBean(T bean) {
		
		UserBean result = (UserBean) super.toBean(bean);
		
		return bean;
	}

	@Id
	private Integer id;
	
	/**  */
	@Column(name = "user_name")
	private String userName;
	
	/**  */
	@Column(name = "phone")
	private String phone;
	
	/**  */
	@Column(name = "email")
	private String email;
	
	/**  */
	@Column(name = "address")
	private String address;
	
	/**  */
	@Column(name = "identNo")
	private String identNo;
	
	/** 用户级别，0:系统管理员，1：管理员,2：普通用户 */
	@Column(name = "level")
	private String level;
	
}
