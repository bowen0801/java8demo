package com.bowen;

import java.util.function.Consumer;

public class ConsumerTest {
    public static void printInfo(String[] strArr, Consumer<String> con1, Consumer<String> con2){
        for (int i = 0; i < strArr.length; i++) {
            //con1.andThen(con1).accept(strArr[i]);
        }
    }

    public static void main(String[] args) {
        String[] strArr = {"费哥哥,男","欢欢姐,女","娇娇妹,女"};
        printInfo(strArr,(message)->{
            System.out.print("姓名:" + message.split(",")[0] + "。  ");
        },(message)->{
            System.out.println("性别:" + message.split(",")[1] + "。");
        });
    }
}
