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
	/**
	 * 根据主题id查找该主题下所有单词
	 * @param themeid
	 * @return
	 */
	public QueryResult<Iword> getAllWords( String themeid);
	/**
	 * 根据主题名称查找主题
	 * @param name 主题名称
	 * @return
	 */
	public WordTheme findByName(String  name);
}
