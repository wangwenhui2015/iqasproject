package com.cnu.iqas.controller.web.ontology;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cnu.iqas.bean.Recommend.Answer;
import com.cnu.iqas.bean.Recommend.Question;
import com.cnu.iqas.bean.Recommend.QuestionAnswer;
import com.cnu.iqas.bean.iword.Iword;
import com.cnu.iqas.bean.ontology.ISentence;
import com.cnu.iqas.service.Recommend.AnswerService;
import com.cnu.iqas.service.Recommend.QuestionAnswerService;
import com.cnu.iqas.service.Recommend.QuestionService;
import com.cnu.iqas.service.ontology.SentenceSim;

@Controller
@RequestMapping(value="/search")
public class SearchController {
	
	private SentenceSim sentenceSim ;
	private QuestionService questionService;
	private AnswerService  answerService;
	private QuestionAnswerService questionAnswerService;
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
		    	Iword iword=sentenceSim.findWordProperty(text);
		    	if( iword !=null ){
					mv.addObject("word", iword);
					mv.setViewName("/front/search/wordresult");
		    	}
	    	}
	    	else
	    	{
	    		//根据句子进行查询
		        System.out.println("根据句子进行查询");
		        ISentence sentence=sentenceSim.maxSimilar(text);
		        if(sentence!=null)
		        {
				mv.addObject("text",sentence.getInstanceLabel());
				mv.addObject("tempAnswer", sentence.getPropertyAnswer());
				mv.addObject("tempVersion", sentence.getPropertyVersion());
				mv.addObject("tempBook", sentence.getPropertyBook());
				mv.addObject("tempScene",sentence.getPropertyScene());
				mv.addObject("tempPattern", sentence.getPropertySentencePattern());
				Question question=new Question();
				question.setContent(sentence.getInstanceLabel());
				question.setType("1");//1表示句型，2表示单词
				question.setCreateDate(new Date());
				question.setUserid("2141002068");//学号从前台获取
			    questionService.save(question);
			    //将数据保存在Answer表中
			    Answer answer=new Answer();
			    answer.setContent(sentence.getPropertyAnswer());
			    answer.setAttributes("回答");
			    answer.setMediaType("文本");
			    answer.setDifficulty("中");
			    answer.setChecked(false);
			    answer.setAddType("自动");
			    answer.setCreateDate(new Date());
			    answerService.save(answer);
			    //将对应的数据信息保存在answer-question表中
			    QuestionAnswer questionAnswer=new QuestionAnswer();
			    questionAnswer.setAnswerId(answer.getAnswerId());
			    questionAnswer.setQuestionId(question.getQuestionId());
			    questionAnswer.setCreateDate(new Date());
			    questionAnswerService.save(questionAnswer);
				mv.setViewName("/front/search/result");
		        }else{
		        mv.setViewName("/front/search/help");	
		        }
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
	public QuestionService getQuestionService() {
		return questionService;
	}
	@Resource
	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}

	public AnswerService getAnswerService() {
		return answerService;
	}
	@Resource
	public void setAnswerService(AnswerService answerService) {
		this.answerService = answerService;
	}

	public QuestionAnswerService getQuestionAnswerService() {
		return questionAnswerService;
	}
	@Resource
	public void setQuestionAnswerService(QuestionAnswerService questionAnswerService) {
		this.questionAnswerService = questionAnswerService;
	}
	
	

	    	
}