package com.atguigu.crowd.mvc.handler;

import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.entity.Role;
import com.atguigu.crowd.mapper.RoleMapper;
import com.atguigu.crowd.service.AdminService;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class TestHandler {
    @Resource
    private AdminService adminService;
    @Resource
    private RoleMapper mapper;





    @RequestMapping("/test/ssm.html")
    public String test(ModelMap modelMap) {
        List<Admin> list = adminService.getAll();
//        int i =1/0;
//        String a=null;
//        System.out.println(a.length());
        modelMap.addAttribute("list", list);
        return "target";
    }


    @RequestMapping("/role.html")
    public void setRole(){
        for (int i = 0; i < 238; i++) {
            mapper.insert(new Role(null,"role"+i));
        }
    }



    @RequestMapping("/test.html")
    public void  test(){
        for (int i = 0; i < 238; i++) {
            Admin admin = new Admin(null, "LoginAcct" + i, "password" + i, "username" + i, "email" + i, null);
            adminService.save(admin);
        }
    }
}
