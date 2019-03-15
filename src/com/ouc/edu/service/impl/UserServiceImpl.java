package com.ouc.edu.service.impl;

import com.ouc.edu.constant.Constant;
import com.ouc.edu.dao.UserDao;
import com.ouc.edu.dao.impl.UserDaoImpl;
import com.ouc.edu.domain.User;
import com.ouc.edu.service.UserService;
import com.ouc.edu.utils.BeanFactory;
import com.ouc.edu.utils.MailUtils;

public class UserServiceImpl implements UserService {

	@Override
	/**
	 * 用户注册
	 */
	public void regist(User user) throws Exception {
		// TODO Auto-generated method stub
		//1.调用dao完成注册
		UserDao ud = (UserDao) BeanFactory.getBean("UserDao");
		ud.save(user);
		
		//2.发送激活邮件
		String emailMsg = "恭喜" + user.getName() + "注册成功！<a href = 'http://localhost:8080/onlinemall/user?method=active&code=" + user.getCode() + "'>点击此处完成激活。</a>";
		MailUtils.sendMail(user.getEmail(), emailMsg);
		System.out.println(user.getEmail());
	}

	@Override
	/**
	 * 用户激活
	 */
	public User active(String code) throws Exception {
		// TODO Auto-generated method stub
		UserDao ud = (UserDao) BeanFactory.getBean("UserDao");;
		//1.通过code获取用户
		User user = ud.getByCode(code);
		//1.1 通过激活码没有找到 用户
		if(user == null) {
			return null;
		}
		//2.若获取到了 修改用户
		user.setState(Constant.USER_IS_ACTIVE);
		user.setCode(null);
		
		ud.update(user);
		return user;
	}

	/**
	 * 用户登录
	 */
	@Override
	public User login(String username, String password) throws Exception {
		// TODO Auto-generated method stub
		UserDao ud = (UserDao) BeanFactory.getBean("UserDao");;
		
		return ud.getByUsernameAndPwd(username,password);
	}
	

}
