package com.ouc.edu.dao;

import java.util.List;

import com.ouc.edu.domain.Order;
import com.ouc.edu.domain.OrderItem;
import com.ouc.edu.domain.PageBean;

public interface OrderDao {

	void save(Order order) throws Exception;

	void saveItem(OrderItem oi) throws Exception;
	
	int getTotalRecord(String uid) throws Exception;

	List<Order> findMyOrdersByPage(PageBean<Order> pb, String uid) throws Exception;

	Order getById(String oid) throws Exception;

	void update(Order order) throws Exception;

	List<Order> findAllByState(String state)throws Exception;
	
}
