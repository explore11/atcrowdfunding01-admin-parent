package com.atguigu.crowd.service;

import com.atguigu.crowd.entity.Role;
import com.github.pagehelper.PageInfo;

/**
 * @program: atcrowdfunding01-admin-parent
 * @description //TODO
 * @author: swq
 * @create: 2020-06-27 14:35
 **/
public interface RoleService {
    /**
     * 根据关键字查询角色分页信息
     * @param pageNum
     * @param pageSize
     * @param keyword
     * @return
     */
    PageInfo<Role> getPageInfo(Integer pageNum, Integer pageSize, String keyword);
}
