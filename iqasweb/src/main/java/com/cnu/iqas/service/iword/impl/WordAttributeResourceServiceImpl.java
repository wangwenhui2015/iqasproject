package com.cnu.iqas.service.iword.impl;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.cnu.iqas.bean.base.QueryResult;
import com.cnu.iqas.bean.iword.WordAttributeResource;
import com.cnu.iqas.bean.iword.WordResource;
import com.cnu.iqas.constant.ResourceConstant;
import com.cnu.iqas.dao.base.DaoSupport;
import com.cnu.iqas.dao.iword.WordAttributeResourceDao;
import com.cnu.iqas.formbean.BaseForm;
import com.cnu.iqas.service.iword.WordAttributeResourceService;
import com.cnu.iqas.service.iword.WordResourceService;
import com.cnu.iqas.utils.PropertyUtils;

/**
* @author 王文辉
* @version 创建时间：2015年12月2日 上午16:21:23
* 类说明 : 单词属性资源服务接口的实现类
*/
@Service("wordAttributeResourceService")
public class WordAttributeResourceServiceImpl implements WordAttributeResourceService {
	
	private WordAttributeResourceDao wordAttributeResourceDao;
	@Override
	public String saveWordResourceFile(ServletContext servletContext, CommonsMultipartFile file, int filetype) throws Exception {
		//获取单词资源的文件保存的相对路径
		   String relativepath=null;
		   switch(filetype){
			    case ResourceConstant.TYPE_IMAGE: //上传图片类型文件
			    	relativepath = PropertyUtils.get("word.imagesavedir");  //获取本地存储路径
			    	break;
			    case ResourceConstant.TYPE_VOICE://声音
			    	relativepath = PropertyUtils.get("word.voicesavedir");  //获取本地存储路径
			    	break;
			    case ResourceConstant.TYPE_VIDEO://视频
			    	relativepath = PropertyUtils.get("word.videosavedir");  //获取本地存储路径
			    	break;
			    case ResourceConstant.TYPE_PICTUREBOOK://绘本
			    	relativepath = PropertyUtils.get("word.picturebooksavedir");  //获取本地存储路径
			    	break;
			    default:
			    		break;
		   }
		return BaseForm.saveFile(servletContext, relativepath, file);
	}

	@Override
	public void save(WordAttributeResource resourceattribute) {
		// TODO Auto-generated method stub
		wordAttributeResourceDao.save(resourceattribute);
	}

	public WordAttributeResourceDao getWordAttributeResourceDao() {
		return wordAttributeResourceDao;
	}
	@Resource
	public void setWordAttributeResourceDao(WordAttributeResourceDao wordAttributeResourceDao) {
		this.wordAttributeResourceDao = wordAttributeResourceDao;
	}

}
