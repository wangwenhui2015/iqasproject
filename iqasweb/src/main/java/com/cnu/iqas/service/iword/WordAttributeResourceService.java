package com.cnu.iqas.service.iword;

import java.util.List;

import com.cnu.iqas.bean.iword.WordAttributeResource;
import com.cnu.iqas.bean.iword.WordResource;
import com.cnu.iqas.dao.base.DAO;

/**
* @author 王文辉 
* @version 创建时间：2015年12月2日 
* 类说明  单词属性资源服务接口
*/
public interface WordAttributeResourceService extends DAO<WordAttributeResource> {

	public List<WordAttributeResource> findResources(String id);
}
