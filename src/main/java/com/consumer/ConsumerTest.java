package com.consumer;

import java.util.function.Consumer;

public class ConsumerTest {
    /**
     * https://blog.csdn.net/qq_32879029/article/details/104272714
     * 当我们看到“Consumer”和“Supplier”这两个接口的时候，会有一种亲切感：这不就是老生常谈的生产者和消费者吗？而接口的设计也正如其名，一个没有入参，一个没有出参。
     * Consumer接口可以用来作为回调，比如发送一些消息：
     */
    public static void sendMessage(String msg, Consumer< String > consumer) {
            consumer.accept(msg);
        }

        public static void main(String[] args) {
            Consumer<String> consumer = (obj) -> {
                System.out.println(obj);
                System.out.println("调用接口打印");
            };
            sendMessage("打印信息", consumer);
        }
}
