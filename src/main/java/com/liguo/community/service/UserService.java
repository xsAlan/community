package com.liguo.community.service;

import com.liguo.community.mapper.UserMapper;
import com.liguo.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User getByToken(String token){
        return userMapper.getByToken(token);
    }
}
