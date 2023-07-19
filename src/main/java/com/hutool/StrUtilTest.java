package com.hutool;

import cn.hutool.core.util.StrUtil;

public class StrUtilTest {
    public static void main(String[] args) {
        // 判断是否为空字符串
        String str = "test";
        boolean empty = StrUtil.isEmpty(str);
        boolean notEmpty = StrUtil.isNotEmpty(str);
        System.out.println("empty"+empty+" notEmpty"+notEmpty);

        // 去除字符串的前后缀
        String s = StrUtil.removeSuffix("a.jpg", ".jpg");
        String s1 = StrUtil.removePrefix("a.jpg", "a.");
        System.out.println("s"+s);
        System.out.println("s1"+s1);
        // 格式化字符串
        String template = "这只是个占位符:{}";
        String str2 = StrUtil.format(template, "我是占位符");
        System.out.println("str2"+str2);

    }
}
