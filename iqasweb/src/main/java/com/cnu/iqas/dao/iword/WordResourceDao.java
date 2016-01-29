package com.cnu.iqas.dao.iword;

import java.util.List;

import com.cnu.iqas.bean.iword.WordResource;
import com.cnu.iqas.dao.base.DAO;

/**
* @author 周亮 
* @version 创建时间：2015年11月26日 下午7:21:49
* 类说明  单词资源数据库访问层
*/
public interface WordResourceDao  extends DAO<WordResource>{
	
	/**
	 * 根据单词id和资源类型查看单词资源
	 * @param wordId
	 * @param type
	 * @return
	 */
	public List<WordResource> findByWord(String wordId,int type);
	
	
}
