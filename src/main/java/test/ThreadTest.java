package test;

/**
 * Created by hukai on 2016/9/18.
 */
// 创建一个新的线程
class NewThread implements Runnable {
    Thread t;
    NewThread() {
        // 创建第二个新线程
        t = new Thread(this, "Demo Thread");
        System.out.println("Child thread: " + t);
        t.start(); // 开始线程
    }

    // 第二个线程入口
    public void run() {
        try {
            for(int i = 5; i > 0; i--) {
                System.out.println("Child Thread: " + i);
                // 暂停线程
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            System.out.println("Child interrupted.");
        }
        System.out.println("Exiting child thread.");
    }
}

class myFirstThread implements Runnable{

    @Override
    public void run() {
        try{
            for(int i=0;i<10;i++){
                System.out.println("qqqqqqqqqqqqqqqqqq");
                Thread.sleep(5000);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
class mySecondThread extends Thread{
    @Override
    public void run(){
        try{
            for(int i=0;i<5;i++){
                System.out.println("wwwwwwwwwwwwwwww");
                Thread.sleep(2000);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
public class ThreadTest {
    public static void main(String args[]) {
        /**
         * 1
         */
//        new NewThread(); // 创建一个新线程
//        try {
//            for(int i = 5; i > 0; i--) {
//                System.out.println("Main Thread: " + i);
//                Thread.sleep(100);
//            }
//        } catch (InterruptedException e) {
//            System.out.println("Main thread interrupted.");
//        }
//        System.out.println("Main thread exiting.");
        /**
         * 2
         */
        Thread thread1 = new Thread(new myFirstThread());
        thread1.start();

        mySecondThread mySecondThread = new mySecondThread();
        mySecondThread.start();
    }
}
