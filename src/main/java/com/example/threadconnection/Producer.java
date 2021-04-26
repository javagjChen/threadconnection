package com.example.threadconnection;

import java.util.Queue;

/**
 * @author chengj17
 * @since 2021/4/1
 */
public class Producer implements Runnable {

    private Integer maxSize;

    private Queue<String> msg;

    public Producer(Integer maxSize, Queue<String> msg) {
        this.maxSize = maxSize;
        this.msg = msg;
    }

    @Override
    public void run() {
        int i = 0;
        while (true){
            i++;
            synchronized (msg){
                if (msg.size()== maxSize){
                    try {
                        msg.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("生成者生产消息:" + i);
                msg.add("消息" + i);
                msg.notify();
            }
        }
    }
}
