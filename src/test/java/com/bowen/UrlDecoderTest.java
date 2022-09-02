package com.bowen;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class UrlDecoderTest {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String a = "%E3%80%903%E7%93%B6%E8%A3%85%E3%80%91%5B%E4%BB%81%E5%92%8C%5D75%25%E9%85%92%E7%B2%BE%E6%B6%88%E6%AF%92%E6%B6%B2%28%E5%96%B7%E9%9B%BE%29";
        String b = URLDecoder.decode(a,"UTF-8");
        System.out.println(b);
        String c = URLDecoder.decode(b,"UTF-8");
        System.out.println(c);

    }
}
