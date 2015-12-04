package com.cnu.iqas.dao.user;

import com.cnu.iqas.bean.user.User;
import com.cnu.iqas.dao.base.DAO;

/**
* @author 周亮 
* @version 创建时间：2015年11月9日 下午8:39:09
* 类说明
*/
public interface UserDao {
	/**
	 * 判断用户输入账号或密码是否正确
	 * @param username 账号
	 * @param password 密码
	 * @return 返回用户实例
	 */
	public User validate(String username, String password);
	/**
	 * 根据用户名查询用户是否存在
	 * @param userName
	 * @return
	 */
	public User findByName(String userName);
}
