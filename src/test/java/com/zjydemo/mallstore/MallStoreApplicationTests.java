package com.zjydemo.mallstore;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.Servlet;
import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
class MallStoreApplicationTests {


    @Autowired
    private DataSource dataSource;

    @Test
    void contextLoads() {

    }

    @Test
    /**
     * Hikari是一个连接池，回忆之前的JDBC中的C3P0、Druid等都是连接池
     * Hikari是号称目前最快的连接池，底部依然采用c3p0管理连接对象
     * spring boot 的默认连接池就是Hikari
     *
     *
     */
    // HikariProxyConnection@482614135 wrapping com.mysql.cj.jdbc.ConnectionImpl@2a7ebe07
    void getConnection() throws SQLException {
        System.out.println(dataSource.getConnection());
    }

}
