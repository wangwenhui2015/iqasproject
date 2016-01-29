package com.cnu.iqas.dao.iword.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.cnu.iqas.bean.iword.WordResource;
import com.cnu.iqas.dao.base.DaoSupport;
import com.cnu.iqas.dao.iword.WordResourceDao;

/**
* @author 周亮 
* @version 创建时间：2015年11月26日 下午7:23:14
* 类说明
*/
@Repository("wordResourceDao")
public class WordResourceDaoImpl extends DaoSupport<WordResource> implements WordResourceDao{

	@Override
	public List<WordResource> findByWord(String wordId, int type) {
		return getHt().find("From WordResource o where o.wordId=? and type=?", wordId,type);
	}
}
