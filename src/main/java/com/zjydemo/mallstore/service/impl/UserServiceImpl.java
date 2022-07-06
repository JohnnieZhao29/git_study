package com.zjydemo.mallstore.service.impl;

import com.zjydemo.mallstore.entity.User;
import com.zjydemo.mallstore.mapper.UserMapper;
import com.zjydemo.mallstore.service.IUserService;
import com.zjydemo.mallstore.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

/**
 * @author zjy
 * @version 1.0
 */

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;


    /**
     * 核心是调用dao层的方法
     * @param user 传入User对象
     */
    @Override
    public void reg(User user) {
        String username = user.getUsername();
        User res = userMapper.findByUsername(username);
        if(res != null){
            throw new UsernameDuplicatedException("用户名被占用");
        }
        // 密码加密处理
        String oldPassword = user.getPassword();
        // 随机生成salt
        // salt + password + salt --> md5
        String salt = UUID.randomUUID().toString().toUpperCase();
        user.setSalt(salt);
        String md5Password = getMD5Password(oldPassword, salt);
        user.setPassword(md5Password);
        // 补全数据

        // 主键值人为清空
        user.setIsDelete(0);
        // 补全日志
        user.setCreatedUser(user.getUsername());
        user.setModifiedUser(user.getUsername());
        Date date = new Date();
        user.setCreatedTime(date);
        user.setModifiedTime(date);
        Integer rows = userMapper.insert(user);
        if(rows != 1){
            throw new InsertException("用户注册过程中产生异常");
        }
    }

    /**
     * MD5生成
     * @param oldPWD
     * @param salt
     * @return
     */
    private String getMD5Password(String oldPWD, String salt){
        for (int i = 0; i < 3; i++) {
            oldPWD = DigestUtils.md5DigestAsHex((salt + oldPWD + salt).getBytes()).toUpperCase();
        }
        // md5加密算法
        return oldPWD;
    }

    @Override
    public User login(String username, String password) {
        User res = userMapper.findByUsername(username);
        if(res == null ){
            throw new UserNotFoundException("用户不存在");
        }
        if(res.getIsDelete()==1){
            throw new UserNotFoundException("用户已被删除");
        }
        String md5Password = getMD5Password(password, res.getSalt());
        if(!res.getPassword().equals(md5Password)){
            throw new PasswordNotMatchException("密码不正确");
        }
        User user = new User();
        user.setUid(res.getUid());
        user.setUsername(res.getUsername());
//        user.setPassword(res.getPassword());
//        user.setPhone(res.getPhone());
        user.setAvatar(res.getAvatar());

        return user;
    }

    @Override
    public void changePassword(Integer uid, String username, String oldPassword, String newPassword) {
        User res = userMapper.findByUid(uid);
        if(res == null || res.getIsDelete() == 1){
            throw new UserNotFoundException("用户不存在");
        }

        String oldmd5Password = getMD5Password(oldPassword, res.getSalt());
        if(!oldmd5Password.equals(res.getPassword())){
            throw new PasswordNotMatchException("原密码错误");
        }

        // 到这里才执行mysql数据库操作
        String newMD5Password = getMD5Password(newPassword, res.getSalt());
        Integer rows = userMapper.updatePassword(uid, newMD5Password, username, new Date());
        if(rows != 1){
            throw new UpdateException("更新错误");
        }
        System.out.println("密码更新成功");

    }

    @Override
    public User getByUid(Integer uid) {
        User user = userMapper.findByUid(uid);
        if(user == null || user.getIsDelete() == 1){
            throw new UserNotFoundException("用户不存在");
        }
        User res = new User();
        res.setUsername(user.getUsername());
        res.setPhone(user.getPhone());
        res.setEmail(user.getEmail());
        res.setGender(user.getGender());
        return res;
    }

    /**
     * uid和username是session传入
     * @param user
     * @param username
     * @param uid
     */
    @Override
    public void changeInfo(User user, String username, Integer uid) {

        User userByUid = userMapper.findByUid(uid);
        if(userByUid == null || userByUid.getIsDelete() == 1){
            throw new UserNotFoundException("用户不存在");
        }
        user.setUid(uid);
//        user.setUsername(username);
        user.setModifiedUser(username);
        user.setModifiedTime(new Date());

        Integer rows = userMapper.updateInfo(user);
        if(rows != 1){
            throw new UpdateException("更新错误，请重复尝试");
        }


    }

    @Override
    public void changeAvatar(Integer uid, String avatar, String username) {
        User byUid = userMapper.findByUid(uid);
        if(byUid == null || byUid.getIsDelete() == 1){
            throw new UserNotFoundException("用户不存在");
        }

        Integer integer = userMapper.updateAvatar(uid, avatar, username, new Date());
        if(integer != 1){
            throw new UpdateException("更新头像失败");
        }
    }


}
