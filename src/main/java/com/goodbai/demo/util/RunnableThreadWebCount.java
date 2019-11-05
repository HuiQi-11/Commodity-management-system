package com.goodbai.demo.util;

import java.util.HashMap;

/**
 * @Author: huiqi
 * @CreateTime: 2019-11-01 14:33
 */
//统计仪表盘页面访问的人数
public class RunnableThreadWebCount implements Runnable{
    public static HashMap<String,Integer> map = new HashMap<>();
    @Override
    public void run() {
        System.out.println("已经启动计数");
        map.put(new String("111"),0);
    }

    public static void showThread(){

    }

    public static int addCount(String key){
        Integer count = map.get(key);
        if (count == null){
            return 0;
        }
        count++;
        map.put(key,count);
        System.out.println("当前页面访问人数"+count);
        return count;
    }
}
