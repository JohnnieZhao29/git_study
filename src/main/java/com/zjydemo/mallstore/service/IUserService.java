package com.zjydemo.mallstore.service;

import com.zjydemo.mallstore.entity.User;

/**
 * @author zjy
 * @version 1.0
 * 用户模块的业务层接口
 */

public interface IUserService {

    /**
     * 用户注册的方法
     * @param user 传入User对象
     */
    public void reg(User user);

    /**
     * 用户登录方法
     * 传入用户名和密码
     * @param username
     * @param password
     * @return 返回一个user对象
     */
    public User login(String username, String password);

    public void changePassword(Integer uid, String username,
                               String oldPassword, String newPassword);

    public User getByUid(Integer uid);

    public void changeInfo(User user, String username, Integer uid);

    /**
     * 修改用户的头像
     * @param uid 用户id
     * @param avatar 用户头像路径
     * @param username 用户名称
     */
    public void changeAvatar(Integer uid, String avatar,
                             String username);
}
