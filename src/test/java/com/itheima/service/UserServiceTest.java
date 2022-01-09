package com.itheima.service;

import com.itheima.mapper.UserMapper;
import com.itheima.pojo.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {


    private UserMapper mockUserMapper;

    @Before
    public void setUpMockObj() throws Exception{
        mockUserMapper = mock(UserMapper.class);
        when(mockUserMapper.selectByPrimaryKey(1)).thenReturn(User.builder().id(1L).name("User1").build());
        when(mockUserMapper.selectOne(isA(User.class))).thenReturn(User.builder().id(1L).name("User1").build());
        when(mockUserMapper.updateByPrimaryKey(isA(User.class))).thenReturn(2);

        userService = new UserService();
        userService.setUserMapper(mockUserMapper);
    }

    @Autowired
    private UserService userService;

    @Test
    public void testUpdate() {
        Integer r = userService.updateNameById(1L, "ylj");
        System.out.println(r);
        verify(mockUserMapper, times(2)).updateByPrimaryKey(isA(User.class));
        ArrayList<Object> list = new ArrayList<>();
        list.clear();
    }

    @Test
    public void queryById() {
        User user = userService.queryById(8L);
        System.out.println("user = " + user);
    }

    @Test
    public void saveUser() {
//        User user = new User();
//        user.setUserName("test2");
//        user.setName("test2");
//        user.setAge(13);
//        user.setPassword("123456");
//        user.setSex(1);
//        user.setCreated(new Date());
//        userService.saveUser(user);
    }
}