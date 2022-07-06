package com.zjydemo.mallstore;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// MapperScan注解指定当前项目中Mapper接口路径的位置
// 在项目启动时会自动加载所有mapper文件
@MapperScan("com.zjydemo.mallstore.mapper")

public class MallStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallStoreApplication.class, args);
    }

}
