package com.example.service;

import com.example.mapper.UserMapper;
import com.example.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    /*
    主键查询
     */
    public User queryById(Integer id){
        return userMapper.queryById(id);
    }

    /*
    查询所有用户返回列表
     */
    public List<User> findList(){
        return userMapper.queryList();
    }

    public User queryByName(String username){
        return userMapper.queryByName(username);
    }

    public int insertUser(User user){
        return userMapper.save(user);
    }

    public int deleteUser(Integer[] id){
        return userMapper.batchDelete(id);
    }

    public int updateUser(User user){
        return userMapper.update(user);
    }


//    public List<User> findByExample(String username, Integer id){

//        criteria.andEqualTo("username", username);
//        criteria.andEqualTo("id", id);
//
//        List<User> list = userMapper.selectByExample(example);

//        return list;
//    }
}
