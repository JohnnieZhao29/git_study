package com.zjydemo.mallstore.controller;

import com.zjydemo.mallstore.controller.ex.FileEmptyException;
import com.zjydemo.mallstore.controller.ex.FileSizeException;
import com.zjydemo.mallstore.controller.ex.FileTypeException;
import com.zjydemo.mallstore.controller.ex.FileUploadIOException;
import com.zjydemo.mallstore.entity.User;
import com.zjydemo.mallstore.service.IUserService;
import com.zjydemo.mallstore.service.ex.InsertException;

import com.zjydemo.mallstore.service.ex.ServiceException;
import com.zjydemo.mallstore.service.ex.UsernameDuplicatedException;
import com.zjydemo.mallstore.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

/**
 * @author zjy
 * @version 1.0
 */

@RestController // 等效Controller + ResponseBody
@RequestMapping("users")
public class UserController extends BaseController{

    @Autowired
    private IUserService userService;

    @RequestMapping("test")
    public void test(){
        System.out.println("test");
    }

    @RequestMapping("reg")
//    @RequestMapping("/reg")
//    @ResponseBody // 表示将JSON数据写入响应包中返回给前端。
    public JsonResult<Void> reg(User user){
        userService.reg(user);
        return new JsonResult<>(OK);

    }

    @RequestMapping("login")
    // 形参名和前端传入相同
    public JsonResult<User> login(String username, String password, HttpSession session){
        User data = userService.login(username, password);

        // 在服务器端传来的session，全局session
        session.setAttribute("uid",data.getUid());
        session.setAttribute("username",data.getUsername());

        System.out.println(getuidFromSession(session));
        System.out.println(getUsernameFromSession(session));

        return new JsonResult<User>(OK, data);
    }

    @RequestMapping("change_password")
    public JsonResult<Void> changePassword(String oldPassword,
                                           String newPassword,
                                           HttpSession session){
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.changePassword(uid,username,oldPassword,newPassword);
        return new JsonResult<>(OK);
    }

    @RequestMapping("get_by_uid")
    public JsonResult<User> getByUid(HttpSession session){

        User byUid = userService.getByUid(getuidFromSession(session));
        return new JsonResult<>(OK, byUid);
    }

    @RequestMapping("change_info")
    public JsonResult<Void> changeInfo(User user, HttpSession session){
        userService.changeInfo(user,
                getUsernameFromSession(session),
                getuidFromSession(session));
        return new JsonResult<>(OK);
    }

    /** 设置上传文件的最大值 */
    public static final int AVATAR_MAX_SIZE = 20 * 1024 * 1024;

    /** 设置可以接受的文件格式 */
    public static final List<String> AVATAR_TYPE = new ArrayList<>();

    static {
        AVATAR_TYPE.add("image/jpeg");
        AVATAR_TYPE.add("image/png");
        AVATAR_TYPE.add("image/bmp");
        AVATAR_TYPE.add("image/gif");
    }

    /**
     * MultipartFile是SpringMVC封装的接口
     * 可以获取文件的数据，任何类型
     * @param session
     * @param file
     * @return
     */
    @RequestMapping("change_avatar")
    public JsonResult<String> changeAvatar(HttpSession session,
                                           // 避免前后端名字不一致
                                           @RequestParam("file") MultipartFile file){
        System.out.println(file.getContentType());
        if(file.isEmpty()){
            throw new FileEmptyException("文件为空");
        }
        if(file.getSize() > AVATAR_MAX_SIZE){
            throw new FileSizeException("文件大小超出限制");
        }
        if(!AVATAR_TYPE.contains(file.getContentType())){
            throw new FileTypeException("文件格式不符");
        }

        // 文件放在某个文件夹下面，上传的文件.../upload/文件.png
//        String parent = session.getServletContext().getRealPath("upload");
        String parent = "D:/Work_Space/for java/MallStore/upload";
        // File对象指向这个路径，file是否存在
        File dir = new File(parent);
        // 检测目录是否存在
        if(!dir.exists()){
            dir.mkdirs();//创建目录
        }
        // 获取文件名称，使用UUID工具类，生成一个新的字符串作为文件名
        String originalFilename = file.getOriginalFilename();
        System.out.println(originalFilename);
        int index = originalFilename.lastIndexOf(".");
        String suffix = originalFilename.substring(index);
        String filename = UUID.randomUUID().toString().toUpperCase() + suffix;
        File dest = new File(dir, filename);
        try {
            file.transferTo(dest); // 将file文件中的数据写入到dest文件中，后缀要一致
        } catch (IOException e) {
            throw new FileUploadIOException("文件读写异常");
        }

        String avatar = "/upload/" + filename;
        userService.changeAvatar(getuidFromSession(session),
                avatar,
                getUsernameFromSession(session)
                );
        return new JsonResult<>(OK, avatar);
    }

}
