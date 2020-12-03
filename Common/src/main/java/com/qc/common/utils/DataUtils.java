package com.qc.common.utils;

import java.util.Collection;
import java.util.Map;

public class DataUtils {

    /**
     * 处理返回值为空情况
     *
     * @param obj
     * @return
     */
    public static String isNull(Object obj) {
        if (obj == null) {
            return "";
        }
        return obj.toString();
    }

    /**
     * 判断对象或对象数组中每一个对象是否为空: 对象为null，字符序列长度为0，集合类、Map为empty
     *
     * @param obj
     * @return
     */
    public static boolean isNullOrEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        if (obj instanceof CharSequence) {
            return ((CharSequence) obj).length() == 0;
        }
        if (obj instanceof Collection) {
            return ((Collection) obj).isEmpty();
        }

        if (obj instanceof Map) {
            return ((Map) obj).isEmpty();
        }

        if (obj instanceof Object[]) {
            Object[] object = (Object[]) obj;
            if (object.length <= 0) {
                return true;
            }
            boolean empty = true;
            for (Object object1 : object) {
                if (!isNullOrEmpty(object1)) {
                    empty = false;
                    break;
                }
            }
            return empty;
        }
        return false;
    }

}
