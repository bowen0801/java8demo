package com.bowen;

import com.util.HttpUtil;
import com.util.HttpUtil2;

import java.net.URL;

public class Test1 {
    public static void main(String[] args) {
        //
        for (int i = 0;i < 99;i++) {
            Thread th = new Thread(new Runnable() {
                @Override
                public void run() {
                    String url = "http://192.168.86.117/cms/rest.htm?lng=116.424236&t=2023-01-03+14%3A18%3A32&method=ddky.cms.homepage50.get&city=%E5%8C%97%E4%BA%AC%E5%B8%82&v=1.0&sign=E89BE943676C644CFA8DBD3096F84F05&shopId=100012&plat=iOS&versionName=6.7.0&lat=39.927717&platform=iOS";
                    //String s = HttpUtil2.doGet(url, null,"UTF-8",false);
                    try {
                        HttpUtil2.doGet(url,null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            th.start();
            System.out.println(th.getId()+" "+th.getName());
        }

    }
}
