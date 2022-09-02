package com.bowen;

import org.apache.commons.lang3.StringUtils;

public class NullTest {
    public static void main(String[] args) {
        String a = "null";
        Long shopId = StringUtils.isNotEmpty(a) ? Long.valueOf(a) : 0l;

    }
}
