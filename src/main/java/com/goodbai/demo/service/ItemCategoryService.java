package com.goodbai.demo.service;

import com.goodbai.demo.model.ItemCategory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: huiqi
 * @CreateTime: 2019-11-01 16:40
 */
@Service
public interface ItemCategoryService {
    ItemCategory findById(ItemCategory itemCategory);

    List<ItemCategory> list(ItemCategory itemCategory);

    List<ItemCategory> list1();

    int count(ItemCategory itemCategory);

    int insert(ItemCategory itemCategory);

    int update(ItemCategory itemCategory);

    int delete(ItemCategory itemCategory);

    int updateStatus(ItemCategory itemCategory);
}
