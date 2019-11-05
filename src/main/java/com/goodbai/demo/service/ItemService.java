package com.goodbai.demo.service;

import com.goodbai.demo.model.Item;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: huiqi
 * @CreateTime: 2019-11-01 16:09
 */
@Service
public interface ItemService {

    Item findById(Item item);

    int delete(Item item);

    List<Item> list(Item item);

    List<Item> lists(Item item);

    int count(Item item);

    int insert(Item item);

    int update(Item item);

    List<Item> selectAll();
}
