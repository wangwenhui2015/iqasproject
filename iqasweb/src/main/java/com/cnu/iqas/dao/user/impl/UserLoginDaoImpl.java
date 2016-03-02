package com.cnu.iqas.dao.user.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cnu.iqas.bean.user.UserLogin;
import com.cnu.iqas.dao.base.DaoSupport;
import com.cnu.iqas.dao.user.UserLoginDao;

/**
* @author 周亮 
* @version 创建时间：2016年1月27日 上午11:41:15
* 类说明
*/
@Repository("userLoginDao")
public class UserLoginDaoImpl extends DaoSupport<UserLogin> implements UserLoginDao{

	@Override
	public UserLogin findCurrentLogin(String userId, String ip) {
		List<UserLogin> list =getHt().find(" From UserLogin o where o.userId=? and ip =? and loginState=1", userId,ip);
		
		if( list !=null && list.size()>0)
			return list.get(0);
		return null;
	}

	@Override
	public List<UserLogin> findLogining(String userId) {
		List<UserLogin> list =getHt().find(" From UserLogin o where o.userId=? and loginState=1", userId);
		return list;
	}
}
