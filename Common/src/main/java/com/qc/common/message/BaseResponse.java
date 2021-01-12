package com.qc.common.message;

import com.qc.common.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<T> implements Serializable {

    private static final long serialVersionUID = -5150002623890371270L;
    private String code;
    private String msg;
    private T data;

    public static BaseResponse Success(){
        BaseResponse<Object> response = new BaseResponse<>();
        response.setMsg(ErrorCode.SUCCESS.getText());
        response.setCode(ErrorCode.SUCCESS.getCode());
        return response;
    }

    public static BaseResponse Fail(){
        BaseResponse<Object> response = new BaseResponse<>();
        response.setMsg(ErrorCode.SYS_UNKNOWN.getText());
        response.setCode(ErrorCode.SYS_UNKNOWN.getCode());
        return response;
    }

    public static BaseResponse error(ErrorCode code, Object data){
        BaseResponse<Object> response = new BaseResponse<>();
        response.setMsg(code.getText());
        response.setCode(code.getCode());
        response.setData(data);
        return response;
    }
}
