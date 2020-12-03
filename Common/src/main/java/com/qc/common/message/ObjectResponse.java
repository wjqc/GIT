package com.qc.common.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import java.io.IOException;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ObjectResponse extends CommonResponse {

    private static final long serialVersionUID = 6942068275464014096L;

    public ObjectResponse(){}

    public ObjectResponse(final Object object){
        this.setResponseData(object);
    }

    public ObjectResponse(final String json) throws IOException {
        if (StringUtils.isNotBlank(json)) {
            ObjectResponse objectResponse = objectMapper.readValue(json, new TypeReference<ObjectResponse>() {
            });
            BeanUtils.copyProperties(objectResponse, this);
        }
    }
}
