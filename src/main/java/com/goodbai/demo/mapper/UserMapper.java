package com.goodbai.demo.mapper;

import com.goodbai.demo.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * @Author: huiqi
 * @CreateTime: 2019-10-31 18:00
 */
//用户表
@Mapper
@Repository
public interface UserMapper {

    User selectByNameAndPwd(User user);

    int insert(User user);

    int update(User user);

    int selectIsName(User user);

    String selectPwdByName(User user);
}
