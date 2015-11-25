package com.cnu.iqas.service.iword.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Service;

import com.cnu.iqas.bean.iword.Iword;
import com.cnu.iqas.bean.iword.VersionWordCount;
import com.cnu.iqas.dao.base.DaoSupport;
import com.cnu.iqas.exception.IwordExistException;
import com.cnu.iqas.service.iword.IwordService;

/**
* @author 周亮 
* @version 创建时间：2015年11月16日 下午10:42:38
* 类说明
*/
@Service
public class IwordServiceImpl extends DaoSupport<Iword>  implements IwordService {

	/**
	 * 批量插入，
	 */
	@Override
	public void batchSave(final List<Iword> iwords){
		 getHt().execute(new HibernateCallback<Void>() {
			@Override
			public Void doInHibernate(Session session) throws HibernateException, SQLException {
				for(Iword word:iwords){
					session.save(word);
				}
				//将Session中的数据刷入数据库并情况Session缓存(一级缓存，此时二级缓存也应该是关闭的)
				session.flush();
				session.clear();
				return null;
			}
		});
	}

	@Override
	public List<VersionWordCount> statisticsVersionAndWordCount() {
		List list =getHt().find("select new com.cnu.iqas.bean.iword.VersionWordCount(w.version as version, count(w) as sum ) from Iword w group by w.version");
		/*getHt().execute(new HibernateCallback<List<VersionWordCount>>() {

			@Override
			public List<VersionWordCount> doInHibernate(Session session) throws HibernateException, SQLException {
				
				return null;
			}
		});*/
		return list;
	}
}
