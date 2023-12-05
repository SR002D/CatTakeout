package com.nwafu.cattakeout.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * 登录验证
 */
@Slf4j
@WebFilter(filterName = "LoginFilter" ,urlPatterns = "/*")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        Object emp = httpServletRequest.getSession().getAttribute("employee");
        // 登录界面，放行
        if(httpServletRequest.getRequestURI().contains("login")){
            log.info("登录界面，放行");
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }


        if(emp==null){
            log.info("未登录，重定向到登录界面");
            httpServletResponse.sendRedirect("/backend/page/login/login.html");
        }else{
            log.info("用户名：{}，登录成功",emp);
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }
}
