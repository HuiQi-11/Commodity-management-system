package com.goodbai.demo.controller;

import com.goodbai.demo.model.Order;
import com.goodbai.demo.model.Stats;
import com.goodbai.demo.redis.DashboardKey;
import com.goodbai.demo.redis.RedisService;
import com.goodbai.demo.service.OrderService;

import com.goodbai.demo.util.RunnableThreadWebCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Author: huiqi
 * @CreateTime: 2019-11-01 14:51
 */
//仪表盘首页
@Controller
public class DashboardController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private RedisService redisService;

    @GetMapping("user/dashboard")
    public String dashboard(Model model, Stats stats) {
        Long mIncome, lastIncome; //当月收入，上个月收入
        Integer curOrderNum, preOrderNum;  //当月订单数，上月订单数
        Integer curRefundOrder, lastRefundOrder; //当月退单数，上月退单数
        Integer orderNum, orderSum;//每天的订单，每天的收入

        //全部加入缓存
        mIncome = redisService.get(DashboardKey.board, "mIncome", Long.class);
        if (mIncome == null) {
            mIncome = orderService.selectCurPayment();
            mIncome = mIncome == null ? 0L : mIncome;
            redisService.set(DashboardKey.board, "mIncome", mIncome);
        }
        lastIncome = redisService.get(DashboardKey.board, "lastIncome", Long.class);
        if (lastIncome == null) {
            lastIncome = orderService.selectLastPayment();
            lastIncome = lastIncome == null ? 0L : lastIncome;
            redisService.set(DashboardKey.board, "lastIncome", lastIncome);
        }

        curOrderNum = redisService.get(DashboardKey.board, "curOrderNum", Integer.class);
        if (curOrderNum == null) {
            curOrderNum = orderService.selectCurOrderNum();
            curOrderNum = curOrderNum == null ? 0 : curOrderNum;
            redisService.set(DashboardKey.board, "curOrderNum", curOrderNum);
        }

        preOrderNum = redisService.get(DashboardKey.board, "preOrderNum", Integer.class);
        if (preOrderNum == null) {
            preOrderNum = orderService.selectLastOrderNum();
            preOrderNum = curOrderNum == null ? 0 : preOrderNum;
            redisService.set(DashboardKey.board, "preOrderNum", preOrderNum);
        }

        curRefundOrder = redisService.get(DashboardKey.board, "curRefundOrder", Integer.class);
        if (curRefundOrder == null) {
            curRefundOrder = orderService.selectCurRefundOrder();
            curRefundOrder = curRefundOrder == null ? 0 : curRefundOrder;
            redisService.set(DashboardKey.board, "curRefundOrder", curRefundOrder);
        }

        lastRefundOrder = redisService.get(DashboardKey.board, "lastRefundOrder", Integer.class);
        if (lastRefundOrder == null) {
            lastRefundOrder = orderService.selectLastRefundOrder();
            lastRefundOrder = lastRefundOrder == null ? 0 : lastRefundOrder;
            redisService.set(DashboardKey.board, "lastRefundOrder", lastRefundOrder);
        }


        int count = RunnableThreadWebCount.addCount("111");
        stats.setPv(count);//访问量
        stats.setOrderNumPer(getPer(curOrderNum, preOrderNum));//月订单数环比
        stats.setmOrderNum(orderService.selectCurOrderNum());//月订单数
        stats.setmIncome(mIncome);//月收入
        stats.setIncomePer(getPer(mIncome, lastIncome));//月收入环比
        stats.setmOrderRefund(orderService.selectCurRefundOrder());
        stats.setmOrderRefundPer(getPer(curRefundOrder, lastRefundOrder));
        //存入model好让前台输出
        model.addAttribute("dashboard", stats);

        List<Integer> data2 = new ArrayList<>();
        List<Integer> data3 = new ArrayList<>();
        Date now = new Date();
        //获取三十天前日期
        Calendar theCa = Calendar.getInstance();
        theCa.setTime(now);
        theCa.add(theCa.DATE, -31);//最后一个数字30可改，30天的意思

        Date temp = new Date();
        Order order = new Order();
        for (int i = 0; i < 31; i++) {
            theCa.add(theCa.DATE, 1);
            temp = theCa.getTime();
            order.setCreateTime(temp);
            //每天的订单数
            orderNum = redisService.get(DashboardKey.board, "orderNum", Integer.class);
            if (orderNum == null) {
                orderNum = orderService.selectDayOrderNum(order);
                orderNum = orderNum == null ? 0 : orderNum;
                redisService.set(DashboardKey.board, "orderNum", orderNum);
            }

            //每天的收入
            orderSum = redisService.get(DashboardKey.board, "orderSum", Integer.class);
            if (orderSum == null) {
                orderSum = orderService.selectDayOrderSum(order);
                orderSum = orderSum == null ? 0 : orderSum;
                redisService.set(DashboardKey.board, "orderSum", orderSum);
            }
            data2.add(orderNum);
            data3.add(orderSum);
        }

        model.addAttribute("data2", data2);
        model.addAttribute("data3", data3);
        return "dashboard";
    }

    //计算增长百分比
    public String getPer(long a, long b) {
        StringBuilder stringBuilder = new StringBuilder();
        if (b != 0) {
            double d = (a - b) / b;
            String s = String.format("%.2f", d);
            stringBuilder.append(s).append("%");
        }
        return stringBuilder.toString();
    }

    //计数
    @RequestMapping(value = "/border/website/count/")
    @ResponseBody
    public int count(@RequestParam("key") String key) {
        return RunnableThreadWebCount.addCount(key);
    }

}
