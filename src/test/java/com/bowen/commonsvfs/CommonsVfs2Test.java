package com.bowen.commonsvfs;

import org.apache.commons.vfs2.*;
import org.junit.Test;

import java.io.IOException;

/**
 * Apache Commons VFS
 */
public class CommonsVfs2Test {

    @Test
    public void test1() throws FileSystemException {
        FileSystemManager fsMgr = VFS.getManager();
        String path = "D:/test.txt";
        FileObject fo = fsMgr.resolveFile(path);
        System.out.println(fo.getFileSystem());
        if (!fo.exists()) {
            System.out.println("fo not exists");
            return;
        }
        System.out.println("parent："+fo.getParent().toString());// "file:///D:/"
        System.out.println("name："+fo.getName());// "file:///D:/test"
        System.out.println("path："+fo.getPath());// "D:\test"
        System.out.println("pubURI："+fo.getPublicURIString());// "file:///D:/test"
        System.out.println("URI："+fo.getURI().toString());// "file:///D:/test"
        System.out.println("URL："+fo.getURL());// "file:///D:/test"
        boolean isFile = fo.isFile();
        boolean isFolder = fo.isFolder();
        // 是否符号链接
        boolean isSymbolic = fo.isSymbolicLink();
        boolean executable = fo.isExecutable();
        boolean isHidden = fo.isHidden();
        System.out.println("type："+fo.getType());
        // 应该放到finally块中关闭，为了便于阅读直接在此关闭了
        // 会同时关闭FileContent并释放FileObject
        fo.close();
        // 关闭文件系统，释放连接，清除缓存等
        fsMgr.close();

    }

    @Test
    public void readContent() throws IOException {
        // 读取文件内容
        // 支持获取字符串，流，字节数组等
        FileSystemManager fsMgr = VFS.getManager();
        String path = "D:/test.txt";
        FileObject fo = fsMgr.resolveFile(path);
        if (fo.isFile()) {
            FileContent fc = fo.getContent();
            // fc.getInputStream();
            // fc.getByteArray();
            // 获取内容 - 字符串形式
            String content = fc.getString("UTF-8");
            System.out.println(content);
        }
        // 在finally或者try-resources中关闭资源
        fo.close();
        fsMgr.close();
    }
}
