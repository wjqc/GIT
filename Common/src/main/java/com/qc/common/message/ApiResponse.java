package com.qc.common.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.qc.common.exception.ErrorCode;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import javax.validation.ConstraintViolation;
import java.io.IOException;
import java.io.Serializable;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ApiResponse implements Serializable {

    private static final long serialVersionUID = 4981122064308407708L;

    private String Code = "0000";
    private String Message = "OK" ;

    public static ApiResponse OK = new ApiResponse();

    protected transient ObjectMapper objectMapper = new ObjectMapper();

    public ApiResponse(){}

    public ApiResponse(final String json) throws IOException {
        if (StringUtils.isNotBlank(json)){
            ApiResponse apiResponse = objectMapper.readValue(json, ApiResponse.class);
            BeanUtils.copyProperties(apiResponse,this);
        }
    }

    public static ApiResponse error(String code, String Message) {

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(code);
        apiResponse.setMessage(Message);

        return apiResponse;
    }

    public static ApiResponse error(ErrorCode code) {
        return error(code.getCode(), code.getText());
    }

    public String toString(){
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return StringUtils.EMPTY;
        }
    }

}
