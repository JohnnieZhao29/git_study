package com.zjydemo.mallstore.mapper;

import com.zjydemo.mallstore.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;

/**
 * @author zjy
 * @version 1.0
 * 用户模块的持久层接口
 */

//@Mapper
public interface UserMapper {
    // 增删改查建议返回行数，判断是否成功
    /**
     * 插入用户数据
     * @param user
     * @return 受影响的行数，根据返回值判断是否执行成功
     */
    Integer insert(User user);


    /**
     * 根据用户名来查询用户数据
     * @param username
     * @return 根据用户名查找，找到则返回user对象，未找到则返回null值
     */
    User findByUsername(String username);


    /**
     * 根据uid查找用户
     * @param uid
     * @return
     */
    User findByUid(Integer uid);


    /**
     * 根据uid修改用户密码，同时更新修改者和修改时间
     * @param uid
     * @param password
     * @param modifiedUser
     * @param modifiedTime
     * @return
     */
    Integer updatePassword(Integer uid,
                           String password,
                           String modifiedUser,
                           Date modifiedTime);

    /**
     * 更新资料
     * @param user
     * @return
     */
    Integer updateInfo(User user);

    Integer updateAvatar(Integer uid, String avatar,
                         String modifiedUser,
                         Date modifiedTime);
}
