package com.qc.common.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import java.io.IOException;
import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ApiResquest implements Serializable {

    private static final long serialVersionUID = -5618641194221613702L;

    private String sign;
    private String reqData;
    private String IP;
    private String MAC;
    private String appID;

    protected transient ObjectMapper objectMapper = new ObjectMapper();

    public ApiResquest(){}

    public ApiResquest(final String json) throws IOException {
        ApiResquest apiResquest = objectMapper.readValue(json, ApiResquest.class);
        BeanUtils.copyProperties(apiResquest,this);
    }

    public String toString(){
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return StringUtils.EMPTY;
        }
    }

}
