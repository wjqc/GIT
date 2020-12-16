package com.qc.provider.entity;

import javax.persistence.*;

import com.qc.common.base.BaseEntity;
import com.qc.common.bean.UserLoginBean;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 *
 * @author WJ
 * @datetime 2020-12-01 18:20:48
 */
@Data
@Table(name = "user_login")
@EqualsAndHashCode(callSuper=false)
public class UserLoginEntity extends BaseEntity {

	public UserLoginEntity() {}
	
	public UserLoginEntity(UserLoginBean bean) {
		
		super(bean);
	}
	
	@Override
	public <T> T toBean(T bean) {
		
		UserLoginBean result = (UserLoginBean) super.toBean(bean);
		
		return bean;
	}

	@Id
	private Integer userId;
	
	/**  */
	@Column(name = "login_name")
	private String loginName;
	
	/**  */
	@Column(name = "password")
	private String password;
	
}
