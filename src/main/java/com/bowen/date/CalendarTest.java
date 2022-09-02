package com.bowen.date;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarTest {
    @Test
    public void test() throws ParseException {
        String dateStr = "2022-07-17 18:47:13";
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sf.parse(dateStr);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int y = cal.get(Calendar.YEAR);
        int i = cal.get(Calendar.MONTH);
        int d = cal.get(Calendar.DAY_OF_MONTH);
        int h = cal.get(Calendar.HOUR_OF_DAY);
        int i1 = cal.get(Calendar.MINUTE);
        System.out.println(y +"-"+ i+"-"+d +"  "+h +":"+i1);
        Date time = cal.getTime();

        cal.add(1,Calendar.DAY_OF_MONTH);
        Date now = new Date();
        int i2 = cal.getTime().compareTo(now);
        System.out.println(i2);


    }
}
