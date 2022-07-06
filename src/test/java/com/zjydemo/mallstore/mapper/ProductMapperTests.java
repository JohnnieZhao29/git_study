package com.zjydemo.mallstore.mapper;

import com.zjydemo.mallstore.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author zjy
 * @version 1.0
 */

@SpringBootTest
public class ProductMapperTests {

    @Autowired
    private ProductMapper productMapper;


    @Test
    public void findHotList(){
        List<Product> list = productMapper.findHotList();
        System.out.println("count=" + list.size());
        for (Product item : list) {
            System.out.println(item);
        }
    }
}
