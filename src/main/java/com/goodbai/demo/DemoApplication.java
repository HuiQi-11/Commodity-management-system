package com.goodbai.demo;

import com.goodbai.demo.util.RunnableThreadWebCount;
import com.goodbai.demo.util.Timers;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;


@SpringBootApplication
@MapperScan("com.goodbai.demo.mapper")
public class DemoApplication {

    public static void main(String[] args) {
        //SpringApplication.run(DemoApplication.class, args);
        SpringApplicationBuilder builder = new SpringApplicationBuilder(DemoApplication.class);
        //设置不使用banner
        builder.bannerMode(Banner.Mode.OFF).run(args);
        //计数线程
        RunnableThreadWebCount runnableThreadWebCount = new RunnableThreadWebCount();
        runnableThreadWebCount.run();
        //计时器线程
        Timers timers = new Timers();
        timers.run();
    }

}
