package com.goodbai.demo.service;

import com.goodbai.demo.model.User;
import org.springframework.stereotype.Service;

/**
 * @Author: huiqi
 * @CreateTime: 2019-10-31 18:07
 */
@Service
public interface UserService {
    User selectByNameAndPwd(User user);

    int insert(User user);

    int update(User user);

    int selectIsName(User user);

    String selectPwdByName(User user);
}
