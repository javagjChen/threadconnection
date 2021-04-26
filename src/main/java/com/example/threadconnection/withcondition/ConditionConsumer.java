package com.example.threadconnection.withcondition;

import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class ConditionConsumer implements Runnable{

    private Integer maxSize;

    private Queue<String> msg;

    Lock lock;

    Condition condition;

    public ConditionConsumer(Integer maxSize, Queue<String> msg, Lock lock, Condition condition) {
        this.maxSize = maxSize;
        this.msg = msg;
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {

        while (true){
            lock.lock();
            if (msg.isEmpty()){
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
            System.out.println("消费者消费:" + msg.remove());
            condition.signal();
            lock.unlock();
        }


    }
}
