package com.cnu.iqas.controller.web.admin;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cnu.iqas.bean.base.MyStatus;
import com.cnu.iqas.bean.base.PageView;
import com.cnu.iqas.bean.base.QueryResult;
import com.cnu.iqas.bean.iword.Iword;
import com.cnu.iqas.bean.iword.WordResource;
import com.cnu.iqas.bean.iword.WordTheme;
import com.cnu.iqas.constant.PageViewConstant;
import com.cnu.iqas.formbean.BaseForm;
import com.cnu.iqas.formbean.iword.WordThemeForm;
import com.cnu.iqas.service.iword.WordThemeService;
import com.cnu.iqas.utils.JsonTool;

/**
* @author 周亮 
* @version 创建时间：2015年12月8日 上午10:13:13
* 类说明   单词主题控制类，功能有：添加主题、查询所有主题、删除主题、编辑主题、为主题添加单词、查询主题下的所有单词、删除主题下的某个单词
*/
@Controller
@RequestMapping(value="admin/control/wordtheme/")
public class WordThemeController {
	//主题服务类
	private WordThemeService wordThemeService; 
	/**
	 * 跳转到添加主题界面
	 * @return
	 */
	@RequestMapping(value="addUI")
	public String addThemeUI(){
		return PageViewConstant.WORDTHEME_ADD_UI;
	}
	
	/**
	 * 添加单词主题，添加失败会在错误map集合中存入key值为name的错误原因
	 * @return
	 */
	@RequestMapping(value="add")
	public ModelAndView addTheme(WordThemeForm formbean){
		ModelAndView mv = new ModelAndView(PageViewConstant.MESSAGE);
		//校验主题名称是否符合规范
		if( formbean.validateThemeName()){
			WordTheme theme = new WordTheme(formbean.getName());
			wordThemeService.save(theme);
		}
		
		mv.addObject("formbean", formbean);
		mv.addObject("message", formbean.getErrors().get("name"));
		mv.addObject("urladdress","admin/control/wordtheme/addUI.html");
		return mv;
	}
	/**
	 * 根据条件查询主题
	 * @return
	 */
	@RequestMapping(value="list")
	public ModelAndView list(WordThemeForm formbean){
		ModelAndView mv = new ModelAndView(PageViewConstant.WORDTHEME_LIST);
		//分页
		PageView<WordTheme> pageView = new PageView<WordTheme>(10, formbean.getPage());
		//查询结果根据时间排序
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String,String>();
		orderby.put("createTime", "asc");
		//构造查询语句
		StringBuilder wheresql = new StringBuilder();
		//查询条件
		List<Object> params = new ArrayList<Object>();
		if( BaseForm.validate(formbean.getName())){
			wheresql.append(" o.name like ? ");
			params.add(formbean.getName()+"%");
		}
		//查询
		QueryResult<WordTheme> qr= wordThemeService.getScrollData(pageView.getFirstResult(), pageView.getMaxresult(),wheresql.toString(), params.toArray(), orderby);
		//将查询结果存到页面
		pageView.setQueryResult(qr);
		mv.addObject("pageView", pageView);
		return mv;
	}
	
	 /**
	  * 通过ajax根据主题id编辑主题的名称
	  * @return
	  */
	 @SuppressWarnings("finally")
	@RequestMapping(value="ajaxeditName")
	 public ModelAndView ajaxeditName(WordThemeForm formbean){
		 ModelAndView mv = new ModelAndView(PageViewConstant.JSON);
		 MyStatus status = new MyStatus();
		 try {
			if(formbean.validateIdAndName()){
				 WordTheme wt =wordThemeService.findByName(formbean.getName());
				 if( wt ==null){
					 wt= wordThemeService.find(formbean.getId());
					 wt.setName(formbean.getName());
					 //更新
					 wordThemeService.update(wt);
				 }else{
					 status.setMessage("主题名称已经存在!");
					 status.setStatus(0);
				 }
			 }else{
				 status.setMessage("修改失败，注意检查名称长度哦!");
				 status.setStatus(0);
			 }
		} catch (Exception e) {
			e.printStackTrace(); 
			status.setMessage("哎呀，修改失败了!");
			status.setStatus(0);
		}finally{
			 mv.addObject("json", JsonTool.createJson(null, null, status));
			 return mv;
		}
	 }
	

	public WordThemeService getWordThemeService() {
		return wordThemeService;
	}
	@Resource
	public void setWordThemeService(WordThemeService wordThemeService) {
		this.wordThemeService = wordThemeService;
	}
}
