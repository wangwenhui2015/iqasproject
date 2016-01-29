package com.cnu.iqas.dao.user.impl;

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

}
