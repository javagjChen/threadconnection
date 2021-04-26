package com.example.threadconnection;

import java.util.Queue;

/**
 * @author chengj17
 * @since 2021/4/1
 */
public class Consumer implements Runnable{

    private Integer maxSize;

    private Queue<String> msg;

    public Consumer(Integer maxSize, Queue<String> msg) {
        this.maxSize = maxSize;
        this.msg = msg;
    }

    @Override
    public void run() {
        while (true){
            synchronized (msg){
                if (msg.isEmpty()){
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
                System.out.println("消费者消费:" + msg.remove());
                msg.notify();
            }
        }
    }
}
