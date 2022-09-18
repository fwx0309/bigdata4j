package org.fwx.lambda.time;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Set;

/**
 * [ JDK 8 中，时间操作类 ]
 *
 * @author : [ fwx ]
 * @version : [ v1.0 ]
 * @createTime : [ 2022/6/10 14:13 ]
 */
public class LocalDateTimeTest {

    /**
     *  1.LocalDateTime
     */
    @Test
    public void test() {
        // 获取当前时间
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("localDateTime = " + localDateTime);

        // 指定时间 (有多个重载方法)
        LocalDateTime localDateTime1 = LocalDateTime.of(2022, 6, 10, 14, 55, 33, 1);
        System.out.println("localDateTime1 = " + localDateTime1);

        // 时间操作
        System.out.println("localDateTime.plusDays(2) = " + localDateTime.plusDays(2));
        System.out.println("localDateTime.minusDays(2) = " + localDateTime.minusDays(2));

        // 分别获取年月日
        System.out.println("localDateTime.getYear() = " + localDateTime.getYear());
        System.out.println("localDateTime.getMonth() = " + localDateTime.getMonth());
        System.out.println("localDateTime.getMonthValue() = " + localDateTime.getMonthValue());
        System.out.println("localDateTime.getDayOfMonth() = " + localDateTime.getDayOfMonth());
        System.out.println("localDateTime.getDayOfYear() = " + localDateTime.getDayOfYear());
        System.out.println("localDateTime.getHour() = " + localDateTime.getHour());
        System.out.println("localDateTime.getMinute() = " + localDateTime.getMinute());
        System.out.println("localDateTime.getSecond() = " + localDateTime.getSecond());
        System.out.println("localDateTime.getNano() = " + localDateTime.getNano());
        // localDateTime 转 时间戳
        System.out.println("localDateTime.toEpochSecond(ZoneOffset.ofHours(8)) = " + localDateTime.toEpochSecond(ZoneOffset.ofHours(8)));
        // 时间戳 转 localDateTime
        LocalDateTime localDateTime2 = LocalDateTime.ofEpochSecond(1656226061L, 0, ZoneOffset.ofHours(8));
        System.out.println("localDateTime2 = " + localDateTime2);
    }

    /**
     *  2. Instant : 时间戳。 （使用 Unix 元年  1970年1月1日 00:00:00 所经历的毫秒值）
     */
    @Test
    public void test1() {
        //默认使用 UTC 时区
        Instant instant = Instant.now();
        System.out.println("instant = " + instant);

        // 修改时区
        ZoneOffset zoneOffset = ZoneOffset.ofHours(8);
        OffsetDateTime offsetDateTime = instant.atOffset(zoneOffset);
        System.out.println("offsetDateTime = " + offsetDateTime);

        // 获取时间戳（13位）
        long epochMilli = offsetDateTime.toInstant().toEpochMilli();
        System.out.println("epochMilli = " + epochMilli);
        // 系统类获取时间戳方式（13位）
        System.out.println("System.currentTimeMillis() = " + System.currentTimeMillis());

        // 通过时间戳(10位)构建 instant
        Instant instant1 = Instant.ofEpochSecond(1654847585L);
        System.out.println("instant1 = " + instant1);
    }

    /**
     *  3. 时间计算
     *      Duration : 用于计算两个“时间”间隔
     *      Period : 用于计算两个“日期”间隔
     */
    @Test
    public void test2() throws InterruptedException {

        // Duration(持续时间): Instant
        Instant i1 = Instant.now();
        Thread.sleep(1000);
        Instant i2 = Instant.now();
        System.out.println("Duration.between(i1,i2) = " + Duration.between(i1, i2).toMillis());

        // Duration(持续时间): LocalTime
        LocalTime l1 = LocalTime.now();
        Thread.sleep(1000);
        LocalTime l2 = LocalTime.now();
        System.out.println("Duration.between(l1,l2) = " + Duration.between(l1, l2).toMillis());

        // Period 周期
        LocalDate d1 = LocalDate.of(2020,1,20);
        Thread.sleep(1000);
        LocalDate d2 = LocalDate.now();
        System.out.println("Period.between(d1,d2) = " + Period.between(d1, d2));
    }

    /**
     *  4. TemporalAdjuster : 时间校正器
     */
    @Test
    public void test3() {
        // 获取系统时间
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("localDateTime = " + localDateTime);

        // 获取下个周日的时间
        LocalDateTime localDateTime1 = localDateTime.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        System.out.println("localDateTime1 = " + localDateTime1);

        // 获取下一个工作日 TemporalAdjuster 为函数接口，这里自定义一个函数接口
        LocalDateTime localDateTime2 = localDateTime.with((l) -> {
            LocalDateTime ldt = (LocalDateTime) l;

            DayOfWeek dayOfWeek = ldt.getDayOfWeek();
            // 工作日算法：周五加3天；周六加两天；其它加一天；
            if (dayOfWeek.equals(DayOfWeek.FRIDAY)) {
                return ldt.plusDays(3);
            } else if (dayOfWeek.equals(DayOfWeek.SATURDAY)) {
                return ldt.plusDays(2);
            } else {
                return ldt.plusDays(1);
            }
        });
        System.out.println("localDateTime2 = " + localDateTime2);
        System.out.println("localDateTime2.getDayOfWeek() = " + localDateTime2.getDayOfWeek());
    }

    /**
     *  5. DateTimeFormatter : 解析和格式化日期或时间
     */
    @Test
    public void test4() {
        // 工具类自带的格式
        DateTimeFormatter localDateTime = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        LocalDateTime now = LocalDateTime.now();
        String format = localDateTime.format(now);
        System.out.println("format = " + format);

        // 自定义格式
        DateTimeFormatter customPattern = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
        String format1 = customPattern.format(now);
        System.out.println("format1 = " + format1);

        // 解析 2022年06月14日 08:31:09
        LocalDateTime parse = LocalDateTime.parse(format1, customPattern);
        System.out.println("parse = " + parse);
    }

    /**
     *  6.ZonedDate、ZonedTime、ZonedDateTime ： 带时区的时间或日期
     */
    @Test
    public void test5() {
        //Set<String> zoneIds = ZoneId.getAvailableZoneIds();
        //Asia/Hong_Kong Asia/Shanghai America/Los_Angeles(洛杉矶)
        //zoneIds.forEach(System.out::println);
        LocalDateTime now = LocalDateTime.now(ZoneId.of("America/Los_Angeles"));
        System.out.println("now = " + now);

        // 显示时区时间
        LocalDateTime now1 = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        System.out.println("now1 = " + now1);
        ZonedDateTime zonedDateTime = now1.atZone(ZoneId.of("Asia/Shanghai"));
        System.out.println("zonedDateTime = " + zonedDateTime);
    }
}
