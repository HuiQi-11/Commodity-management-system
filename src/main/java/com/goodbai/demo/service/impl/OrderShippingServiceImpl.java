package com.goodbai.demo.service.impl;

import com.goodbai.demo.mapper.OrderShippingMapper;
import com.goodbai.demo.model.OrderShipping;
import com.goodbai.demo.service.OrderShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: huiqi
 * @CreateTime: 2019-11-05 10:29
 */
@Service
public class OrderShippingServiceImpl implements OrderShippingService {

    @Autowired
    private OrderShippingMapper orderShippingMapper;

    @Override
    public int deleteByPrimaryKey(String orderId) {
        return orderShippingMapper.deleteByPrimaryKey(orderId);
    }

    @Override
    public int insert(OrderShipping record) {
        return orderShippingMapper.insert(record);
    }

    @Override
    public OrderShipping selectByPrimaryKey(String orderId) {
        return orderShippingMapper.selectByPrimaryKey(orderId);
    }

    @Override
    public List<OrderShipping> selectAll() {
        return orderShippingMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(OrderShipping record) {
        return orderShippingMapper.updateByPrimaryKey(record);
    }
}
