package com.bowen.methodcallback.demo3;
/**
 * description
 * ftp上传业务部分抽取出来，ftp连接，关闭封装了起来，通过接口回调的方式实现
 * @version v1.0.0
 * @since 2019年12月02日
 */
public class Test {
    public static void main(String[] args) {
        /*ProductTagFtpCallback callback = new ProductTagFtpCallback() {
            @Override
            public void invoke(String path, FTPClient ftp) throws IOException {
            }
        };*/
        ProductTagFtpCallback callback = new ProductTagFtpCallbackImpl();
        ImageUtil.ftp("/c/cms/banner/",callback);
    }
}
