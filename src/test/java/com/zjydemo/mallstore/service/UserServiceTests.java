package com.zjydemo.mallstore.service;


import com.zjydemo.mallstore.entity.User;
import com.zjydemo.mallstore.mapper.UserMapper;
import com.zjydemo.mallstore.service.ex.ServiceException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import javax.sql.rowset.serial.SerialException;

/**
 * @author zjy
 * @version 1.0
 */

// 标注当前类是测试类，不会随同项目一起打包发送
@SpringBootTest

public class UserServiceTests {

    @Autowired
    // 接口不能直接创建Bean
    private IUserService userService;

    /**
     * 1. 必须TEST注解
     * 2. 必须void 和 public
     * 3. 形参表为空
     */
    @Test
    public void reg(){
        try {
            User user = new User();
            user.setUsername("zw");
            user.setPassword("12138");
            userService.reg(user);
            System.out.println("ok");
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }


    @Test
    public void login(){
        try {
            String username = "zjy";
            String password = "123";
            User login = userService.login(username, password);
            System.out.println("ok" + "\n" + login);
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void changePassword(){
        try {
            userService.changePassword(103,"zw",
                    "12138", "123");
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }
    }


    @Test
    public void changeInfo(){
        User user = new User();
        user.setPhone("110");
        user.setEmail("zwshd@163.com");
        user.setGender(0);
        userService.changeInfo(user,"zw",103);
    }

    @Test
    public void changeAvatar(){
        userService.changeAvatar(103, "/upload/newP.phg","zw");
    }

}
