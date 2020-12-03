package com.qc.common.message;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.BeanUtils;

import java.io.IOException;
import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SimpleRequest extends CommonRequest<JSONObject>{
    private static final long serialVersionUID = -8270357115973048906L;

    public SimpleRequest(){}

    public SimpleRequest(final String json) throws IOException {
        if (StringUtils.isNotBlank(json)){
            SimpleRequest simpleRequest = objectMapper.readValue(json, new TypeReference<SimpleRequest>() {
            });
            BeanUtils.copyProperties(simpleRequest,this);

            String data = simpleRequest.getRequestData().toJSONString();

            if (StringUtils.isNotBlank(data)){
                setRequestData(JSON.parseObject(data));
            }
        }
    }

    public String getString(final String name){
        if (this.getRequestData() != null ){
            return StringUtils.defaultString(this.getRequestData().getString(name));
        }
        return StringUtils.EMPTY;
    }

    public JSONObject get(final String name){
        if (this.getRequestData() != null){
            return this.getRequestData().getJSONObject(name);
        }
        return null;
    }

    public JSONArray getArray(final String name){
        if (this.getRequestData() != null){
            return this.getRequestData().getJSONArray(name);
        }
        return null;
    }

    public Integer getInteger(final String name) {

        String str = getString(name);

        if (StringUtils.isBlank(str)) {
            return null;
        }
        return Integer.parseInt(str);
    }

    public double getDouble(final String name) {

        return NumberUtils.toDouble(getString(name));
    }

    public BigDecimal getBigDecimal(final String name) {

        final String stringValue = getString(name);

        if (StringUtils.isNotBlank(stringValue) && !stringValue.equals("null")) {
            return new BigDecimal(stringValue);
        } else {
            return BigDecimal.ZERO;
        }
    }
}
