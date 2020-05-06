package com.atguigu.crowd.mvc.handler;

import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.service.AdminService;
import com.atguigu.crowd.util.CrowdConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * @program: atcrowdfunding01-admin-parent
 * @description
 * @author: swq
 * @create: 2020-04-30 22:56
 **/
@Controller
public class AdminHandler {
    @Autowired
    private AdminService adminService;

    /**
     * 登录
     *
     * @param loginAccount
     * @param loginPassword
     * @param session
     * @return
     */
    @RequestMapping("/admin/do/login.html")
    public String doLogin(@RequestParam("loginAccount") String loginAccount, @RequestParam("loginPassword") String loginPassword, HttpSession session) {

        Admin admin = adminService.doLogin(loginAccount, loginPassword);

        session.setAttribute(CrowdConstants.LOGIN_ADMIN, admin);

        return "redirect:/admin/to/main/page.html";
    }


    /**
     * 退出登录
     * @param session
     * @return
     */
    @RequestMapping("/admin/do/loginOut.html")
    public String loginOut(HttpSession session) {
        //强制清除session
        session.invalidate();
        return "redirect:/admin/to/login.html";
    }
}
