package com.goodbai.demo.redis;


public class DashboardKey extends BasePrefix {

    public static final int BOARD_EXPIRE = 3600*24 *2;//默认过期时间两天

    //防止被外面实例化

    private DashboardKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }


    //需要缓存的字段

    public static DashboardKey board = new DashboardKey(BOARD_EXPIRE,"board");

}
