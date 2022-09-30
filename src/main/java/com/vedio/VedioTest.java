package com.vedio;

import org.junit.Test;

import java.io.File;

public class VedioTest {
    @Test
    public void test1(){
        File file=new File("D:\\testdemo\\a.MP4");
        try {
            VideoUtils.fetchPic(file,"D:\\testdemo\\ok.jpg");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testTime(){
        File file=new File("D:\\testdemo\\a.MP4");
        Long videoTime = VideoUtils.getVideoTime(file);
        System.out.println(videoTime);
    }
}
