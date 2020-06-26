package com.atguigu.crowd.service.impl;

import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.entity.AdminExample;
import com.atguigu.crowd.mapper.AdminMapper;
import com.atguigu.crowd.service.AdminService;
import com.atguigu.crowd.util.CrowdConstants;
import com.atguigu.crowd.util.CrowdUtils;
import com.atguigu.crowd.util.exception.LoginFailException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminMapper adminMapper;

    @Test
    public void  test(){
        for (int i = 0; i < 238; i++) {
            Admin admin = new Admin(null, "LoginAcct" + i, "password" + i, "username" + i, "email" + i, null);
            adminMapper.insert(admin);

        }
    }

    /**
     * 登录账号
     * @param loginAccount
     * @param loginPassword
     * @return
     */
    public Admin doLogin(String loginAccount, String loginPassword) {
        // 1、与数据库进行交互  查询账号
        AdminExample adminExample = new AdminExample();
        // 2、判断账号是否存在
        AdminExample.Criteria criteria = adminExample.createCriteria();
        criteria.andLoginAcctEqualTo(loginAccount);
        List<Admin> adminList = adminMapper.selectByExample(adminExample);
        // 3、如果不存在 则抛出自定义的异常
        if (null == adminList || adminList.size() <= 0) {
            throw new LoginFailException(CrowdConstants.MESSAGE_LOGIN_FAILED);
        }
        // 账号个数超过一个   说明账号不唯一
        if (adminList.size() > 1) {
            throw new RuntimeException(CrowdConstants.SYSTEM_ERROR_ACCOUNT_NOT_UNIQUE);
        }
        // 获取对象
        Admin admin = adminList.get(0);
        if (null == admin) {
            throw new LoginFailException(CrowdConstants.MESSAGE_LOGIN_FAILED);
        }
        // 获取数据库中的密码
        String userPwdByDB = admin.getUserPswd();
        // 从页面传过来后记性md5加密
        String userPwdByForm = CrowdUtils.md5(loginPassword);
        // 判断是否相等  不相等则抛出异常
        if (!Objects.equals(userPwdByDB, userPwdByForm)) {
            throw new LoginFailException(CrowdConstants.MESSAGE_LOGIN_FAILED);
        }
        return admin;
    }

    /**
     * 根据关键字查询分页信息
     * @param keyWord
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<Admin> getPageInfo(String keyWord, Integer pageNum, Integer pageSize) {
        // 1、调用pageHelper的静态方法
        PageHelper.startPage(pageNum,pageSize);
        // 2、调用查询方法
        List<Admin> list = adminMapper.selectAdminByKeyWord(keyWord);
        // 3、返回数据
        return new PageInfo<>(list);
    }


    /**
     * 删除用户对象
     * @param adminId
     * @return
     */
    @Override
    public Boolean removeAdmin(Integer adminId) {
        int i = adminMapper.deleteByPrimaryKey(adminId);
        if (i>0){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }


    /**
     * 保存用户
     * @param admin
     */
    @Override
    public void saveAdmin(Admin admin) {
        adminMapper.insert(admin);
    }

    /**
     * 根据id查询用户
     * @param adminId
     */
    @Override
    public  Admin getAdminById(Integer adminId) {
        return adminMapper.selectByPrimaryKey(adminId);
    }

    /**
     * 更新用户
     * @param admin
     */
    @Override
    public void editAdmin(Admin admin) {
        adminMapper.updateByPrimaryKeySelective(admin);
    }

    public void save(Admin admin) {
        adminMapper.insert(admin);
    }


    public List<Admin> getAll() {
        return adminMapper.selectByExample(new AdminExample());
    }
}

