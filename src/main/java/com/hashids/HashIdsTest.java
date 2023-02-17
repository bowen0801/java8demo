package com.hashids;

import com.alibaba.fastjson.JSON;
import org.hashids.Hashids;
import org.junit.Test;

public class HashIdsTest {
    public static void main(String[] args) {
        final String SALT = "this is my salt";
        final int MIN_HASH_LENGTH = 11;

        Hashids hashids = new Hashids(SALT, MIN_HASH_LENGTH);
        String encryptString = hashids.encode(347L);

        System.out.println(encryptString); // Y5bAyr8dLO4
        long[] decode = hashids.decode(encryptString);
        System.out.println(JSON.toJSONString(decode));


        /**
         *编码16进制字符串为Hashids字符串
         */

        /*Hashids hashids1 = new Hashids(SALT, MIN_HASH_LENGTH);
        String encryptString1 = hashids1.encodeHex(Hexcn.hutool.core.util.HexUtil.encodeHexStr("this is a string"));
        System.out.println(encryptString1); // 1prnZLrKPlS5EEe61reMCNzkJXP*/




    }

    /**
     * 自定义字母表映射集
     *
     * 作者：weixsun
     * 链接：https://www.imooc.com/article/316419
     * 来源：慕课网
     * 本文原创发布于慕课网 ，转载请注明出处，谢谢合作
     */
    @Test
    void test_hashids_costom_alphabet_by_Constructor() {
        final String SALT = "this is my salt";
        final int MIN_HASH_LENGTH = 11;
        final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890_-";


        Hashids hashids = new Hashids(SALT, MIN_HASH_LENGTH, ALPHABET);
        String encryptString = hashids.encode(347L);

        System.out.println(encryptString); // kqBg-Kpg7_J

    }
}
