package com.cnu.iqas.service.iword.impl;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cnu.iqas.bean.base.QueryResult;
import com.cnu.iqas.bean.iword.WordAttributeResource;
import com.cnu.iqas.bean.iword.WordResource;
import com.cnu.iqas.dao.base.DaoSupport;
import com.cnu.iqas.service.iword.WordAttributeResourceService;
import com.cnu.iqas.service.iword.WordResourceService;

/**
* @author 王文辉
* @version 创建时间：2015年12月2日 上午16:21:23
* 类说明 : 单词属性资源服务接口的实现类
*/
@Service("wordAttributeResourceService")
public class WordAttributeResourceServiceImpl extends DaoSupport<WordAttributeResource>implements WordAttributeResourceService {

	@Override
	public List<WordAttributeResource> findResources(String id) {
		// TODO Auto-generated method stub
		
		return null;
	}

}
