package com.bowen.date;

import com.videocut.Test1;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class LocalDateTimeTest {
    public static void main(String[] args) {

    }

    //创建方法
    @Test
    public void test1(){
        //获取当前系统时间
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);//2023-04-25T09:46:00.742
        //使用指定年月日和时分秒初始化一个LocalDateTime对象
        LocalDateTime localDateTime = LocalDateTime.of(2020, 11, 11, 11, 11, 11);
        System.out.println(localDateTime);
    }
    //获取方法
    @Test
    public void test2(){
        LocalDateTime localDateTime = LocalDateTime.of(2020, 11, 11, 11, 11, 20);
        ////public int getYear()           获取年
        int year = localDateTime.getYear();
        System.out.println("年为" +year);//年为2020
        //public int getMonthValue()     获取月份（1-12）

        int month = localDateTime.getMonthValue();
        System.out.println("月份为" + month);//月份为11

        Month month1 = localDateTime.getMonth();
        System.out.println(month1);//NOVEMBER

        //public int getDayOfMonth()     获取月份中的第几天（1-31）
        int day = localDateTime.getDayOfMonth();
        System.out.println("日期为" + day);//日期为11

        //public int getDayOfYear()      获取一年中的第几天（1-366）
        int dayOfYear = localDateTime.getDayOfYear();
        System.out.println("这是一年中的第" + dayOfYear + "天");//这是一年中的第316天

        //public DayOfWeek getDayOfWeek()获取星期
        DayOfWeek dayOfWeek = localDateTime.getDayOfWeek();
        System.out.println("星期为" + dayOfWeek);//星期为WEDNESDAY

        //public int getMinute()        获取分钟
        int minute = localDateTime.getMinute();
        System.out.println("分钟为" + minute);//分钟为11

        //public int getHour()           获取小时
        int hour = localDateTime.getHour();
        System.out.println("小时为" + hour);//小时为11
    }

    //LocalDateTime转换方法
    @Test
    public void test3(){
        LocalDateTime localDateTime = LocalDateTime.of(2020, 12, 12, 8, 10, 12);
        //public LocalDate toLocalDate ()    转换成为一个LocalDate对象
        LocalDate localDate = localDateTime.toLocalDate();
        System.out.println(localDate);//2020-12-12

        //public LocalTime toLocalTime ()    转换成为一个LocalTime对象
        LocalTime localTime = localDateTime.toLocalTime();
        System.out.println(localTime);//08:10:12
    }

    //LocalDateTime格式化和解析
    @Test
    public void test4(){
        //把一个日期字符串解析成为一个LocalDateTime对象
        //public static LocalDateTime parse (准备解析的字符串, 解析格式) 把一个日期字符串解析成为一个LocalDateTime对象
        String s = "2020年11月12日 13:14:15";
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
        LocalDateTime parse = LocalDateTime.parse(s, pattern);
        System.out.println(parse);//2020-11-12T13:14:15


        //把一个LocalDateTime格式化成为一个字符串
        LocalDateTime localDateTime = LocalDateTime.of(2020, 11, 12, 13, 14, 15);
        System.out.println(localDateTime);
        //public String format (指定格式)   把一个LocalDateTime格式化成为一个字符串
        DateTimeFormatter pattern1 = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
        String s1 = localDateTime.format(pattern1);
        System.out.println(s1);//2020年11月12日 13:14:15
    }

    //LocalDateTime增加或者减少时间的方法

    /**
     * 方法名	说明
     * public LocalDateTime plusYears (long years)	添加或者减去年
     * public LocalDateTime plusMonths(long months)	添加或者减去月
     * public LocalDateTime plusDays(long days)	添加或者减去日
     * public LocalDateTime plusHours(long hours)	添加或者减去时
     * public LocalDateTime plusMinutes(long minutes)	添加或者减去分
     * public LocalDateTime plusSeconds(long seconds)	添加或者减去秒
     * public LocalDateTime plusWeeks(long weeks)	添加或者减去周
     */
    @Test
    public void test5(){
        //public LocalDateTime plusYears (long years)   添加或者减去年

        LocalDateTime localDateTime = LocalDateTime.of(2020, 11, 11, 13, 14, 15);
        //LocalDateTime newLocalDateTime = localDateTime.plusYears(1);
        //System.out.println(newLocalDateTime);

        LocalDateTime newLocalDateTime = localDateTime.plusYears(-1);
        System.out.println(newLocalDateTime);//2019-11-11T13:14:15
    }

    //LocalDateTime减少或者增加时间的方法

    /**
     * 方法名	说明
     * public LocalDateTime minusYears (long years)	减去或者添加年
     * public LocalDateTime minusMonths(long months)	减去或者添加月
     * public LocalDateTime minusDays(long days)	减去或者添加日
     * public LocalDateTime minusHours(long hours)	减去或者添加时
     * public LocalDateTime minusMinutes(long minutes)	减去或者添加分
     * public LocalDateTime minusSeconds(long seconds)	减去或者添加秒
     * public LocalDateTime minusWeeks(long weeks)	减去或者添加周
     */
    @Test
    public void test6(){
        //public LocalDateTime minusYears (long years)  减去或者添加年
        LocalDateTime localDateTime = LocalDateTime.of(2020, 11, 11, 13, 14, 15);
        //LocalDateTime newLocalDateTime = localDateTime.minusYears(1);
        //System.out.println(newLocalDateTime);

        LocalDateTime newLocalDateTime = localDateTime.minusYears(-1);
        System.out.println(newLocalDateTime);//2021-11-11T13:14:15
    }

    //LocalDateTime修改方法

    /**
     * 方法名	说明
     * public LocalDateTime withYear(int year)	直接修改年
     * public LocalDateTime withMonth(int month)	直接修改月
     * public LocalDateTime withDayOfMonth(int dayofmonth)	直接修改日期(一个月中的第几天)
     * public LocalDateTime withDayOfYear(int dayOfYear)	直接修改日期(一年中的第几天)
     * public LocalDateTime withHour(int hour)	直接修改小时
     * public LocalDateTime withMinute(int minute)	直接修改分钟
     * public LocalDateTime withSecond(int second)	直接修改秒
     */
    @Test
    public void test7(){
        //public LocalDateTime withYear(int year)   修改年
        LocalDateTime localDateTime = LocalDateTime.of(2020, 11, 11, 13, 14, 15);
        // LocalDateTime newLocalDateTime = localDateTime.withYear(2048);
        // System.out.println(newLocalDateTime);

        LocalDateTime newLocalDateTime = localDateTime.withMonth(10);
        System.out.println(newLocalDateTime);
    }

    /**
     * Period应用 计算两个时间的间隔
     * 方法名	说明
     * public static Period between(开始时间,结束时间)	计算两个“时间"的间隔
     * public int getYears()	获得这段时间的年数
     * public int getMonths()	获得此期间的总月数
     * public int getDays()	获得此期间的天数
     * public long toTotalMonths()	获取此期间的总月数
     */
    @Test
    public void test8(){
        //public static Period between(开始时间,结束时间)  计算两个"时间"的间隔

        LocalDate localDate1 = LocalDate.of(2020, 1, 1);
        LocalDate localDate2 = LocalDate.of(2048, 12, 12);
        Period period = Period.between(localDate1, localDate2);
        System.out.println(period);//P28Y11M11D

        //public int getYears()         获得这段时间的年数
        System.out.println(period.getYears());//28
        //public int getMonths()        获得此期间的月数
        System.out.println(period.getMonths());//11
        //public int getDays()          获得此期间的天数
        System.out.println(period.getDays());//11

        //public long toTotalMonths()   获取此期间的总月数
        System.out.println(period.toTotalMonths());//347
    }

    /**
     * Duration
     * 方法名	说明
     * public static Durationbetween(开始时间,结束时间)	计算两个“时间"的间隔
     * public long toSeconds()	获得此时间间隔的秒
     * public int toMillis()	获得此时间间隔的毫秒
     * public int toNanos()	获得此时间间隔的纳秒
     */
    @Test
    public void test9(){
        //public static Duration between(开始时间,结束时间)  计算两个“时间"的间隔

        LocalDateTime localDateTime1 = LocalDateTime.of(2020, 1, 1, 13, 14, 15);
        LocalDateTime localDateTime2 = LocalDateTime.of(2020, 1, 2, 11, 12, 13);
        Duration duration = Duration.between(localDateTime1, localDateTime2);
        System.out.println(duration);//PT21H57M58S
        //public long toSeconds()	       获得此时间间隔的秒
        //System.out.println(duration.toSeconds());//79078
        //public int toMillis()	           获得此时间间隔的毫秒
        System.out.println(duration.toMillis());//79078000
        //public int toNanos()             获得此时间间隔的纳秒
        System.out.println(duration.toNanos());//79078000000000
    }

    @Test
    public void test10(){
        //比较两个时间大小
        LocalDateTime localDateTime1 = LocalDateTime.of(2022, 1, 1, 13, 14, 15);
        LocalDateTime localDateTime2 = LocalDateTime.of(2023, 1, 1, 13, 14, 15);
        System.out.println(localDateTime1.isBefore(localDateTime2));
        System.out.println(localDateTime1.isEqual(localDateTime2));
        System.out.println(localDateTime1.isAfter(localDateTime2));
    }

    @Test
    public void test12(){
        //Date LocalDateTime相互转换
        LocalDateTime localDateTime = LocalDateTime.now();
        Date date = Date.from( localDateTime.atZone( ZoneId.systemDefault()).toInstant());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String format = df.format(date);
        System.out.println(format);//2023-04-25 11:37:39

        Date todayDate = new Date();
        LocalDateTime ldt = todayDate.toInstant()
                .atZone( ZoneId.systemDefault())
                .toLocalDateTime();
        System.out.println(ldt);


    }
}
