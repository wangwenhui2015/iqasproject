package com.cnu.iqas.service.iword;

import java.util.List;

import com.cnu.iqas.bean.iword.WordResource;
import com.cnu.iqas.dao.base.DAO;

/**
* @author 周亮 
* @version 创建时间：2015年11月23日 上午11:34:22
* 类说明  单词资源服务接口
*/
public interface WordResourceService extends DAO<WordResource> {

	public List<WordResource> findResources(String uuid);
}
