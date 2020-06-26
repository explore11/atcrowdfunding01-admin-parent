package com.atguigu.crowd.service;

import com.atguigu.crowd.entity.Admin;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface AdminService {
    void save(Admin admin);

    List<Admin> getAll();

    /**
     * 查询登录账号
     * @param loginAccount
     * @param loginPassword
     * @return
     */
    Admin doLogin(String loginAccount, String loginPassword);

    /**
     * 根据关键字查询分页信息
     * @param keyWord
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<Admin> getPageInfo(String keyWord,Integer pageNum,Integer pageSize);

    /**
     * 删除用户对象
     * @param adminId
     * @return
     */
    Boolean removeAdmin(Integer adminId);

    /**
     * 保存用户
     * @param admin
     */
    void saveAdmin(Admin admin);

    /**
     * 根据id用户
     * @param adminId
     */
    Admin getAdminById(Integer adminId);

    /**
     * 更新用户
     * @param admin
     */
    void editAdmin(Admin admin);
}
