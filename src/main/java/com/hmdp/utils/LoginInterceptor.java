package com.hmdp.utils;

import cn.hutool.core.bean.BeanUtil;
import com.hmdp.dto.UserDTO;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @program: hm-dianping
 * @description: 登录拦截器
 * @author: Songzw
 * @create: 2023-11-27 15:12
 **/
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取session
        HttpSession session = request.getSession();
        //获取用户
        UserDTO user = (UserDTO) session.getAttribute("user");
        //判断用户是否存在
        if (user == null){
            //不存在 拦截
            return false;
        }
        //User--》UserDTO
        //存在 保存在threadlocal
        UserHolder.saveUser(user);
        //放行
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserHolder.removeUser();
    }
}
