package com.ouc.edu.service;

import java.util.List;

import com.ouc.edu.domain.Category;

public interface CategoryService {

	String findAll() throws Exception;

	String findAllFromRedis() throws Exception;
	
	List<Category> findList() throws Exception;

	void save(Category c) throws Exception;

	Category finById(String cid) throws Exception;
}
