package com.zjydemo.mallstore.controller;

import com.zjydemo.mallstore.controller.ex.*;
import com.zjydemo.mallstore.entity.User;
import com.zjydemo.mallstore.service.ex.*;
import com.zjydemo.mallstore.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;
import java.net.PasswordAuthentication;

/**
 * @author zjy
 * @version 1.0
 * 控制层类的基类
 */

public class BaseController {
    public static final int OK = 200;

    /**
     * 获取session对象中的uid
     * 返回当前登录用户的uid的值
     * @param session
     * @return
     */
    protected final Integer getuidFromSession(HttpSession session){
        return Integer.valueOf(session.getAttribute("uid").toString());
    }

    protected final String getUsernameFromSession(HttpSession session){
        return session.getAttribute("username").toString();
    }

    // 项目中产生异常，自动拦截到此方法
    @ExceptionHandler({ServiceException.class, FileUploadException.class})
    public JsonResult<Void> handlerException(Throwable throwable){
        JsonResult<Void> res = new JsonResult<>(throwable);
        if(throwable instanceof UsernameDuplicatedException){
            res.setState(4000);
        }else if(throwable instanceof UserNotFoundException){
            res.setState(4001);
        }else if(throwable instanceof PasswordNotMatchException){
            res.setState(4002);
        }else if(throwable instanceof AddressNumbersOutOfException){
            res.setState(4003);
        }else if(throwable instanceof AddressNotFoundException){
            res.setState(4004);
        }else if(throwable instanceof AccessDeniedException){
            res.setState(4005);
        }else if(throwable instanceof ProductNotFoundException){
            res.setState(4006);
        }else if(throwable instanceof CartNotFoundException){
            res.setState(4007);
        }else if(throwable instanceof InsertException){
            res.setState(5000);
        }else if(throwable instanceof UpdateException){
            res.setState(5001);
        }else if(throwable instanceof DeleteException){
            res.setState(5002);
        }else if(throwable instanceof FileEmptyException){
            res.setState(6001);
        }else if(throwable instanceof FileSizeException){
            res.setState(6002);
        }else if(throwable instanceof FileTypeException){
            res.setState(6003);
        }else if(throwable instanceof FileStateException){
            res.setState(6004);
        }else if(throwable instanceof FileUploadIOException){
            res.setState(6005);
        }

        return res;
    }
}
