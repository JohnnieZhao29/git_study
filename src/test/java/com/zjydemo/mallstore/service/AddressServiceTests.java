package com.zjydemo.mallstore.service;


import com.zjydemo.mallstore.entity.Address;
import com.zjydemo.mallstore.entity.User;
import com.zjydemo.mallstore.service.ex.ServiceException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author zjy
 * @version 1.0
 */

// 标注当前类是测试类，不会随同项目一起打包发送
@SpringBootTest

public class AddressServiceTests {

    @Autowired
    // 接口不能直接创建Bean
    private IAddressService addressService;

    /**
     * 1. 必须TEST注解
     * 2. 必须void 和 public
     * 3. 形参表为空
     */

    @Test
    public  void addAddress(){
        Address address = new Address();

        address.setPhone("110120119");
        address.setName("朋友");
        addressService.addAddress(address, "zw", 103);

    }

    @Test
    public void setDefault(){
        addressService.setDefault(2,103,"zw");
    }



    @Test
    public void delete(){
        addressService.delete(5,103,"admin");
    }

}
