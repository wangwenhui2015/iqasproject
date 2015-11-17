package com.cnu.iqas.controller.web.ontology;

import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cnu.iqas.bean.ontology.IWord;
import com.cnu.iqas.service.ontology.SentenceSim;

@Controller
@RequestMapping(value="/search")
public class SearchController {
	
	private SentenceSim sentenceSim ;
	/**
	 * 用户输入问句后首先进行判断，如果是单词跳转到查询单词的页面，如果是查询句子，跳转到查询句子的页面。
	 * @param  request
	 * @param  response
	 * @return 相关的页面
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/sentence")
	public ModelAndView searchSentence(String text){
		ModelAndView mv = new ModelAndView();
	    	if(text.trim().length()<15)
	    	{
		    	//根据单词进行查询
		    	IWord iword=sentenceSim.findWordProperty(text);
		    	if( iword !=null ){
					mv.addObject("word", iword);
					mv.setViewName("/front/search/wordresult");
		    	}
	    	}
	    	else
	    	{
		    	//根据句子进行查询
		        System.out.println("根据句子进行查询");
		        List<String>Property=sentenceSim.maxSimilar(text);
		        System.out.println(Property);
				mv.addObject("text",Property.get(0));
				mv.addObject("tempAnswer", Property.get(1));
				mv.addObject("tempVersion", Property.get(2));
				mv.addObject("tempBook", Property.get(3));
				mv.addObject("tempScene", Property.get(4));
				mv.addObject("tempPattern", Property.get(5));
				mv.setViewName("/front/search/result");
	    	}
	    	return mv;
	}

	public SentenceSim getSentenceSim() {
		return sentenceSim;
	}
	@Resource
	public void setSentenceSim(SentenceSim sentenceSim) {
		this.sentenceSim = sentenceSim;
	}

	    	
}

	