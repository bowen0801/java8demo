package com.hutool;

import cn.hutool.core.io.resource.ClassPathResource;

import java.io.IOException;
import java.util.Properties;

public class ClassPathResourceTest {
    public static void main(String[] args) throws IOException {
        // 获取定义在src/main/resources文件夹中的配置文件
        ClassPathResource resource = new ClassPathResource("generator.properties");
        Properties properties = new Properties();
        properties.load(resource.getStream());
        System.out.println("/classPath:{}"+properties);
    }
}
