package com.ouc.edu.web.servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ouc.edu.domain.PageBean;
import com.ouc.edu.domain.Product;
import com.ouc.edu.service.ProductService;
import com.ouc.edu.service.impl.ProductServiceImpl;
import com.ouc.edu.utils.CookUtils;
import com.ouc.edu.web.servlet.base.BaseServlet;

/**
 * 前台商品模块
 */
public class ProductServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 分类商品分页展示
	 */
	public String findByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1.获取pagenumber cid  设置pagesize
			/*String parameter = request.getParameter("pageNumber");*/
			int pageNumber = 1;
			
			try {
				pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
			} catch (NumberFormatException e) {
			}
			
			int pageSize = 12;
			String cid = request.getParameter("cid");
			
			//2.调用service 分页查询商品 参数:3个, 返回值:pagebean
			ProductService ps = new ProductServiceImpl();
			PageBean<Product> bean=ps.findByPage(pageNumber,pageSize,cid);
			
			//3.将pagebean放入request中,请求转发 product_list.jsp
			request.setAttribute("pb", bean);
		} catch (Exception e) {
			request.setAttribute("msg", "分页查询失败");
			return "/jsp/msg.jsp";
		}
		return "/jsp/product_list.jsp";
	}
	
	/**
	 * 商品详情
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String getById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1.获取pid
			String pid = request.getParameter("pid");
//			//获取cookie
//			Cookie cookie = CookUtils.getCookieByName("history", request.getCookies());
//			
//			//拼凑商品id
//			if(cookie == null) {
//				Cookie cookie2 = new Cookie(pid, "history");
//				cookie.setPath("/");
//				cookie.setMaxAge(7*24*60*60);
//				response.addCookie(cookie2);
//			}else {
//				//不是第一次浏览商品
//				String value = cookie.getValue();
//				String[] ids = value.split("-");
//				LinkedList<String> list = new LinkedList<String>(Arrays.asList(ids));
//				
//				if(list.contains(pid)) {
//					//
//					list.remove(pid);
//					list.addFirst(pid);
//				}else {
//					if(list.size() >= 6) {
//						list.removeLast();
//						list.addFirst(pid);
//					}else {
//						list.addFirst(pid);
//					}
//				}
//				StringBuffer sb = new StringBuffer();
//				for(String id : list) {
//					sb.append(id+"-");
//				}
//				String history = sb.substring(0, sb.length()-1);
//				Cookie c = new Cookie(history, "history");
//				c.setPath("/");
//				c.setMaxAge(7*24*60*60);
//				response.addCookie(c);
//			}
			//2.调用service获取单个商品 参数:pid 返回值:product
			ProductService ps =new ProductServiceImpl();
			Product pro=ps.getById(pid);
			
			//3.将product放入request域中,请求转发 /jsp/product_info.jsp
			request.setAttribute("bean", pro);
		} catch (Exception e) {
			request.setAttribute("msg", "查询单个商品失败");
			return "/jsp/msg.jsp";
		}
		
		return "/jsp/product_info.jsp";
	}
}
