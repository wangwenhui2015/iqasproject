package com.cnu.iqas.service.common;

import java.util.List;

import com.cnu.iqas.bean.ios.SuserWord;

/**
* @author 周亮 
* @version 创建时间：2016年3月6日 下午10:19:26
* 类说明
*/
public interface IUserWordService<T> {
	/**
	 * 根据主题和用户id查询用户在该主题下的学习单词
	 * @param theme  主题
	 * @param userId 用户id
	 * @return
	 */
	public List<T> getWords(String theme,String userId);
	/**
	 * 保存用户学习单词
	 * @param entity
	 */
	public void save(T entity);
}
