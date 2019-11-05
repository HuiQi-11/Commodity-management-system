package com.goodbai.demo.service.impl;

import com.goodbai.demo.mapper.ItemMapper;
import com.goodbai.demo.model.Item;
import com.goodbai.demo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: huiqi
 * @CreateTime: 2019-11-01 16:11
 */
//操作tb_item表
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemMapper itemMapper;

    @Override
    //根据id查询
    public Item findById(Item item) {
        return itemMapper.findById(item);
    }

    @Override
    //根据id删除
    public int delete(Item item) {
        return itemMapper.delete(item);
    }

    @Override
    //
    public List<Item> list(Item item) {
        return itemMapper.list(item);
    }

    @Override
    //
    public List<Item> lists(Item item) {
        return itemMapper.lists(item);
    }

    @Override
    //
    public int count(Item item) {
        return itemMapper.count(item);
    }

    @Override
    //插入数据
    public int insert(Item item) {
        return itemMapper.insert(item);
    }

    @Override
    //更新数据
    public int update(Item item) {
        return itemMapper.update(item);
    }

    @Override
    //全选商品
    public List<Item> selectAll() {
        return itemMapper.selectAll();
    }
}
