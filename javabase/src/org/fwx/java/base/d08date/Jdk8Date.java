package org.fwx.java.base.d08date;

import org.junit.Test;

import java.time.*;
import java.time.chrono.Chronology;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

/**
 * @ClassName Jdk8Date
 * @Description
 *  1.LocalDate、LocalTime、LocalDateTime
 * @Author Fwx
 * @Date 2023/3/23 20:43
 * @Version 1.0
 */
public class Jdk8Date {

    @Test
    public void localDatesTest(){
        // 实例化方式一
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime localDateTime2 = LocalDateTime.now(ZoneId.of("America/Phoenix"));
        System.out.println(localDate);
        System.out.println(localTime);
        System.out.println(localDateTime);
        System.out.println("localDateTime2 = " + localDateTime2);
        System.out.println("---------------");

        // 实例化方式二
        LocalDate localDate1 = LocalDate.of(2022,1,2);
        LocalTime localTime1 = LocalTime.of(12,1,3);
        LocalDateTime localDateTime1 = LocalDateTime.of(2022,2,4,5,6,1);
        System.out.println(localDate1);
        System.out.println(localTime1);
        System.out.println(localDateTime1);
        System.out.println("---------------");

        // *** 操作方法
        // 获取值
        int dayOfMonth = localDate.get(ChronoField.DAY_OF_MONTH);
        System.out.println(dayOfMonth);
        int dayOfMonth1 = localDateTime.getDayOfMonth();
        System.out.println(dayOfMonth1);

        // 修改
        LocalDateTime with = localDateTime.with(ChronoField.DAY_OF_MONTH, 1);
        System.out.println(with);

        LocalDateTime with1 = localDateTime.withDayOfYear(2);
        System.out.println(with1);

        LocalDateTime plus = localDateTime.plus(3L, ChronoUnit.DAYS);
        System.out.println(plus);

        LocalDateTime plusDays = localDateTime.plusDays(1);
        System.out.println(plusDays);

        LocalDateTime minusDays = localDateTime.minusDays(1);
        System.out.println(minusDays);
        System.out.println("---------------");

        // 类型转换
        long epochSecond = localDateTime.toEpochSecond(ZoneOffset.ofHours(8));
        System.out.println("epochSecond = " + epochSecond);

        Date date = new Date(epochSecond * 1000);
        System.out.println("date = " + date);

        Date from = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        System.out.println("from = " + from);

        //Instant instant = localDateTime.toInstant(ZoneOffset.ofHours(0));
        Instant instant = localDateTime.toInstant(ZoneOffset.UTC);
        System.out.println("instant = " + instant);

        // 解析
        System.out.println("---------------");
        LocalDateTime parse = LocalDateTime.parse("2023-03-23T14:23:20");
        System.out.println("parse = " + parse);
    }

    @Test
    public void instantTest(){
        // 实例化方式一 (本初子午线)
        Instant now = Instant.now();
        System.out.println("now = " + now);

        ZonedDateTime atZone = now.atZone(ZoneId.of("Asia/Shanghai"));
        System.out.println("atZone = " + atZone);

        OffsetDateTime offsetDateTime = now.atOffset(ZoneOffset.ofHours(8));
        System.out.println("offsetDateTime = " + offsetDateTime);
        System.out.println("---------------");

        // 类型转换
        LocalDateTime localDateTime = offsetDateTime.toLocalDateTime();
        System.out.println("localDateTime = " + localDateTime);

        LocalDateTime localDateTime1 = atZone.toLocalDateTime();
        System.out.println("localDateTime1 = " + localDateTime1);

    }

    @Test
    public void dateTimeFormartterTest(){
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        String formatStr = pattern.format(localDateTime);
        System.out.println("formatStr = " + formatStr);

        TemporalAccessor parse = pattern.parse("2022-02-01 10:01:12");
        System.out.println("parse = " + parse);
        LocalDateTime parse3 = LocalDateTime.parse("2000-01-01 15:05:12", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println("parse3 = " + parse3);

        // *** LocalDateTime DateTimeFormatter 应用
        // yyyy-MM-dd hh:mm:ss 解析 如果格式是hh即12小时制，加上a !!!
        LocalDateTime parse1 = LocalDateTime.parse("2022-02-01 11:01:12 下午", DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a"));
        System.out.println("parse1 = " + parse1);
        // yyyy-MM-dd HH:mm:ss 解析 如果格式是HH即24小时制，不用加上a
        LocalDateTime parse2 = LocalDateTime.parse("2022-02-01 13:01:12", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println("parse2 = " + parse2);

        String format = parse2.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println("format = " + format);


    }
}
