package com.goodbai.demo.service.impl;

import com.goodbai.demo.mapper.ReItemMapper;
import com.goodbai.demo.model.ReItem;
import com.goodbai.demo.service.ReItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: huiqi
 * @CreateTime: 2019-11-01 16:24
 */
//操作tb_re_item表
@Service
public class ReItemServiceImpl implements ReItemService {

    @Autowired
    private ReItemMapper reItemMapper;

    @Override
    //根据id删除
    public int deleteByPrimaryKey(int id) {
        return reItemMapper.deleteByPrimaryKey(id);
    }

    @Override
    //插入数据
    public int insert(ReItem record) {
        return reItemMapper.insert(record);
    }

    @Override
    //根据id查找
    public ReItem selectByPrimaryKey(int id) {
        return reItemMapper.selectByPrimaryKey(id);
    }

    @Override
    //
    public List<ReItem> selectAll() {
        return reItemMapper.selectAll();
    }

    @Override
    //更新表中数据
    public int updateByPrimaryKey(ReItem record) {
        return reItemMapper.updateByPrimaryKey(record);
    }

    @Override
    //数据的行数
    public int count(ReItem record) {
        return reItemMapper.count(record);
    }
}
