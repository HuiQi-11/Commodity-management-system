package com.goodbai.demo.util;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @Author: huiqi
 * @CreateTime: 2019-11-01 14:41
 */
//Timer，一般用来做延时任务或者循环定时执行的任务。
//使用Timer的时候，必须要有一个TimerTask去执行任务，这是一个线程，它实现了Runnable接口，
//run方法里面就是线程需要做的，也是我们自己定义的。
public class Timers implements Runnable {

    @Override
    public void run() {
        System.out.println("计数程序启动");
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                RunnableThreadWebCount.showThread();
            }
        };
        Timer timer = new Timer();
        //第二个参数是延时（1秒），第三个参数是间隔时间（1秒）
        timer.scheduleAtFixedRate(task,1000,1000);
    }
}
