package com.atguigu.crowd.mvc.handler;

import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.service.AdminService;
import com.atguigu.crowd.util.CrowdConstants;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
     * 根据关键字查询分页信息
     * @param keyWord
     * @param pageNum
     * @param pageSize
     * @param modelMap
     * @return
     */
    @RequestMapping("/admin/get/page.html")
    public String getPageInfo(
            @RequestParam(value = "keyWord",defaultValue = "")String keyWord,
            @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
            @RequestParam(value = "pageSize",defaultValue = "5")Integer pageSize,
            ModelMap modelMap){

        //调用service方法
        PageInfo<Admin> pageInfo = adminService.getPageInfo(keyWord, pageNum, pageSize);
        // 将查询到的值存到域中
        modelMap.addAttribute(CrowdConstants.ATTR_NAME_PAGE_INFO,pageInfo);
        return "admin-page";
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
