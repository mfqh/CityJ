package com.whut.cityj.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = "/exam*")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session =((HttpServletRequest)request).getSession();
        if(session.getAttribute("user") != null){
            //存在用户，放行
            chain.doFilter(request, response);
        }

    }

    @Override
    public void destroy() {

    }
}
