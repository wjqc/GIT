package com.qc.common.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import java.io.IOException;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommonResponse<T> extends ApiResponse {
    private static final long serialVersionUID = 5529801678084990138L;

    private T responseData;

    public CommonResponse(){
    }

    public CommonResponse(final String json) throws IOException {
        if (StringUtils.isNotBlank(json)) {
            CommonResponse<T> commonResponse = objectMapper.readValue(json, new TypeReference<CommonResponse<T>>() {
            });
            BeanUtils.copyProperties(commonResponse, this);
        }
    }
}
