package com.qc.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.qc.common.exception.ErrorCode;
import com.qc.common.message.BaseResponse;
import com.qc.common.utils.IPUtils;
import com.qc.zuul.config.IPList;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class IpFilter extends ZuulFilter {

    @Autowired
    private IPList ipList;

    public IpFilter(){
        super();
    }

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        String ipAddress = IPUtils.getIpAddress(context.getRequest());
        //黑名单禁用
        if (StringUtils.isNotBlank(ipAddress) && ipList.getBlack().contains(ipAddress)){
            context.sendZuulResponse();
            BaseResponse error = BaseResponse.error(ErrorCode.NO_AUTH.getCode(), null);
            context.setResponseBody(error.toString());
            context.getResponse().setContentType("application/json; charset=utf-8");
            return null;
        }
        return null;
    }
}
