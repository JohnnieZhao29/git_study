package com.zjydemo.mallstore.mapper;

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

public class DistrictMapperTests {

    @Autowired
    // 接口不能直接创建Bean
    private DistrictMapper districtMapper;

    @Test
    public void find(){
        List<District> district = districtMapper.findDistrict("210100");
        for (District d : district) {
            System.out.println(d);
        }

    }

    @Test
    public void findname(){
        String nameByCode = districtMapper.findNameByCode("371500");
        System.out.println(nameByCode);
    }


}
