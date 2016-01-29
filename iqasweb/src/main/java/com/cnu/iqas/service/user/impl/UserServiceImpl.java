package com.cnu.iqas.service.user.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cnu.iqas.bean.user.User;
import com.cnu.iqas.bean.user.UserLogin;
import com.cnu.iqas.dao.user.UserDao;
import com.cnu.iqas.dao.user.UserLoginDao;
import com.cnu.iqas.service.user.UserService;
import com.cnu.iqas.utils.WebUtils;
@Service("userService")
public class UserServiceImpl implements UserService {
	private UserDao userDao;
	/**
	 * 登录记录操作表
	 */
	private  UserLoginDao userLoginDao;
	//给密码加密
	public void save(User entity) {
		if( entity !=null){
			entity.setPassword(WebUtils.MD5Encode(entity.getPassword().trim()));
			userDao.save(entity);
		}else{
			throw new RuntimeException("保存用户为空!");
		}
	}

	@Override
	public User validate(String userName, String password) {
		// TODO Auto-generated method stub
		User user = null;
		if( userName !=null &&!userName.equals("") && password!=null && !password.equals("")){
			user =userDao.validate(userName, WebUtils.MD5Encode(password.trim()));
		}
		return user;
	}

	public User findByName(String userName) {
		// TODO Auto-generated method stub
		if( userName!=null &&!userName.equals(""))
		    return userDao.findByName(userName);
		else
			return null;
	}
	public UserDao getUserDao() {
		return userDao;
	}
	@Resource(name="userDao")
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public UserLoginDao getUserLoginDao() {
		return userLoginDao;
	}
	@Resource
	public void setUserLoginDao(UserLoginDao userLoginDao) {
		this.userLoginDao = userLoginDao;
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		userDao.update(user);
	}

	@Override
	@Transactional
	public User login(String userName, String password,String ip) {
		//校验
		User user =validate(userName, password);
		//保存登录记录
		try {
			UserLogin entity = new UserLogin();
			entity.setUserName(userName);
			entity.setIp(ip);
			userLoginDao.save(entity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("保存登录日志出错!");
		}
		
		return user;
	}

}
