package com.qc.common.Handle;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wj
 * @date 2021/8/25
 * @apiNote
 */
@Slf4j
public class LogInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        log.debug("<---LogInterceptor is start--->");

        String servletPath = request.getServletPath();
        String[] split = servletPath.split("/");
        if (split.length>2){
            String serviceId = split[2];
            log.debug("SERVICE_ID-->{}",serviceId);
            //String serviceId = logBack.serviceId();
            MDC.put("SERVICE_ID",serviceId);
        }
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                            @Nullable ModelAndView modelAndView) throws Exception {
        log.debug("<---postHandle is start--->");
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                 @Nullable Exception ex) throws Exception {
        log.debug("<---afterCompletion is start--->");
    }

}
