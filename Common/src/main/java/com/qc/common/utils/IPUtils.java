package com.qc.common.utils;

import javax.servlet.http.HttpServletRequest;

public class IPUtils {

    public static String getIpAddress(HttpServletRequest request){
        String requestAddr = request.getHeader("x-forwarded-for");
        if (requestAddr == null || requestAddr.length() == 0 || "unknown".equalsIgnoreCase(requestAddr)) {
            requestAddr = request.getHeader("Proxy-Client-IP");
        }
        if (requestAddr == null || requestAddr.length() == 0 || "unknown".equalsIgnoreCase(requestAddr)) {
            requestAddr = request.getHeader("WL-Proxy-Client-IP");
        }
        if (requestAddr == null || requestAddr.length() == 0 || "unknown".equalsIgnoreCase(requestAddr)) {
            requestAddr = request.getRemoteAddr();
        }
        return requestAddr;
    }

}
