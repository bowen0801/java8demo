package com.bowen.bigdecimal;

import org.junit.Test;

import java.math.BigDecimal;

public class BigDecimalTest {
    @Test
    public void test1(){
        //当前小数点后面的位数过多的时候，多余的0没有实际意义，根据业务需求可以选择保留或者去掉多余的0。后端存储浮点型数据一般会用到BigDecimal类型，可以使用相关的方法去掉小数后面多余的0，然后转成String类。
        BigDecimal b1 = new BigDecimal(310);
        BigDecimal b2 = new BigDecimal(100);
        BigDecimal bigDecimal = b1.divide(b2).setScale(2, BigDecimal.ROUND_HALF_UP);
        String s = bigDecimal.stripTrailingZeros().toPlainString();
        System.out.println("s:"+s);//3.1

        //double数字直接转BigDecimal时最好使用String构造器或者使用valueOf
        BigDecimal c1 = new BigDecimal("0.10");
        String s1 = c1.stripTrailingZeros().toPlainString();
        System.out.println("s1:"+s1);// 0.1

        BigDecimal c2 = new BigDecimal(0.10);
        String s2 = c2.stripTrailingZeros().toPlainString();
        System.out.println(s2);//0.1000000000000000055511151231257827021181583404541015625

        String c4 = BigDecimal.valueOf(0.10).stripTrailingZeros().toPlainString();
        System.out.println(c4);//0.1


        double d = 0.1;
        String s3 = Double.toString(d);
        System.out.println(s3);

    }
}
