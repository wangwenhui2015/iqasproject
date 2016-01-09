package com.cnu.iqas.service.iword.impl;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.cnu.iqas.bean.base.QueryResult;
import com.cnu.iqas.bean.iword.WordResource;
import com.cnu.iqas.constant.ResourceConstant;
import com.cnu.iqas.dao.base.DaoSupport;
import com.cnu.iqas.dao.iword.WordResourceDao;
import com.cnu.iqas.formbean.BaseForm;
import com.cnu.iqas.service.iword.WordResourceService;
import com.cnu.iqas.utils.PropertyUtils;

/**
* @author 周亮 
* @version 创建时间：2015年11月23日 上午11:38:23
* 类说明 : 单词资源服务接口的实现类
*/
@Service("wordResourceService")
public class WordResourceServiceImpl implements WordResourceService {

	private WordResourceDao wordResourceDao;

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
	public WordResource find(String id) {
		// TODO Auto-generated method stub
		
		return wordResourceDao.find(id);
	}

	@Override
	public void update(WordResource wr) {
		// TODO Auto-generated method stub
		wordResourceDao.update(wr);
	}
	@Override
	public void save(WordResource resource) {
		// TODO Auto-generated method stub
		wordResourceDao.save(resource);
	}
	@Override
	public List<WordResource> getAllDatas(String string, Object[] array) {
		// TODO Auto-generated method stub
		return getAllDatas(string, array);
	}

	public WordResourceDao getWordResourceDao() {
		return wordResourceDao;
	}
	@Resource
	public void setWordResourceDao(WordResourceDao wordResourceDao) {
		this.wordResourceDao = wordResourceDao;
	}
}
