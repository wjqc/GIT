package com.qc.common.exception;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

public class ErrorCode implements Serializable {

	private static final long serialVersionUID = 2471999484765518060L;

	public static final ErrorCode SUCCESS = new ErrorCode("000000","交易成功");
	public static final ErrorCode SYS_UNKNOWN = new ErrorCode("100001", "系统未知错误");
	public static final ErrorCode SYS_NO_DATA_SUCCESS = new ErrorCode("100002", "操作失败，请重试");
	public static final ErrorCode SYS_PARAM_INVALID = new ErrorCode("100003", "参数校验失败");
	public static final ErrorCode USER_OR_PASSWD_ERROR = new ErrorCode("100004", "用户名或密码错误");

	
	private String code;
	private String text;

	public ErrorCode(String code) {
		this.code = code;
	}

	public ErrorCode(String code, String text) {

		this.code = code;
		this.text = text;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {

		this.code = code;
	}

	/**
	 * @return 根据用户语言返回国际化提示
	 */
	public String getText() {
		return text;
	}

	public void setText(String message) {

		this.text = message;
	}

	@Override
	public String toString() {

		return StringUtils.isBlank(getText()) ? String.valueOf(code)
				: String.format("%s, %s", this.code, this.getText());
	}

	public static class Device {
		public static final String STATUS_UNK = "UNK";
		public static final String STATUS_INA = "INA";
		public static final String STATUS_ONL = "ONL";
		public static final String STATUS_OFL = "OFL";
		public static final String STATUS_N_EXP = "N-EXP";

		public static final String STATUS_Y = "Y";// 可用
		public static final String STATUS_N = "N";// 不可用

		public static final String SEX_WOMAN = "M";// 女
		public static final String SEX_MAN = "F";// 男

		public static final String CUSTOMER_TYPE_MED = "MED";// 媒体方
		public static final String CUSTOMER_TYPE_AGE = "AGE";// 代理商
		public static final String CUSTOMER_TYPE_ADV = "ADV";// 广告主
	}
}
