package com.example.mapper;

import com.example.pojo.User;

import java.util.List;

public interface UserMapper {

    List<User> queryList();

    User queryById(Integer id);

    User queryByName(String username);

    int save(User user);

    int batchDelete(Integer[] ids);

    int update(User user);

}
