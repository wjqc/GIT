package com.qc.common.utils;

import org.slf4j.Logger;
import org.slf4j.MDC;

/**
 * @author wj
 * @date 2021/8/24
 * @apiNote
 */
public class LogbackUtils {

    private static final String MDC_KEY = "SERVICE_ID";

    public static void debug(String serviceId,String format, String message, Logger log){
        MDC.put(MDC_KEY,serviceId);
        log.debug(format,message);
    }

    public static void warn(String serviceId, String format,String message, Logger log){
        MDC.put(MDC_KEY,serviceId);
        log.warn(format,message);
    }

    public static void info(String serviceId, String format, String message, Logger log){
        MDC.put(MDC_KEY,serviceId);
        log.info(format,message);
    }

    public static void error(String serviceId, String format, String message, Logger log){
        MDC.put(MDC_KEY,serviceId);
        log.error(format,message);
    }

    public static void trace(String serviceId,String format,  String message, Logger log){
        MDC.put(MDC_KEY,serviceId);
        log.trace(format,message);
    }

    public static void debug(String serviceId, String message, Logger log){
        MDC.put(MDC_KEY,serviceId);
        log.debug(message);
    }

    public static void warn(String serviceId, String message, Logger log){
        MDC.put(MDC_KEY,serviceId);
        log.warn(message);
    }

    public static void info(String serviceId, String message, Logger log){
        MDC.put(MDC_KEY,serviceId);
        log.info(message);
    }

    public static void error(String serviceId, String message, Logger log){
        MDC.put(MDC_KEY,serviceId);
        log.error(message);
    }

    public static void trace(String serviceId, String message, Logger log){
        MDC.put(MDC_KEY,serviceId);
        log.trace(message);
    }

    public static void debug(String serviceId, String message, Throwable throwable, Logger log){
        MDC.put(MDC_KEY,serviceId);
        log.debug(message,throwable);
    }

    public static void warn(String serviceId, String message, Throwable throwable, Logger log){
        MDC.put(MDC_KEY,serviceId);
        log.warn(message,throwable);
    }

    public static void info(String serviceId, String message, Throwable throwable, Logger log){
        MDC.put(MDC_KEY,serviceId);
        log.info(message,throwable);
    }

    public static void error(String serviceId, String message, Throwable throwable, Logger log){
        MDC.put(MDC_KEY,serviceId);
        log.error(message,throwable);
    }

    public static void trace(String serviceId, String message, Throwable throwable, Logger log){
        MDC.put(MDC_KEY,serviceId);
        log.trace(message,throwable);
    }
}
