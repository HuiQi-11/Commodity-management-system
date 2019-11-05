package com.goodbai.demo.service;

import com.goodbai.demo.model.Order;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: huiqi
 * @CreateTime: 2019-11-01 12:27
 */
@Service
public interface OrderService {
    int deleteByPrimaryKey(String orderId);

    int insert(Order record);

    Order selectByPrimaryKey(String orderId);

    List<Order> list(Order order);

    List<Order> listRefund(Order order);

    List<Order> selectAll();

    Integer selectCurOrderNum();

    Integer selectLastOrderNum();

    Long selectCurPayment();

    Long selectLastPayment();

    Integer selectCurRefundOrder();

    Integer selectLastRefundOrder();

    Integer selectDayOrderSum(Order order);

    Integer selectDayOrderNum(Order order);

    Integer updateByPrimaryKey(Order record);
}
