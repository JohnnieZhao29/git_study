package com.zjydemo.mallstore.mapper;

import com.zjydemo.mallstore.entity.Address;
import com.zjydemo.mallstore.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

/**
 * @author zjy
 * @version 1.0
 */

// 标注当前类是测试类，不会随同项目一起打包发送
@SpringBootTest

public class AddressMapperTests {

    @Autowired
    // 接口不能直接创建Bean
    private AddressMapper addressMapper;

    @Test
    public void insert(){
        Address address = new Address();
        address.setUid(103);
        address.setPhone("110120119");
        address.setName("自己");
        addressMapper.insert(address);
    }

    @Test
    public void count(){
        Integer count = addressMapper.count(103);
        System.out.println(count);
    }

    @Test
    public void findByUid(){
        List<Address> byUid = addressMapper.findByUid(103);
        for (Address address : byUid) {
            System.out.println(address);
        }

    }

    @Test
    public void findByAid(){
        System.out.println(addressMapper.findByAid(7));
    }

    @Test
    public void clearDefault(){
        System.out.println(addressMapper.clearDefault(103));
    }
    @Test
    public void setDefault(){
        addressMapper.setDefault(7, "zw", new Date());
    }



    @Test
    public void deleteByAid() {
        addressMapper.deleteByAid(2);

    }

    @Test
    public void findLastModified() {
        System.out.println(addressMapper.findLastModified(103));
    }




}
