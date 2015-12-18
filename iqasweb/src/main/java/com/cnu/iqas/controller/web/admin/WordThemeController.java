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
import com.cnu.iqas.bean.iword.WordThemeWordRel;
import com.cnu.iqas.constant.PageViewConstant;
import com.cnu.iqas.formbean.BaseForm;
import com.cnu.iqas.formbean.iword.WordThemeForm;
import com.cnu.iqas.service.iword.IwordService;
import com.cnu.iqas.service.iword.WordThemeService;
import com.cnu.iqas.service.iword.WordThemeWordRelService;
import com.cnu.iqas.utils.JsonTool;

/**
* @author 周亮 
* @version 创建时间：2015年12月8日 上午10:13:13
* 类说明   单词主题控制类，功能有：
* 添加主题、
* 查询所有主题、
* 删除主题、
* 编辑主题、
* 为主题添加单词、
* 查询主题下的所有单词、
* 删除主题下的某个单词
*/
@Controller
@RequestMapping(value="admin/control/wordtheme/")
public class WordThemeController {
	//主题服务类
	private WordThemeService wordThemeService; 
	//主题和单词关系服务类
	private WordThemeWordRelService wordThemeWordRelService;
	//单词服务类
	private IwordService iwordService;
	/**
	 * 跳转到添加主题界面
	 * @return
	 */
	@RequestMapping(value="addUI")
	public String addThemeUI(){
		return PageViewConstant.WORDTHEME_ADD_UI;
	}
	/**
	 * 根据主题id删除主题
	 * @param id 主题id
	 * @return
	 */
	public String ajaxDeleteTheme(String id){
		
		if( BaseForm.validate(id)){
			wordThemeService.disable(id);
		}
		return "";
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
		mv.addObject("message", "添加成功！");
		mv.addObject("urladdress","admin/control/wordtheme/addUI.html");
		return mv;
	}
	/**
	 * 为主题添加单词
	 * 输入：单词uuid
	 *      主题id
	 * @return
	 */
	@RequestMapping(value="addword")
	public ModelAndView addWord4Theme(WordThemeForm formbean){

		ModelAndView mv = new ModelAndView(PageViewConstant.MESSAGE);
		boolean success = false;
		if( BaseForm.validate(formbean.getId())&& BaseForm.validate(formbean.getUuid())){
			Iword word =iwordService.find(formbean.getUuid());
			WordTheme theme = wordThemeService.find(formbean.getId());
			if( word !=null&& theme!=null){

				WordThemeWordRel entity = new WordThemeWordRel(word.getUuid(), theme.getId());
				wordThemeWordRelService.save(entity);
				success = true;
			}
		}
		if( success){
			mv.addObject("message", "添加成功！");
		}else{
			mv.addObject("message", "添加失败！");
		}
		mv.addObject("urladdress","admin/control/wordtheme/addUI.html");
		return mv;
	}
	/**
	 * 分页获取主题下的单词
	 * 输入：第几页:page
	 * 		主题id:id
	 * @return
	 */
	@RequestMapping(value="wordlist")
	public ModelAndView getWords4Theme(WordThemeForm formbean){

		ModelAndView mv = new ModelAndView(PageViewConstant.WORDTHEME_WORDS_LIST);
		QueryResult<Iword> queryResult=null;
		//分页
		PageView<Iword> pageView = new PageView<Iword>(10, formbean.getPage());
		if( formbean.getId()!=null){
			
		    WordTheme theme=	wordThemeService.find(formbean.getId());
		    if( theme!=null){
			 queryResult= wordThemeService.getWords(formbean.getId(), pageView.getFirstResult(), pageView.getMaxresult());    
			 mv.addObject("theme",theme);
		    }
		}
		//将查询结果存到页面
		pageView.setQueryResult(queryResult);
		mv.addObject("pageView", pageView);
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

	public WordThemeWordRelService getWordThemeWordRelService() {
		return wordThemeWordRelService;
	}

	@Resource
	public void setWordThemeWordRelService(WordThemeWordRelService wordThemeWordRelService) {
		this.wordThemeWordRelService = wordThemeWordRelService;
	}

	public IwordService getIwordService() {
		return iwordService;
	}

	@Resource
	public void setIwordService(IwordService iwordService) {
		this.iwordService = iwordService;
	}
	
}
