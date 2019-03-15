package com.ouc.edu.web.servlet;

import com.ouc.edu.web.servlet.base.BaseServlet;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class VerificationServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		HttpSession session =request.getSession();
		String verificationCode = (String)session.getAttribute("verificationCode");
		String checkcode = request.getParameter("op");
		PrintWriter out = response.getWriter();
		if(checkcode.equals(verificationCode)){
			out.println(1);
		}else{
			out.println(0);
		}
		out.flush();
		out.close();
	}

}
