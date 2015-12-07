package com.cnu.iqas.service.iword;

import java.util.List;

import com.cnu.iqas.bean.base.QueryResult;
import com.cnu.iqas.bean.iword.Iword;
import com.cnu.iqas.bean.iword.WordTheme;
import com.cnu.iqas.bean.iword.WordThemeTypeEnum;

/**
* @author 周亮 
* @version 创建时间：2015年12月7日 下午6:49:00
* 类说明  单词主题服务类接口
*/
public interface WordThemeService {
	/**
	 * 根据主题类型获取该类型下所有单词
	 * @param themeid  主题id
	 * @return  查询结果类
	 */
	public QueryResult<Iword> getAllWords(String themeid);
	
	/**
	 * 查询所有主题
	 * @return 查询结果类
	 */
	public QueryResult<WordTheme> getThemes();
	
	/**
	 * 保存一个实体
	 * @param entity
	 */
	public void save(WordTheme entity);
	/**
	 * 根据主题类型查找主题
	 * @param type
	 * @return
	 */
	public WordTheme findByType(int type);
}
