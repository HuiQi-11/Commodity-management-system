package com.goodbai.demo.mapper;

import com.goodbai.demo.model.ItemCategory;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: huiqi
 * @CreateTime: 2019-11-01 16:38
 */
//商品分类表
@Mapper
@Repository
public interface ItemCategoryMapper {

    ItemCategory findById(ItemCategory itemCategory);

    List<ItemCategory> list(ItemCategory itemCategory);

    List<ItemCategory> list1();

    int count(ItemCategory itemCategory);

    int insert(ItemCategory itemCategory);

    int update(ItemCategory itemCategory);

    int delete(ItemCategory itemCategory);

    int updateStatus(ItemCategory itemCategory);
}
