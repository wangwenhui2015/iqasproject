package com.cnu.iqas.service.iword.impl;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cnu.iqas.bean.base.QueryResult;
import com.cnu.iqas.bean.iword.WordResource;
import com.cnu.iqas.dao.base.DaoSupport;
import com.cnu.iqas.service.iword.WordResourceService;

/**
* @author 周亮 
* @version 创建时间：2015年11月23日 上午11:38:23
* 类说明 : 单词资源服务接口的实现类
*/
@Service("wordResourceService")
public class WordResourceServiceImpl extends DaoSupport<WordResource>implements WordResourceService {

	@Override
	public List<WordResource> findResources(String uuid) {
		// TODO Auto-generated method stub
		
		return null;
	}

}
