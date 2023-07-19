package com.hutool;

import cn.hutool.core.bean.BeanUtil;

import java.util.Map;

public class BeanUtilTest {
    public static void main(String[] args) {

        PmsBrand brand = new PmsBrand();
        brand.setId(1L);
        brand.setName("小米");
        brand.setShowStatus(0);

        // Bean转Map
        Map<String, Object> map = BeanUtil.beanToMap(brand);
        System.out.println("beanUtil bean to map:{}"+map);

        // Map转Bean
        PmsBrand mapBrand = BeanUtil.mapToBean(map, PmsBrand.class, false);
        System.out.println("beanUtil map to bean:{}"+mapBrand);

        // Bean属性拷贝
        PmsBrand copyBrand = new PmsBrand();
        BeanUtil.copyProperties(brand, copyBrand);
        System.out.println("beanUtil copy properties:{}"+copyBrand);
    }
}
