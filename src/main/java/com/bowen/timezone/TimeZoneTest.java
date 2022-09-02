package com.bowen.timezone;

import org.junit.Test;

import java.util.Calendar;
import java.util.TimeZone;

public class TimeZoneTest {
    @Test
    public void test1(){
        //获取 JVM 启动时获取的时区
        TimeZone aDefault = TimeZone.getDefault();
        System.out.println(aDefault);

        TimeZone utc = TimeZone.getTimeZone("UTC");
        System.out.println(utc);
        TimeZone gmt = TimeZone.getTimeZone("GMT");
        System.out.println(gmt);
        TimeZone gmt1 = TimeZone.getTimeZone("GMT+08:00");
        System.out.println(gmt1);
        TimeZone shanghai = TimeZone.getTimeZone("Asia/Shanghai");
        System.out.println(shanghai);

    }

    @Test
    public void test2(){
        Calendar ca = Calendar.getInstance();
        TimeZone tz = ca.getTimeZone();
        System.out.println(tz.getID());
    }
}
