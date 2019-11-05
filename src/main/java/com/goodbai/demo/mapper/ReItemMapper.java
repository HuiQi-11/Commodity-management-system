package com.goodbai.demo.mapper;

import com.goodbai.demo.model.ReItem;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: huiqi
 * @CreateTime: 2019-11-01 16:19
 */
//商品回收表
@Mapper
@Repository
public interface ReItemMapper {

    int deleteByPrimaryKey(int id);

    int insert(ReItem record);

    ReItem selectByPrimaryKey(int id);

    List<ReItem> selectAll();

    int updateByPrimaryKey(ReItem record);

    int count(ReItem record);
}
