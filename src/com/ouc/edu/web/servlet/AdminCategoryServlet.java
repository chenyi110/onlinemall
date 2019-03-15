package com.ouc.edu.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.ouc.edu.domain.Category;
import com.ouc.edu.service.CategoryService;
import com.ouc.edu.service.impl.CategoryServiceImpl;
import com.ouc.edu.utils.BeanFactory;
import com.ouc.edu.utils.UUIDUtils;
import com.ouc.edu.web.servlet.base.BaseServlet;

/**
 * 后台分类管理模块
 */
public class AdminCategoryServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 展示所有分类
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1.调用service 获取所有的分类
			CategoryService cs = (CategoryService) BeanFactory.getBean("CategoryService");
			List<Category> list = cs.findList();
			
			//2.将返回值放入request域中 请求转发
			request.setAttribute("list", list);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		return "/admin/category/list.jsp";
	}

	/**
	 * 添加分类
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1.封装category对象
			Category c = new Category();
			c.setCid(UUIDUtils.getId());
			c.setCname(request.getParameter("cname"));
			
			//2.调用service完成添加操作
			CategoryService cs = (CategoryService) BeanFactory.getBean("CategoryService");
			cs.save(c);
			
			//3.重定向
			response.sendRedirect(request.getContextPath()+"/adminCategory?method=findAll");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return null;
	}
	
	/**
	 * 跳转到添加页面
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String addUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return "/admin/category/add.jsp";
	}
	
	public String editUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String cid = request.getParameter("cid");
			CategoryService categoryService = new CategoryServiceImpl();
			Category category = categoryService.finById(cid);
			request.setAttribute("category", category);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
		}
		return "/admin/category/edit.jsp";
	}
	
	public String edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Category category = BeanUtils.populate(Category.class, request.getParameterMap());
		CategoryService categoryService = new CategoryServiceImpl();
		//categoryService.edit(category);
		
		response.sendRedirect(request.getContextPath()+"/adminCategory?method=findAll");
		return null;
	}
}
