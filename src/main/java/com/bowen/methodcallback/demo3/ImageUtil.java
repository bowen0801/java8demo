package com.bowen.methodcallback.demo3;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.IOException;
/**
 * description
 *
 * @version v1.0.0
 * @since 2019年12月02日
 */
public class ImageUtil {
    public static void ftp(String path, FtpCallback callback) {
        FTPClient ftp = new FTPClient();
        try {
            //连接ftp
            ftp.connect("192.168.86.117", 22);
            //登录
            ftp.login("admin","admin");//登录
            ftp.enterLocalPassiveMode();
            if (!FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
                throw new RuntimeException("ftp登陆失败！");
            }
            if (ftp.listFiles(path).length == 0) {
                if (!ftp.makeDirectory(path)) {
                    throw new RuntimeException("FTP创建目录失败:" + path);
                }
            }
            ftp.setControlEncoding("UTF8");
            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
            if(!ftp.changeWorkingDirectory(path)){
                throw new RuntimeException("FTP切换目录失败" + path);
            }
            callback.invoke(path, ftp);
            ftp.logout();
        } catch (IOException e) {
            throw new RuntimeException("FTP上传文件失败", e);
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
