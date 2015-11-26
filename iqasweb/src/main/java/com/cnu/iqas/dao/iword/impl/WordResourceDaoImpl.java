package com.cnu.iqas.dao.iword.impl;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.cnu.iqas.bean.iword.WordResource;
import com.cnu.iqas.dao.iword.WordResourceDao;

/**
* @author 周亮 
* @version 创建时间：2015年11月26日 下午7:23:14
* 类说明
*/
@Repository("wordResourceDao")
public class WordResourceDaoImpl implements WordResourceDao{

	protected HibernateTemplate  ht;

	public HibernateTemplate getHt() {
		return ht;
	}
	@Resource(name="hibernateTemplate")
	public void setHt(HibernateTemplate ht) {
		this.ht = ht;
	}
	@Override
	public List<WordResource> findByWord(String uuid) {
		
		return null;
	}
}
