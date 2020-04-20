package com.atguigu.crowd.service;

import com.atguigu.crowd.entity.Admin;

import java.util.List;

public interface AdminService {
    void save(Admin admin);

    List<Admin> getAll();


}
