package com.atguigu.crowd.mvc.handler;

import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @program: atcrowdfunding01-admin-parent
 * @description
 * @author: swq
 * @create: 2020-04-30 22:56
 **/
public class AdminHandler {
    @Autowired
    private AdminService adminService;

    @RequestMapping("/admin/do/login.html")
    public String doLogin(@RequestParam("loginAccount") String loginAccount, @RequestParam("loginPassword") String loginPassword) {

        Admin admin = adminService.doLogin(loginAccount, loginPassword);

        return "system-main";
    }
}
