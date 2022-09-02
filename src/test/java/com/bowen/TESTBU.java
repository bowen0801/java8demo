package com.bowen;
/**
 * description
 *
 * @version v1.0.0
 * @since 2020年04月15日
 */
public class TESTBU {
    public static void main(String[] args) {
        StringBuffer buf = new StringBuffer();
        buf.append("aaad").append(",");
        buf.deleteCharAt(buf.length() -1);
        System.out.println(buf.toString());
    }
}
