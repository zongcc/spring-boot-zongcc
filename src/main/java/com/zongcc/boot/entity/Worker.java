package com.zongcc.boot.entity;

import java.util.concurrent.CountDownLatch;

/**
 * Created by chunchengzong on 2017-01-10.
 */
public class Worker implements Runnable {

    private CountDownLatch countDownLatch;
    private String name;

    public Worker(CountDownLatch countDownLatch,String name) {
        this.countDownLatch = countDownLatch;
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("the thread "+name+" is starting .......");
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }finally {
            countDownLatch.countDown();
        }
        System.out.println("the thread "+name+" is end .......");
    }
}
