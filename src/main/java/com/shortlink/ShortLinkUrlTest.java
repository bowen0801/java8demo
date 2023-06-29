package com.shortlink;

import com.google.common.hash.Hashing;

public class ShortLinkUrlTest {
    public static void main(String[] args) {
        String url="http://www.baidu.com/id=123456789&name=987654321";
        String shortLinkCode = createShortLinkCode(url);
        System.out.println(shortLinkCode);//4uy2GC
    }



    private static String CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    /**
     * murmurhash算法
     * @param param
     * @return
     */
    public static long murmurHash32(String param){
        long murmurHash32 = Hashing.murmur3_32().hashUnencodedChars(param).padToLong();
        return murmurHash32;
    }

    //MurmurHash生成得到的是一个long类型的10进制数，通常我们为了缩短短链的位数，可以适用Base62将结果转换为62进制数

    /**
     * 10进制转62进制
     * @param num
     * @return
     */
    private static  String encodeToBase62(long num){

        // StringBuffer线程安全，StringBuilder线程不安全
        StringBuffer sb = new StringBuffer();
        do{
            int i = (int )(num%62);
            sb.append(CHARS.charAt(i));
            num = num/62;
        }while (num>0);

        String value = sb.reverse().toString();
        return value;
    }

    /**
     * 生成短链码
     * @param param
     * @return
     */
    public static String createShortLinkCode(String param){
        //获取MurmurHash的值
        long murmurhash = murmurHash32(param);
        System.out.println(murmurhash);
        //进制转换
        String code = encodeToBase62(murmurhash);
        return code;
    }

}
