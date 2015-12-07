package com.cnu.iqas.service.iword.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cnu.iqas.bean.base.QueryResult;
import com.cnu.iqas.bean.iword.Iword;
import com.cnu.iqas.bean.iword.WordTheme;
import com.cnu.iqas.bean.iword.WordThemeTypeEnum;
import com.cnu.iqas.dao.iword.IwordDao;
import com.cnu.iqas.dao.iword.WordThemeDao;
import com.cnu.iqas.service.iword.WordThemeService;

/**
* @author 周亮 
* @version 创建时间：2015年12月7日 下午6:55:14
* 类说明
*/
@Service("wordThemeService")
public class WordThemeServiceImpl implements WordThemeService {
	//单词主题数据访问类
	private WordThemeDao wordThemeDao;
	//单词数据访问类
	private IwordDao iwordDao;
	
	/**
	 * 根据主题类型获取该类型下所有单词
	 * @param type  主题类型
	 * @return  查询结果类
	 */
	@Override
	public QueryResult<Iword> getAllWords(String themeid) {
		// TODO Auto-generated method stub
		
		return wordThemeDao.getAllWords(themeid);
	}

	/**
	 * 查询所有主题
	 * @return 查询结果类
	 */
	@Override
	public QueryResult<WordTheme> getThemes() {
		return wordThemeDao.getScrollData();
	}

	@Override
	public void save(WordTheme entity) {
		if( entity.getType()==null)
			throw new RuntimeException("请确定主题类型！");
		wordThemeDao.save(entity);
	}
	@Override
	public WordTheme findByType(int type) {
			return wordThemeDao.findByType(type);
	}

	
	public WordThemeDao getWordThemeDao() {
		return wordThemeDao;
	}
	@Resource
	public void setWordThemeDao(WordThemeDao wordThemeDao) {
		this.wordThemeDao = wordThemeDao;
	}

	public IwordDao getIwordDao() {
		return iwordDao;
	}
	@Resource
	public void setIwordDao(IwordDao iwordDao) {
		this.iwordDao = iwordDao;
	}

}
