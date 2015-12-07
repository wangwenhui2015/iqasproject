package com.cnu.iqas.dao.iword;

import com.cnu.iqas.bean.base.QueryResult;
import com.cnu.iqas.bean.iword.Iword;
import com.cnu.iqas.bean.iword.WordTheme;
import com.cnu.iqas.bean.iword.WordThemeTypeEnum;
import com.cnu.iqas.dao.base.DAO;

/**
* @author 周亮 
* @version 创建时间：2015年12月7日 下午6:40:20
* 类说明
*/
public interface WordThemeDao extends DAO<WordTheme> {

	public QueryResult<Iword> getAllWords( String themeid);
	
	public WordTheme findByType(int type);
}
