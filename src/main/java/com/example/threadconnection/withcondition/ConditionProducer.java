package com.example.threadconnection.withcondition;

import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class ConditionProducer implements Runnable{
    private Integer maxSize;

    private Queue<String> msg;

    Lock lock;

    Condition condition;

    public ConditionProducer(Integer maxSize, Queue<String> msg, Lock lock, Condition condition) {
        this.maxSize = maxSize;
        this.msg = msg;
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {
        int i = 0;
        while (true){
            i++;
            lock.lock();
            if (msg.size()== maxSize){
                try {
                    condition.await();
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
            condition.signal();
            lock.unlock();
        }
    }
}
