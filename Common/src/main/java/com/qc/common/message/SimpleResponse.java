package com.qc.common.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SimpleResponse extends CommonResponse<Map<String,Object>> {

    private static final long serialVersionUID = 3025226350467853711L;

    public SimpleResponse(){
        this.setResponseData(new HashMap<String,Object>());
    }

    public SimpleResponse(final String josn) throws IOException {
        if (StringUtils.isNotBlank(josn)) {
            SimpleResponse simpleResponse = objectMapper.readValue(josn, new TypeReference<SimpleResponse>() {
            });
            BeanUtils.copyProperties(simpleResponse, this);
        }
    }

    public void addObject(final String name,final String value){
        this.getResponseData().put(name,value);
    }
}
