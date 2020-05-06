package com.atguigu.crowd.mvc.interceptor;

import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.util.AccessForbidException;
import com.atguigu.crowd.util.CrowdConstants;
import com.atguigu.crowd.util.LoginFailException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @program: atcrowdfunding01-admin-parent
 * @description 登录拦截器
 * @author: swq
 * @create: 2020-05-06 22:13
 **/
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取session
        HttpSession session = request.getSession();
        //从session中获取admin 对象
        Admin admin = (Admin) session.getAttribute(CrowdConstants.LOGIN_ADMIN);

        //判断
        if (null == admin){
            throw new AccessForbidException(CrowdConstants.MESSAGE_ACCESS_FORBID);
        }

        return true;
    }
}
