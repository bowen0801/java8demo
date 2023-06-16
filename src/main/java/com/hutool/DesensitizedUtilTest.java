package com.hutool;

import cn.hutool.core.util.DesensitizedUtil;
import org.junit.Test;

/**
 * 数据脱敏
 * 一、功能
 * 对应一些敏感进行脱敏
 *
 * 二、DesensitizedUtil支持的脱敏数据类型
 * 用户id
 * 中文姓名
 * 身份证号
 * 座机号
 * 手机号
 * 地址
 * 电子邮件
 * 密码
 * 中国大陆车牌，包含普通车辆、新能源车辆
 * 银行卡
 */
public class DesensitizedUtilTest {

    @Test
    public void test1(){
        //3.1 中文姓名脱敏
        String chineseName = DesensitizedUtil.chineseName("历飞雨");
        System.out.println(chineseName);
    }

    @Test
    public void test2(){
        //3.2 身份证号脱敏
        String s = DesensitizedUtil.idCardNum("51343620000320711X", 5, 2);
        System.out.println(s);
    }

    @Test
    public void test3(){
        //3.3 手机号脱敏
        String s = DesensitizedUtil.mobilePhone("18049531999");
        System.out.println(s);
    }

    @Test
    public void test4(){
        //3.4 邮箱脱敏
        String email = DesensitizedUtil.email("1792561025@qq.com");
        System.out.println(email);
    }

    @Test
    public void test5(){
        //3.5 银行卡号脱敏
        String bankCard = DesensitizedUtil.bankCard("9559980868435875810");
        System.out.println(bankCard);
    }



}
