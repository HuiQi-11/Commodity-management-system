package com.goodbai.demo.service;

import com.goodbai.demo.model.ReItem;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: huiqi
 * @CreateTime: 2019-11-01 16:20
 */
@Service
public interface ReItemService {

    int deleteByPrimaryKey(int id);

    int insert(ReItem record);

    ReItem selectByPrimaryKey(int id);

    List<ReItem> selectAll();

    int updateByPrimaryKey(ReItem record);

    int count(ReItem record);
}
