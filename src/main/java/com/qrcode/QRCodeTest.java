package com.qrcode;

import cn.hutool.extra.qrcode.QrCodeUtil;
import com.google.zxing.common.BitMatrix;

import java.io.File;

public class QRCodeTest {
    public static void main(String[] args) throws Exception {
        QRCodeUtil.encode("https://www.baidu.com","D:/小工具/");
        //QrCodeUtil.decode(new File());88991920.jpg
    }
}
