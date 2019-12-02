package com.bowen.methodcallback.demo3;
import org.apache.commons.net.ftp.FTPClient;

import java.io.IOException;
/**
 * description
 */
public interface FtpCallback {
    void invoke(String path, FTPClient ftp) throws IOException;
}
