package com.example.mapper;

import com.example.pojo.User;
import com.example.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;

    @Test
    public void queryById(){
        User user = userMapper.queryById(18);
        System.out.println("user = " + user);
    }

//    @Test
//    public void queryByExample(){
//        List<User> user = userService.findByExample("刘亦菲",18);
//        user.forEach(System.err::println);
//    }
}