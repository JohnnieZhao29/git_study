package com.zjydemo.mallstore.service;


import com.zjydemo.mallstore.entity.Product;
import com.zjydemo.mallstore.entity.User;
import com.zjydemo.mallstore.service.ex.ServiceException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author zjy
 * @version 1.0
 */

// 标注当前类是测试类，不会随同项目一起打包发送
@SpringBootTest

public class ProductsServiceTests {

    @Autowired
    // 接口不能直接创建Bean
    private IProductService productService;

    /**
     * 1. 必须TEST注解
     * 2. 必须void 和 public
     * 3. 形参表为空
     */
    @Test
    public void findHotList() {
        try {
            List<Product> list = productService.findHotList();
            System.out.println("count=" + list.size());
            for (Product item : list) {
                System.out.println(item);
            }
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
}
