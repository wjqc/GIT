package com.qc.common.utils;


import com.qc.common.Comparison;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: 对象比较类 
 * @Description: TODO
 * @author limh
 * @date 2017年3月2日
 */
public class CompareObjUtil {

    public static List<Comparison> compareObj(Object beforeObj, Object afterObj) throws Exception{
        List<Comparison> differents = new ArrayList<Comparison>();
        
        if(beforeObj == null) throw new Exception("原对象不能为空");
        if(afterObj == null) throw new Exception("新对象不能为空");
        if(!beforeObj.getClass().isAssignableFrom(afterObj.getClass())){
            throw new Exception("两个对象不相同，无法比较");
        }
        
        //取出属性
        Field[] beforeFields = beforeObj.getClass().getDeclaredFields();
        Field[] afterFields = afterObj.getClass().getDeclaredFields();
        Field.setAccessible(beforeFields, true); 
        Field.setAccessible(afterFields, true);
        
        //遍历取出差异值
        if(beforeFields != null && beforeFields.length > 0){
            for(int i=0; i<beforeFields.length; i++){
                Object beforeValue = beforeFields[i].get(beforeObj);
                Object afterValue = afterFields[i].get(afterObj);
                    if((beforeValue != null && !"".equals(beforeValue) && !beforeValue.equals(afterValue)) || ((beforeValue == null || "".equals(beforeValue)) && afterValue != null)){
                        Comparison comparison = new Comparison();
                        comparison.setField(beforeFields[i].getName());
                        comparison.setBefore(beforeValue);
                        comparison.setAfter(afterValue);
                        differents.add(comparison);
                    }
            }
        }
        
        return differents;
    }
}
