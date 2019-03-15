package com.ouc.edu.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ouc.edu.domain.Product;
import com.ouc.edu.service.ProductService;
import com.ouc.edu.service.impl.ProductServiceImpl;
import com.ouc.edu.web.servlet.base.BaseServlet;

/**
 * 首页模块
 */
public class IndexServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public String index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			//1.调用productservice查询最新商品 和 热门商品
			ProductService ps = new ProductServiceImpl();
			List<Product> hotList=ps.findHot();
			List<Product> newList=ps.findNew();
			
			//2.将两个list都放入request域中
			request.setAttribute("hList", hotList);
			request.setAttribute("nList", newList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/jsp/index.jsp";
	}
}
