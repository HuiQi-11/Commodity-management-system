package com.goodbai.demo.service.impl;

import com.goodbai.demo.mapper.ItemCategoryMapper;
import com.goodbai.demo.model.ItemCategory;
import com.goodbai.demo.service.ItemCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: huiqi
 * @CreateTime: 2019-11-01 16:40
 */
//操作tb_category表
@Service
public class ItemCategoryServiceImpl implements ItemCategoryService {

    @Autowired
    private ItemCategoryMapper itemCategoryMapper;

    @Override
    //根据id查询
    public ItemCategory findById(ItemCategory itemCategory) {
        return itemCategoryMapper.findById(itemCategory);
    }

    @Override
    //
    public List<ItemCategory> list(ItemCategory itemCategory) {
        return itemCategoryMapper.list(itemCategory);
    }

    @Override
    //
    public List<ItemCategory> list1() {
        return itemCategoryMapper.list1();
    }

    @Override
    //查询行数
    public int count(ItemCategory itemCategory) {
        return itemCategoryMapper.count(itemCategory);
    }

    @Override
    //插入数据
    public int insert(ItemCategory itemCategory) {
        return itemCategoryMapper.insert(itemCategory);
    }

    @Override
    //更新表中数据
    public int update(ItemCategory itemCategory) {
        return itemCategoryMapper.update(itemCategory);
    }

    @Override
    //删除表中的数据根据id
    public int delete(ItemCategory itemCategory) {
        return itemCategoryMapper.delete(itemCategory);
    }

    @Override
    //更新商品的状态:1(正常),2(删除)
    public int updateStatus(ItemCategory itemCategory) {
        return itemCategoryMapper.updateStatus(itemCategory);
    }
}
