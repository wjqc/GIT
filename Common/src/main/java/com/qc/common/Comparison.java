package com.qc.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class Comparison implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1846712669568527918L;
	//字段
    private String field;
    //字段旧值
    private Object before;
    //字段新值
    private Object after;
}
