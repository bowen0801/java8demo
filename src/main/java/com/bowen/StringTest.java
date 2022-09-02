package com.bowen;

import org.junit.Test;

public class StringTest {

    @Test
    public void testconcat(){
        /**
         * 1. "a"+"b"
         * 2."a".concat("b")
         * 结果一样
         */
        //(skuId.toString().concat("_").concat(addOnItem.toString()).concat("_").concat(zeroBuyItem.toString()).concat("_").concat(promotionId.toString()).concat("_").concat(promotionType.toString()));

        /*
           1.字符串连接使用concat
        if (!shopNameList.isEmpty()) {
            String msg = "很抱歉，".concat(org.apache.commons.lang.StringUtils.join(shopNameList, "、")).concat("正在休息中");
            return new ResultObject (OrderResultEnum.ORDER_PHARMACY_OFFLINE.getIntCode(), msg, null, null, true);
        }*/


    }

    @Test
    public void testEqualsNull(){
        String source = null;
        boolean equals = "vhlive".equals(source);
        System.out.print(equals);
    }

    @Test
    public void testReplace(){
        String str = "a，b, c,d";
        String s = str.replaceAll(" ", "").replaceAll("，", ",");
        System.out.println(s);
    }
}
