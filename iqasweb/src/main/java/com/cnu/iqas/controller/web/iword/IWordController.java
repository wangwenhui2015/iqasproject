package com.cnu.iqas.controller.web.iword;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cnu.iqas.bean.ontology.Iword;
import com.cnu.iqas.formbean.iword.IwordForm;
import com.cnu.iqas.service.iword.IwordService;
import com.cnu.iqas.utils.WebUtils;

/**
* @author 周亮 
* @version 创建时间：2015年11月16日 下午10:39:54
* 类说明
*/
@Controller
@RequestMapping(value="/word")
public class IWordController {
	
	private IwordService iwordService;
	
	@RequestMapping(value="/add")
	public ModelAndView add(@Valid IwordForm formbean,BindingResult bindingResult){
		ModelAndView mv = new ModelAndView("share/json");
		if( !bindingResult.hasErrors()){
			Iword word = new Iword();
			WebUtils.copyBean(word,formbean);
			iwordService.save(word);
		}else{
			formbean.getErrors().put("result", "信息有误");
			mv.addObject("formbean", formbean);
		}
		return mv;
	}
	
	
	public IwordService getIwordService() {
		return iwordService;
	}
	@Resource
	public void setIwordService(IwordService iwordService) {
		this.iwordService = iwordService;
	}
	
	
}
