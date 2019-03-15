package com.ouc.edu.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.ouc.edu.dao.CategoryDao;
import com.ouc.edu.domain.Category;
import com.ouc.edu.utils.DataSourceUtils;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class CategoryDaoImpl implements CategoryDao {

	@Override
	/**
	 * 查询所有分类
	 */
	public List<Category> findAll() throws Exception {
		// TODO Auto-generated method stub
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from category";
		return qr.query(sql, new BeanListHandler<>(Category.class));
	}

	@Override
	public void save(Category c) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into category values (?,?);";
		qr.update(sql, c.getCid(),c.getCname());
		
	}

	@Override
	public Category findById(String cid) throws Exception {
		// TODO Auto-generated method stub
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from category where cid = ?";
		//Category category = (Category) qr.query(sql, new BeanListHandler<Category>(Category.class), cid);
		
		return (Category) qr.query(sql, new BeanListHandler<Category>(Category.class), cid);
	}

}
