package test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by hukai on 2016/9/22.
 */
public class CountDownLatchTest1 implements Runnable{
//    final AtomicInteger number = new AtomicInteger();
    int i=0;
    volatile boolean bol = false;

    @Override
    public void run() {
//        System.out.println("====="+number.getAndIncrement());
        System.out.println(i++);
        synchronized (this) {
            try {
                if (!bol) {
                    System.out.println(bol);
                    bol = true;
                    Thread.sleep(2000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            System.out.println("并发数量为" + number.intValue());
            System.out.println("并发数量为" + i);
        }
    }

    public static void main(String[] args) {
        ExecutorService pool = Executors. newCachedThreadPool();
        CountDownLatchTest1 test = new CountDownLatchTest1();
        for (int i=0;i<9;i++) {
            pool.execute(test);
        }

//        ExecutorService executorService = Executors.newFixedThreadPool(10);
//
//        executorService.execute(new Runnable() {
//            public void run() {
//                System.out.println("Asynchronous task");
//            }
//        });
//
//        executorService.shutdown();
    }
}