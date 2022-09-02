package com.bowen.base64;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.util.Base64;

public class Base64Test {
    public static void main(String[] args) throws IOException {
        String str = "超越技术";
        BASE64Encoder encoder = new BASE64Encoder();
        String enStr = encoder.encode(str.getBytes("UTF-8"));
        System.out.println(enStr);


        char[] pem_array = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};
        System.out.println(pem_array.length);

        BASE64Encoder encoder1 = new BASE64Encoder();
        String enStr1 = encoder1.encode(str.getBytes("UTF-8"));

        String str1 = "中国";
        // 字符"中国"在转换为二进制过程中进行了那些操作分析如下：
        /**                         中                                国
         * 在UTF-8编码表中对应的十进制  14989485                          15047613
         *转换为二进制                111001001011100010101101          111001011001101110111101
         * 按8bit一组分成字节         11100100  10111000  10101101      11100101  10011011  10111101   这是分成了6个字节
         * 转换成十进制               228        184       173            229      155       189
         *
         *  ？？？                    -28       -72        -83         -27      -101      -67
         *
         */

        System.out.println(enStr1);//5Lit5Zu9
        byte[] strs = str1.getBytes("UTF-8");

        System.out.println(strs);
    }
}
