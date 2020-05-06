package com.atguigu.crowd.mvc.handler;

import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.service.AdminService;
import com.sun.org.apache.xpath.internal.SourceTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.security.PublicKey;
import java.util.List;

@Controller
public class TestHandler {
    @Resource
    private AdminService adminService;

    @RequestMapping("/test/ssm.html")
    public String test(ModelMap modelMap) {
        List<Admin> list = adminService.getAll();
//        int i =1/0;
//        String a=null;
//        System.out.println(a.length());
        modelMap.addAttribute("list", list);
        return "target";
    }
}
