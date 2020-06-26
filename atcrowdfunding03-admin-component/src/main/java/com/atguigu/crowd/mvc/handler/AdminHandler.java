package com.atguigu.crowd.mvc.handler;

import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.service.AdminService;
import com.atguigu.crowd.util.CrowdConstants;
import com.atguigu.crowd.util.CrowdUtils;
import com.atguigu.crowd.util.exception.LoginAcctNotUniqueException;
import com.atguigu.crowd.util.exception.LoginAcctNotUniqueForUpdateException;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

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
     * 根据关键字查询分页信息
     *
     * @param keyWord
     * @param pageNum
     * @param pageSize
     * @param modelMap
     * @return
     */
    @RequestMapping("/admin/get/page.html")
    public String getPageInfo(
            @RequestParam(value = "keyWord", defaultValue = "") String keyWord,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            ModelMap modelMap) {

        //调用service方法
        PageInfo<Admin> pageInfo = adminService.getPageInfo(keyWord, pageNum, pageSize);
        // 将查询到的值存到域中
        modelMap.addAttribute(CrowdConstants.ATTR_NAME_PAGE_INFO, pageInfo);
        return "admin-page";
    }

    /**
     * 删除用户对象
     *
     * @param adminId
     * @param keyWord
     * @param pageNum
     * @return
     */
    @RequestMapping("/admin/remove/{id}/{pageNum}/{keyWord}")
    public String removeAdmin(
            @PathVariable("id") Integer adminId,
            @PathVariable("pageNum") Integer pageNum,
            @PathVariable("keyWord") String keyWord
    ) {

        Boolean flag = adminService.removeAdmin(adminId);
        return "redirect:/admin/get/page.html?pageNum=" + pageNum + "&keyWord=" + keyWord;
    }


    /**
     * 保存用户
     *
     * @param admin
     * @return
     */
    @RequestMapping("/admin/save.html")
    public String saveAdmin(Admin admin) {
        //密码加密
        String userPswd = admin.getUserPswd();
        admin.setUserPswd(CrowdUtils.md5(userPswd));
        //设置时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createTime = simpleDateFormat.format(new Date());
        admin.setCreateTime(createTime);

        //保存用户
        try {
            adminService.saveAdmin(admin);
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof DuplicateKeyException) {
                throw new LoginAcctNotUniqueException(CrowdConstants.MESSAGE_LOGIN_ACCOUNT_ALREADY_IN_USE);
            }
        }
        return "redirect:/admin/get/page.html?pageNum=" + Integer.MAX_VALUE;
    }

    /**
     * 查询单个用户  并天跳转到更新用户页面
     *
     * @return
     */
    @RequestMapping("/admin/to/edit/page.html")
    public String getAdminById(@RequestParam("adminId") Integer adminId, ModelMap modelMap) {
        Admin admin = adminService.getAdminById(adminId);
        modelMap.addAttribute("admin", admin);
        return "admin-edit";
    }


    /**
     * 更新用户
     *
     * @param admin
     * @return
     */
    @RequestMapping("/admin/edit.html")
    public String editAdmin(Admin admin, @RequestParam("pageNum") Integer pageNum, @RequestParam("keyWord") String keyWord) {
        try {
            adminService.editAdmin(admin);
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof DuplicateKeyException) {
                throw new LoginAcctNotUniqueForUpdateException(CrowdConstants.MESSAGE_LOGIN_ACCOUNT_ALREADY_IN_USE);
            }
        }

        return "redirect:/admin/get/page.html?pageNum=" + pageNum + "&keyWord=" + keyWord;
    }


    /**
     * 退出登录
     *
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
