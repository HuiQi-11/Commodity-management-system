package com.goodbai.demo.service.impl;

import com.goodbai.demo.mapper.OrderMapper;
import com.goodbai.demo.model.Order;
import com.goodbai.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: huiqi
 * @CreateTime: 2019-11-01 12:28
 */
//操作tb_order表
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    //根据id删除
    public int deleteByPrimaryKey(String orderId) {
        return orderMapper.deleteByPrimaryKey(orderId);
    }

    @Override
    //插入
    public int insert(Order record) {
        return orderMapper.insert(record);
    }

    @Override
    //根据id查找
    public Order selectByPrimaryKey(String orderId) {
        return orderMapper.selectByPrimaryKey(orderId);
    }

    @Override
    //获取所有数据
    public List<Order> list(Order order) {
        return orderMapper.list(order);
    }

    @Override
    //根据是否退款（isRefund）查询
    public List<Order> listRefund(Order order) {
        return orderMapper.listRefund(order);
    }

    @Override
    //选择所有
    public List<Order> selectAll() {
        return orderMapper.selectAll();
    }

    @Override
    //
    public Integer selectCurOrderNum() {
        return orderMapper.selectCurOrderNum();
    }

    @Override
    //
    public Integer selectLastOrderNum() {
        return orderMapper.selectLastOrderNum();
    }

    @Override
    //根据已付款的产品输出价格
    public Long selectCurPayment() {
        return orderMapper.selectCurPayment();
    }

    @Override
    //
    public Long selectLastPayment() {
        return orderMapper.selectLastPayment();
    }

    @Override
    //根据退款情况输出
    public Integer selectCurRefundOrder() {
        return orderMapper.selectCurRefundOrder();
    }

    @Override
    //
    public Integer selectLastRefundOrder() {
        return orderMapper.selectLastRefundOrder();
    }

    @Override
    //
    public Integer selectDayOrderSum(Order order) {
        return orderMapper.selectDayOrderSum(order);
    }

    @Override
    //
    public Integer selectDayOrderNum(Order order) { return orderMapper.selectDayOrderNum(order); }

    @Override
    //更新用户数据
    public Integer updateByPrimaryKey(Order record) {
        return orderMapper.updateByPrimaryKey(record);
    }
}
