package com.cnu.iqas.dao.iword.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.cnu.iqas.bean.base.QueryResult;
import com.cnu.iqas.bean.iword.Iword;
import com.cnu.iqas.bean.iword.WordTheme;
import com.cnu.iqas.bean.iword.WordThemeTypeEnum;
import com.cnu.iqas.dao.base.DaoSupport;
import com.cnu.iqas.dao.iword.WordThemeDao;

/**
* @author 周亮 
* @version 创建时间：2015年12月7日 下午6:41:09
* 类说明 单词主题数据访问类
*/
@Repository("wordThemeDao")
public class WordThemeDaoImpl extends DaoSupport<WordTheme>implements WordThemeDao {

	@Override
	public QueryResult<Iword> getWords(final String themeid,final int firstindex,final  int maxresult) {
		
		QueryResult<Iword> qr =getHt().execute(new HibernateCallback<QueryResult<Iword>>() {
			@Override
			public QueryResult<Iword> doInHibernate(Session session) throws HibernateException, SQLException {
				QueryResult<Iword> q=new QueryResult<Iword>();
				String hql = "select o from Iword o where o.uuid in ( select wordId from WordThemeWordRel where wordThemeId =:wordThemeId)";
				Query query =session.createQuery(hql);
				query.setParameter("wordThemeId", themeid);
				//分页
				if(firstindex!=-1 && maxresult!=-1) 
				query.setFirstResult(firstindex).setMaxResults(maxresult);
				
				List list = query.list();
				q.setResultlist(list);
				 String hqlcount = "select count(*) from Iword o where o.uuid in ( select wordId from WordThemeWordRel where wordThemeId =:wordThemeId)";
				
				query = session.createQuery(hqlcount);
				query.setParameter("wordThemeId", themeid);
				q.setTotalrecord((Long)query.uniqueResult());
				return q;
			}
		});
		return qr;
	}

	@Override
	public WordTheme findByName(final String name) {
		
		List<WordTheme> list =getHt().find("From WordTheme Where name=?", name);
		if( list !=null && list.size() ==1)
			return list.get(0);
		else
			return null;
	}


}
