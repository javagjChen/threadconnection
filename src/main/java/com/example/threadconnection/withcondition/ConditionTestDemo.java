package com.example.threadconnection.withcondition;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTestDemo {



    public static void main(String[] args) {

        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        Queue<String> msg = new LinkedBlockingQueue<>();
        int maxSize = 5;
        ConditionProducer producer = new ConditionProducer(maxSize,msg,lock,condition);
        ConditionConsumer consumer = new ConditionConsumer(maxSize,msg,lock,condition);
        Thread t1 = new Thread(producer);
        Thread t2 = new Thread(consumer);
        t1.start();
        t2.start();
    }
}
