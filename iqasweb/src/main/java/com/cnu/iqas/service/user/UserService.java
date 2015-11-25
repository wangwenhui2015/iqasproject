package com.cnu.iqas.service.user;

import com.cnu.iqas.bean.user.User;
import com.cnu.iqas.dao.base.DAO;

public interface UserService extends DAO<User> {

	/**
	 * 判断用户输入账号或密码是否正确
	 * @param username 账号
	 * @param password 密码
	 * @return 返回用户实例
	 */
	public User validate(String username, String password);
}