package com.atguigu.crowd.test;

import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.mapper.AdminMapper;
import com.atguigu.crowd.service.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-persist-mybatis.xml","classpath:spring-persist-tx.xml"})
public class CrowdSpringTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private AdminMapper mapper;

    @Resource
    private AdminService adminService;

    @Test
    public void saveAdmin() {
        Admin admin = new Admin(null,"jerry","123456","杰瑞","jerry@qq.com",null);
        adminService.save(admin);
        int i = 1/0;
    }


    @Test
    public void getTestMapper() {
        Admin admin = new Admin(null, "111111", "123456", "张三", "23@qq.com", null);
        int count = mapper.insert(admin);
        System.out.println("影响的行数" + count);
    }

    @Test
    public void getDtaSource() throws SQLException {
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }
}
