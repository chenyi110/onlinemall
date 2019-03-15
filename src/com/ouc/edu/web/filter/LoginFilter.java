package com.ouc.edu.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ouc.edu.domain.User;
import com.ouc.edu.service.UserService;
import com.ouc.edu.service.impl.UserServiceImpl;
import com.ouc.edu.utils.CookUtils;

public class LoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		try {
			//0 强转
			HttpServletRequest request = (HttpServletRequest) req;
			HttpServletResponse response = (HttpServletResponse) resp;
			
			//如果是登录页直接放行
			String servletPath = request.getServletPath();
			if(servletPath.startsWith("/UserServlet")) {
				String method = request.getParameter("method");
				if("loginUI".equals(method)) {
					chain.doFilter(request, response);
					return;
				}
			}
			//1.用户登录信息
			User loginUser = (User) request.getSession().getAttribute("loginUser");
			
			//2.如果已经登录，放行，不需要自动登录
			if(loginUser != null) {
				chain.doFilter(request, response);
				return;
			}
			
			//3.获得自动登录的cookie信息
			Cookie userCookie = CookUtils.getCookieByName("autoLoginCookie", request.getCookies());
			
			
			//4.判断自动登录cookie是否存在
			if(userCookie == null) {
				chain.doFilter(request, response);
				return;
			}
			
			//5.通过用户cookie中记录信息，查询用户
			//5.1获得用户信息
			String[] u = userCookie.getValue().split("@");
			String username = u[0];
			String password = u[1];
			UserService us = new UserServiceImpl();
			
			//5.2执行登录
			loginUser = us.login(username, password);
			
			//6.如果没有查询（修改密码
			if(loginUser == null) {
				chain.doFilter(request, response);
				return;
			}
			
			//7.自动登录
			request.getSession().setAttribute("loginUser", loginUser);
			//放行
			chain.doFilter(request, response);
		} catch (Exception e) {
			System.out.println("登录异常，自动忽略");
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
