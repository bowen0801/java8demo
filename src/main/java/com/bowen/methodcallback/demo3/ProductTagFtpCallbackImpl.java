package com.bowen.methodcallback.demo3;
import org.apache.commons.net.ftp.FTPClient;

import java.io.IOException;
/**
 * description
 *
 * @version v1.0.0
 * @since 2019年12月02日
 */
public class ProductTagFtpCallbackImpl implements ProductTagFtpCallback{
    @Override
    public void invoke(String path, FTPClient ftp) throws IOException {
        //具体执行一些业务
        //执行ftp上传
        System.out.println("upload...");
    }
}
