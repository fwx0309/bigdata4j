package org.fwx.java.base.d08date;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @ClassName CalendarTest01
 * @Description TODO
 * @Author Fwx
 * @Date 2023/3/21 17:40
 * @Version 1.0
 */
public class CalendarTest01 {
    public static void main(String[] args) {
        // *** 1. 设置时区
        // 获取所有的可用时区id
        String[] availableIDs = TimeZone.getAvailableIDs();
        /*for (String availableID : availableIDs) {
            System.out.println(availableID);
        }*/

        TimeZone timeZone = TimeZone.getTimeZone("Asia/Shanghai");
        System.out.println(timeZone);

        // 默认是系统时区
        Calendar calendar1 = Calendar.getInstance(TimeZone.getDefault());
        System.out.println(calendar1.getTime());
        System.out.println(calendar1.getTimeZone());


        // *** 2. Calendar 方法测试
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));

        calendar.add(Calendar.DAY_OF_MONTH,2);
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));

        calendar.set(Calendar.DAY_OF_MONTH,1);
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));

        calendar.setTime(new Date());
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));

        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
        System.out.println(calendar.get(Calendar.DAY_OF_WEEK));
        System.out.println(calendar.get(Calendar.DAY_OF_YEAR));
        System.out.println(calendar.get(Calendar.YEAR));

    }
}
