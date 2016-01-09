package com.cnu.iqas.service.iword;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.cnu.iqas.bean.iword.WordAttributeResource;
import com.cnu.iqas.bean.iword.WordResource;
import com.cnu.iqas.dao.base.DAO;

/**
* @author 王文辉 
* @version 创建时间：2015年12月2日 
* 类说明  单词属性资源服务接口
*/
public interface WordAttributeResourceService  {
	
	/**
	 * 保存单词资源文件，并返回保存的相对路径
	 * @param servletContext    应用上下文
	 * @param file				保存的文件
	 * @param filetype			文件类型：图片、绘本、声音、视频
	 * @return					返回文件在工程中的先对路径
	 * @throws Exception
	 */
	public String saveWordResourceFile(ServletContext servletContext, CommonsMultipartFile file, int filetype)throws Exception;
	/**
	 * 保存单词属性资源
	 * @param resourceattribute
	 */
	public void save(WordAttributeResource resourceattribute);
}
