package com.goodbai.demo.redis;

//仪表盘需要缓冲的字段
public abstract class BasePrefix implements KeyPrefix {

    //有效期
    private int expireSeconds;

    //前缀
    private String prefix;

    public BasePrefix(String prefix){
        this(0, prefix);//默认0代表永不过期
    }

    public BasePrefix(int expireSeconds, String prefix){
        this.expireSeconds = expireSeconds;
        this.prefix = prefix;
    }

    @Override
    public int expireSeconds() {
        return expireSeconds;
    }

    @Override
    public String getPrefix() {
        String className = getClass().getSimpleName();//拿到参数类类名
        return className + ":" + prefix;
    }
}
