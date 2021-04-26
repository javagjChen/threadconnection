package com.example.threadconnection;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author chengj17
 * @since 2021/4/1
 */
public class TestDemo {

    public static void main(String[] args) {
        Queue<String> msg = new LinkedBlockingQueue<>();
        int maxSize = 5;
        Producer producer = new Producer(maxSize,msg);
        Consumer consumer = new Consumer(maxSize,msg);
        Thread t1 = new Thread(producer);
        Thread t2 = new Thread(consumer);
        t1.start();
        t2.start();
    }
}
