package com.cnu.iqas.controller.web.admin;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cnu.iqas.bean.iword.Iword;
import com.cnu.iqas.bean.iword.WordResource;
import com.cnu.iqas.constant.PageViewConstant;
import com.cnu.iqas.controller.web.ontology.FileController;
import com.cnu.iqas.formbean.BaseForm;
import com.cnu.iqas.formbean.iword.WordResourceForm;
import com.cnu.iqas.service.iword.IwordService;
import com.cnu.iqas.service.iword.WordResourceService;

import riotcmd.infer;

/**
* @author 周亮 
* @version 创建时间：2015年11月23日 上午11:30:45
* 类说明  单词资源控制类，负责单词自身资源的增、删、查、改
*/
@Controller
@RequestMapping(value="/admin/control/wordresource")
public class WordResourceController  implements ServletContextAware{
	//日志类
	private final static Logger logger = LogManager.getLogger(WordResourceController.class);
	 //单词资源服务类
	 private WordResourceService wordResourceService;
	 //应用对象
	 private ServletContext servletContext;
	 //单词服务类
	 private IwordService iwordService;
	 
	 //添加资源界面
	 @RequestMapping(value="addResourceUI")
	 public ModelAndView addResourceUI(String uuid){
		 ModelAndView mv = new ModelAndView(PageViewConstant.ADDWORDRESOURCE);
		 Iword word = iwordService.find(uuid);
		 if( word == null){
			 mv.setViewName(PageViewConstant.MESSAGE);
		      mv.addObject("message","该单词不存在！");
		 }else{
			 mv.addObject("word", word);}
		 
		 return mv;
	 }
	 
	 //进入资源详情界面
	 @RequestMapping(value="resourceDetailUI")
	 public ModelAndView resourceDetailUI(String uuid){
		 ModelAndView mv = new ModelAndView(PageViewConstant.WORD_RESOURCE_DETAILUI);
		 Iword word = iwordService.find(uuid);
		 if( word == null){
			 mv.setViewName(PageViewConstant.MESSAGE);
		      mv.addObject("message","该单词不存在！");
		 }else{
			 mv.addObject("word", word);}
		 
		 return mv;
	 }
	 /**
	  * 添加单词资源，该方法应该具有事务属性，暂时还未加
	  * 
	  * @return
	  */
	 @RequestMapping(value="/addResource")
	 public ModelAndView addResource(WordResourceForm formbean,  @RequestParam("file") CommonsMultipartFile  file){
		 //默认保存失败
		 boolean flage = false;
		 ModelAndView mv = new ModelAndView();
		 //1.根据单词id,获取单词
		 Iword word= null;
		 if( BaseForm.validate(formbean.getUuid())){
			word= iwordService.find(formbean.getUuid());
		 }
		 if( null !=word ){
			//1.对文件的格式和大小进行判断
			 if(formbean.validateResourceTypeAndSize(file, formbean.getType())){
				//2.保存文件，返回文件保存路径
				 //单词原名称
				 String fileName = file.getOriginalFilename();
				 //3.资源的保存相对路径
				 String filesavepath=null;
				 try {
					 filesavepath=formbean.saveFile(servletContext, file, formbean.getType());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					formbean.getErrors().put("error", "上传文件有误！");
					logger.error("上传单词资源：出现错误");
				}
				//4.建立资源类保存信息
				 WordResource resource = new WordResource();
				 //5.设置资源所属的单词
				 resource.setIword(word);
				 resource.setName(fileName);//单词原名称
				 resource.setSavepath(filesavepath);//包含文件名的相对保存路径
				 resource.setType(formbean.getType());//资源类型
				 //保存到数据库
				 wordResourceService.save(resource);
				 flage = true;//保存成功
			 }
		 }else{
			 formbean.getErrors().put("error", "该资源对应的单词不存在!");
		 }
		 
		 mv.setViewName(PageViewConstant.MESSAGE);
		 if( flage ){
			 mv.addObject("message","保存成功");
		 }
		 else{
			 mv.addObject("message", formbean.getErrors().get("error"));
		 }
		 //mv.addObject("uuid", word.getUuid());
		 mv.addObject("urladdress", "/admin/control/wordresource/addResourceUI.html?uuid="+word.getUuid());
		 return mv;
	 }
	 
	public WordResourceService getWordResourceService() {
		return wordResourceService;
	}
	@Resource
	public void setWordResourceService(WordResourceService wordResourceService) {
		this.wordResourceService = wordResourceService;
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		// TODO Auto-generated method stub
		this.servletContext = servletContext;
	}

	public IwordService getIwordService() {
		return iwordService;
	}
	@Resource
	public void setIwordService(IwordService iwordService) {
		this.iwordService = iwordService;
	}
	
}
