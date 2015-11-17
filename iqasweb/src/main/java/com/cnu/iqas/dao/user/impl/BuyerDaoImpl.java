package com.cnu.iqas.dao.user.impl;

import java.sql.SQLException;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.cnu.iqas.bean.user.User;
import com.cnu.iqas.dao.user.UserDao;
import com.cnu.iqas.utils.WebUtils;

/**
* @author 周亮 
* @version 创建时间：2015年11月9日 下午8:41:46
* 类说明
*/
@Repository("userDao")
public class BuyerDaoImpl implements UserDao {
	//@PersistenceContext protected EntityManager em;
	protected HibernateTemplate  ht;

	public HibernateTemplate getHt() {
		return ht;
	}
	@Resource(name="hibernateTemplate")
	public void setHt(HibernateTemplate ht) {
		this.ht = ht;
	}
	@Override
	public User validate(final String username, final String password) {
		User user = ht.execute(new HibernateCallback<User>() {
			@Override
			public User doInHibernate(Session session)
					throws HibernateException, SQLException {
				User user = (User) session.createQuery("from User o where  o.username=:username and  o.password=:password")
								.setParameter("username", username)
								.setParameter("password", password)
								.uniqueResult();
				return user;
			}
		});
		return user;
	}

}
