package com.jade.system.biz.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

public class CallableDemo implements Callable<List<Integer>> {

    private final CountDownLatch countDownLatch;
    int i;

    public CallableDemo(int i, CountDownLatch countDownLatch) {
        this.i = i;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public List<Integer> call() {
        List<Integer> list = new ArrayList<>();
        list.add(i + 10);
        countDownLatch.countDown();
        return list;
    }
}
