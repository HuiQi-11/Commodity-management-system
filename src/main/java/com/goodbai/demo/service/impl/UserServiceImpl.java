package com.goodbai.demo.service.impl;

import com.goodbai.demo.mapper.UserMapper;
import com.goodbai.demo.model.User;
import com.goodbai.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: huiqi
 * @CreateTime: 2019-10-31 18:08
 */
//操作tb_user表
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /*查询用户密码*/
    @Override
    public User selectByNameAndPwd(User user) {
        return userMapper.selectByNameAndPwd(user);
    }

    /*添加用户*/
    @Override
    public int insert(User user) {
        return userMapper.insert(user);
    }

    /*更新用户*/
    @Override
    public int update(User user) {
        return userMapper.update(user);
    }

    /*通过用户名查询*/
    @Override
    public int selectIsName(User user) {
        return userMapper.selectIsName(user);
    }

    /*通过用户名和邮箱查询密码*/
    @Override
    public String selectPwdByName(User user) {
        return userMapper.selectPwdByName(user);
    }
}