package com.zjydemo.mallstore.mapper;

import com.zjydemo.mallstore.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

/**
 * @author zjy
 * @version 1.0
 */

// 标注当前类是测试类，不会随同项目一起打包发送
@SpringBootTest

public class UserMapperTests {

    @Autowired
    // 接口不能直接创建Bean
    private UserMapper userMapper;

    /**
     * 1. 必须TEST注解
     * 2. 必须void 和 public
     * 3. 形参表为空
     */
    @Test
    public void insert(){
        User user = new User();
        user.setUsername("iza2y");
        user.setPassword("12345");
        Integer rows = userMapper.insert(user);
        System.out.println(rows);
    }


    @Test
    public void findByName(){
        User zjy = userMapper.findByUsername("zjy");
        System.out.println(zjy);
    }




    @Test
    public void findByUid() {
        userMapper.updatePassword(30,"111111",
                "admin",new Date());
    }


    @Test
    public void updatePassword() {
        System.out.println(userMapper.findByUid(30));

    }

    @Test
    public void updateInfo(){
        User user = new User();
        user.setUid(103);
        user.setPhone("12138");
        user.setEmail("zhangweishihundan@163.com");
        user.setGender(1);
        user.setModifiedUser(userMapper.findByUid(103).getUsername());
        user.setModifiedTime(new Date());
        userMapper.updateInfo(user);
    }

    @Test
    public void updateAvatar(){
        userMapper.updateAvatar(103,"/upload/avator.jpg", "zw",new Date());

    }

}
