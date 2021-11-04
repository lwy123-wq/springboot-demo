package com.example.dididemo.filter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyFilter implements Filter {
    private Logger logger =LoggerFactory.getLogger(MyFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletResponse res = (HttpServletResponse) response;
        HttpServletRequest req = (HttpServletRequest) request;
        String origin = req.getHeader("Origin");

        if (!org.springframework.util.StringUtils.isEmpty(origin)) {
            //带cookie的时候，origin必须是全匹配，不能使用*
            res.addHeader("Access-Control-Allow-Origin", origin);
        }
        res.addHeader("Access-Control-Allow-Methods", "*");
        String headers = req.getHeader("Access-Control-Request-Headers");
        // 支持所有自定义头
        if (!org.springframework.util.StringUtils.isEmpty(headers)) {
            res.addHeader("Access-Control-Allow-Headers", headers);
        }
        res.addHeader("Access-Control-Max-Age", "3600");
        // enable cookie
        res.addHeader("Access-Control-Allow-Credentials", "true");

        //处理options请求
        if (req.getMethod().toUpperCase().equals("OPTIONS")) {
            return;
        }

        //获取token
        //String auth = req.getHeader("Authorization");
        String auth = req.getHeader("token");

        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
