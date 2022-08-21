package com.jade.system.biz.demo;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class MultiThread {

    @SneakyThrows
    public List<Long> enhance(int i) {
        final CountDownLatch countDownLatch = new CountDownLatch(i);
        ExecutorService pool = Executors.newCachedThreadPool();
        List<Long> list = new ArrayList<>();
        for (long j = 0; j < i; j++) {
            long finalJ = j;
            Runnable runnable = () -> {
                list.add(finalJ);
                countDownLatch.countDown();
            };
            pool.execute(runnable);
        }
        countDownLatch.await();
        pool.shutdown();
        return list;
    }

    @SneakyThrows
    public List<Integer> enhance(int i, List<Integer> res) {
        final CountDownLatch countDownLatch = new CountDownLatch(i);
        ExecutorService pool = Executors.newCachedThreadPool();
        ArrayList<Future<List<Integer>>> list = new ArrayList<>();
        for (int j = 0; j < i; j++) {
            CallableDemo callableDemo = new CallableDemo(j, countDownLatch);
            list.add(pool.submit(callableDemo));
        }
        countDownLatch.await();
        list.forEach(l -> {
            try {
                res.addAll(l.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
        return res;
    }
}
