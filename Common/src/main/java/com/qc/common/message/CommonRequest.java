package com.qc.common.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import java.io.IOException;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommonRequest<T> extends ApiResquest {

    private static final long serialVersionUID = -5676615715869091581L;
    private T requestData;

    public CommonRequest(){

    }

    public CommonRequest(final String json) throws IOException {
        if (StringUtils.isNotBlank(json)){
            CommonRequest<T> commonRequest = objectMapper.readValue(json, new TypeReference<CommonRequest<T>>() {
            });
            BeanUtils.copyProperties(commonRequest,this);
            setRequestData(commonRequest.getReqData());
        }
    }

    private void setRequestData(final String json) throws IOException {
        if (StringUtils.isNotBlank(json)) {
                this.requestData = objectMapper.readValue(super.getReqData(), new TypeReference<T>() {
            });
        }
    }

    public T getRequestData(){
        return requestData;
    }

    public void setRequestData(T requestData){
        this.requestData = requestData;
    }
}
