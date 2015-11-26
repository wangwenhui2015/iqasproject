package com.cnu.iqas.dao.iword;

import java.util.List;

import com.cnu.iqas.bean.iword.WordResource;

/**
* @author 周亮 
* @version 创建时间：2015年11月26日 下午7:21:49
* 类说明  单词资源数据库访问层
*/
public interface WordResourceDao {
	
	public List<WordResource> findByWord(String uuid);
}
