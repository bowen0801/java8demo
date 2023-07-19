package com.hutool;

import cn.hutool.core.util.NumberUtil;

import java.math.BigDecimal;

public class NumberUtilTeste {
    public static void main(String[] args) {

        double n1 = 1.234;
        double n2 = 1.234;
        double result;

        //对float、double、BigDecimal做加减乘除操作
        result = NumberUtil.add(n1, n2);
        result = NumberUtil.sub(n1, n2);
        result = NumberUtil.mul(n1, n2);
        result = NumberUtil.div(n1, n2);

        // 保留两位小数
        BigDecimal roundNum = NumberUtil.round(n1, 2);
        String n3 = "1.234";

        // 判断是否为数字、整数、浮点数
        NumberUtil.isNumber(n3);
        NumberUtil.isInteger(n3);
        NumberUtil.isDouble(n3);


    }
}
