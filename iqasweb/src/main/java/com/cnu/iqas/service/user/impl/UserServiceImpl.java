package com.cnu.iqas.service.user.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cnu.iqas.bean.user.User;
import com.cnu.iqas.dao.base.DaoSupport;
import com.cnu.iqas.dao.user.UserDao;
import com.cnu.iqas.service.user.UserService;
import com.cnu.iqas.utils.WebUtils;
@Service("userService")
public class UserServiceImpl implements UserService {
	private UserDao userDao;
	
	//给密码加密
	public void save(User entity) {
		entity.setPassword(WebUtils.MD5Encode(entity.getPassword().trim()));
		userDao.save(entity);
	}

	@Override
	public User validate(String userName, String password) {
		// TODO Auto-generated method stub
		User user = null;
		user =userDao.validate(userName, WebUtils.MD5Encode(password.trim()));
		return user;
	}

	public User findByName(String userName) {
		// TODO Auto-generated method stub
		return userDao.findByName(userName);
	}
	public UserDao getUserDao() {
		return userDao;
	}
	@Resource(name="userDao")
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
