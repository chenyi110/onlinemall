package com.ouc.edu.service;

import java.util.List;

import com.ouc.edu.domain.PageBean;
import com.ouc.edu.domain.Product;

public interface ProductService {

	List<Product> findHot() throws Exception;

	List<Product> findNew() throws Exception;

	Product getById(String pid) throws Exception;

	PageBean<Product> findByPage(int pageNumber, int pageSize, String cid) throws Exception;

	List<Product> findAll() throws Exception;

	void save(Product p)throws Exception;
}
