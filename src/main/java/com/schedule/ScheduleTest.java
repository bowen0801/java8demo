package com.schedule;

import java.util.Timer;
import java.util.TimerTask;

public class ScheduleTest {
    /**
     * 延时任务
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("开始");
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("执行定时任务");
            }
        },10000);
        System.out.println("结束");

        /*new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                try{
                    adAccountApi.chargeCoin(userId,-changeCoin,type,null,remark);
                }catch(Exception e){
                    logger.error("扣除用户叮当币失败！",e);
                    String result = "用户ID:"+userId+"扣除叮当币:"+ changeCoin +"失败，请及时扣除！";
                    //扣除叮当币失败后需要短信通知
                    String sendSingleMessage = MessageUtils.sendMessage(CmsApiConfig.getDdTels().split(","), 1L, new String[]{result}, "cms-awardMinus");
                    logger.info("用户：{}短信发送结果为：{}",userId,sendSingleMessage);
                }
            }
        }, 5000);*/
    }
}
