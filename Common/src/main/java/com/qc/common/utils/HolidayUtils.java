package com.qc.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by liangyadong on 2018/8/28 0028.
 */
public class HolidayUtils {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    /**
     * 获取计划激活日期
     * @param today opening date
     * @param list holidayList
     * @param num num个工作日后
     * @return
     * @throws ParseException
     */
    public static Date getScheduleActiveDate(Date today, List<String> list, int num) throws ParseException {
        Date tomorrow = null;
        int delay = 1;
        while(delay <= num){
            tomorrow = getTomorrow(today);
            //当前日期+1,判断是否是节假日,不是的同时要判断是否是周末,都不是则scheduleActiveDate日期+1
            if(!isWeekend(sdf.format(tomorrow)) && !isHoliday(sdf.format(tomorrow),list)){
                delay++;
                today = tomorrow;
            } else if (isWeekend(sdf.format(tomorrow))){
                today = tomorrow;
            } else if (isHoliday(sdf.format(tomorrow),list)){
                today = tomorrow;
            }
        }

        return today;
    }

    /**
     * 获取tomorrow的日期
     *
     * @param date
     * @return
     */
    public static Date getTomorrow(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, +1);
        date = calendar.getTime();
        return date;
    }

    /**
     * 判断是否是weekend
     *
     * @param sdate
     * @return
     * @throws ParseException
     */
    public static boolean isWeekend(String sdate) throws ParseException {
        Date date = sdf.parse(sdate);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
            return true;
        } else{
            return false;
        }

    }

    /**
     * 判断是否是holiday
     *
     * @param sdate
     * @param list
     * @return
     * @throws ParseException
     */
    public static boolean isHoliday(String sdate, List<String> list) throws ParseException {
        if(list.size() > 0){
            for(int i = 0; i < list.size(); i++){
                if(sdate.equals(list.get(i))){
                    return true;
                }
            }
        }
        return false;
    }
}