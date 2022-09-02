package com.bowen;

import java.math.BigDecimal;

public class LongTest {
	public static void main(String[] args) {
		String a = "0.01";
		BigDecimal b = new BigDecimal(a);
		b = b.setScale(2, BigDecimal.ROUND_HALF_UP); //四舍五入
		System.out.println(b.doubleValue());
		
		String s="125.671";
		BigDecimal d = new BigDecimal(s); 
		d=d.setScale(2, BigDecimal.ROUND_HALF_UP); //小数位 直接舍去
		System.out.println(d.longValue());
		//b=b.setScale(2, BigDecimal.ROUND_HALF_UP); //四舍五入
		
		BigDecimal bg = new BigDecimal(a).multiply(new BigDecimal(100));
        Long f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).longValue();
        System.out.println(f1);
        
        Long c = 100L;
        Long m = 52L;
        String ts = String.valueOf(new BigDecimal(c).subtract(new BigDecimal(m)).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP));
        
        System.out.println(ts);
        
	}

}
