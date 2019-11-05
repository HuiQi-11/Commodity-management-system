package com.goodbai.demo.service.impl;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.goodbai.demo.mapper.OrderItemMapper;
import com.goodbai.demo.model.OrderItem;
import com.goodbai.demo.service.OrderItemService;
import com.sun.mail.imap.protocol.ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: huiqi
 * @CreateTime: 2019-11-05 10:06
 */
@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Override
    public int deleteByPrimaryKey(String id) {
        return orderItemMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(OrderItem record) {
        return orderItemMapper.insert(record);
    }

    @Override
    public OrderItem selectByPrimaryKey(String id) {
        return orderItemMapper.selectByPrimaryKey(id);
    }

    @Override
    public OrderItem selectByPrimaryOrderKey(String orderId) {
        return orderItemMapper.selectByPrimaryOrderKey(orderId);
    }

    @Override
    public List<OrderItem> selectAll() {
        return orderItemMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(OrderItem record) {
        return orderItemMapper.updateByPrimaryKey(record);
    }
}
