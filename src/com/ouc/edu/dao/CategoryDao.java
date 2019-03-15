package com.ouc.edu.dao;

import java.util.List;

import com.ouc.edu.domain.Category;

public interface CategoryDao {

	List<Category> findAll() throws Exception;

	void save(Category c) throws Exception;

	Category findById(String cid) throws Exception;

}
