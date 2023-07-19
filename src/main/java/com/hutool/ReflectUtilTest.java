package com.hutool;

import cn.hutool.core.util.ReflectUtil;

import java.lang.reflect.Method;

public class ReflectUtilTest {
    public static void main(String[] args) {

        // 获取某个类的所有方法
        Method[] methods = ReflectUtil.getMethods(PmsBrand.class);

        // 获取某个类的指定方法
        Method method = ReflectUtil.getMethod(PmsBrand.class, "getId");

        // 使用反射来创建对象
        PmsBrand pmsBrand = ReflectUtil.newInstance(PmsBrand.class);

        // 反射执行对象的方法
        ReflectUtil.invoke(pmsBrand, "setId", 1);

    }
}
