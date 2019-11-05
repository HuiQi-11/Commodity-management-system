package com.goodbai.demo.mapper;

import com.goodbai.demo.model.Item;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: huiqi
 * @CreateTime: 2019-11-01 16:06
 */
//商品表
@Mapper
@Repository
public interface ItemMapper {

    Item findById(Item item);

    int delete(Item item);

    List<Item> list(Item item);

    List<Item> lists(Item item);

    int count(Item item);

    int insert(Item item);

    int update(Item item);

    List<Item> selectAll();
}
