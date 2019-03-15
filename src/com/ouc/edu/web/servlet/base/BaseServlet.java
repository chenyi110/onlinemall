package com.ouc.edu.web.servlet.base;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 抽取出来的通用的servlet
 */
public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			//1.获取方法名称
			String mName = request.getParameter("method");
			
			if(mName == null || mName.trim().length() == 0) {
				mName = "index";
			}
			//2.获取方法对象
			Method method = this.getClass().getMethod(mName, HttpServletRequest.class, HttpServletResponse.class);
			
			//3.让方法执行，接收返回值
			String path = (String) method.invoke(this, request, response);
			
			//4.判断返回值是否为空，若不为空，则统一处理请求转发
			if(path != null) {
				request.getRequestDispatcher(path).forward(request, response);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	public String index(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().println("亲~不要皮~");
		return null;
		
	}

}
