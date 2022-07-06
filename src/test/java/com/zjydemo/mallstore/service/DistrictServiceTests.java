package com.zjydemo.mallstore.service;


import com.zjydemo.mallstore.entity.Address;
import com.zjydemo.mallstore.entity.District;
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

public class DistrictServiceTests {

    @Autowired
    // 接口不能直接创建Bean
    private IDistrictService districtService;

    /**
     * 1. 必须TEST注解
     * 2. 必须void 和 public
     * 3. 形参表为空
     */

    @Test
    public  void getDistrict(){
        List<District> district = districtService.getDistrict("86");
        for (District t : district) {
            System.out.println(t);
        }
    }

}
