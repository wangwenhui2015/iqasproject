package com.cnu.iqas.controller.web.ontology;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
@RequestMapping(value = "/search")
public class SearchController {

	private SentenceSim sentenceSim;
	private QuestionService questionService;
	private AnswerService answerService;
	private QuestionAnswerService questionAnswerService;

	/**
	 * 方法说明：在查询不到资源的情况可以采用人工添加的方式
	 * 
	 * @return
	 */
	@RequestMapping(value = "/addResource")
	public ModelAndView addResource(String text) {
		Iword iword = sentenceSim.findWordProperty(text);
		ModelAndView mv = new ModelAndView();
		// 将数据保存在问题表
		Question tempquestion = new Question();
		tempquestion.setContent(text);
		tempquestion.setType("2");// 1表示句型，2表示单词
		tempquestion.setCreateDate(new Date());
		tempquestion.setUserid("2141002068");// 学号从前台获取
		questionService.save(tempquestion);
		// 将数据保存在Answer表中
		Answer answer = new Answer();
		answer.setContent(text);
		answer.setAttributes("回答");
		answer.setMediaType("文本");
		answer.setDifficulty("中");
		answer.setChecked("0");
		answer.setAddType("自动");
		answer.setCreateDate(new Date());
		answerService.save(answer);
		// 将对应的数据信息保存在answer-question表中
		QuestionAnswer questionAnswer = new QuestionAnswer();
		questionAnswer.setAnswerId(answer.getAnswerId());
		questionAnswer.setQuestionId(tempquestion.getQuestionId());
		questionAnswer.setCreateDate(new Date());
		questionAnswerService.save(questionAnswer);
		if (iword != null) {
			mv.addObject("word", iword);
			mv.setViewName("/front/search/wordresult");
		} else {
			mv.setViewName("/front/search/help");
		}
		return mv;
	}

	/**
	 * @return
	 */
	@RequestMapping(value = "/Resource")
	public ModelAndView searchSentence(HttpSession httpSession) {
		System.out.println("searchSentence!!!");
		ModelAndView mv = new ModelAndView();
		String text = (String) httpSession.getAttribute("text");
		System.out.println("传过来的数据是" + text);
		Question question = questionService.find("o.content = ?", text);
		if (question != null) {
			System.out.println(question.getContent());
			// 获取了问题ID
			int questionId = question.getQuestionId();
			Map<String, String> mediaMap = new HashMap<String, String>();
			// 保存联想图片到Map中
			List<Object> associationpic = new ArrayList<Object>();
			associationpic.add(questionId);
			associationpic.add("1");
			associationpic.add("联想");
			associationpic.add("图片");
			List<Answer> listassociationpic = answerService.getAllData(
					"o.questionId = ? and o.checked= ? and o.attributes=? and o.mediaType=?", associationpic.toArray());
			System.out.println(listassociationpic.get(0).getMediaUrl());
			mediaMap.put("associationpic", listassociationpic.get(0).getMediaUrl());
			// 保存联想音频到Map中
			List<Object> associationaud = new ArrayList<Object>();
			associationaud.add(questionId);
			associationaud.add("1");
			associationaud.add("联想");
			associationaud.add("音频");
			List<Answer> listassociationaud = answerService.getAllData(
					"o.questionId = ? and o.checked= ? and o.attributes=? and o.mediaType=?", associationaud.toArray());
			System.out.println(listassociationaud.get(0).getMediaUrl());
			mediaMap.put("associationaud", listassociationaud.get(0).getMediaUrl());
			// 保存联想文本到Map中
			List<Object> associationtxt = new ArrayList<Object>();
			associationtxt.add(questionId);
			associationtxt.add("1");
			associationtxt.add("联想");
			associationtxt.add("文本");
			List<Answer> listassociationtxt = answerService.getAllData(
					"o.questionId = ? and o.checked= ? and o.attributes=? and o.mediaType=?", associationtxt.toArray());
			System.out.println(listassociationtxt.get(0).getContent());
			mediaMap.put("associationtxt", listassociationtxt.get(0).getContent());

			// 保存同义词图片到Map中
			List<Object> Synonymspic = new ArrayList<Object>();
			Synonymspic.add(questionId);
			Synonymspic.add("1");
			Synonymspic.add("同义词");
			Synonymspic.add("图片");
			List<Answer> listSynonymspic = answerService.getAllData(
					"o.questionId = ? and o.checked= ? and o.attributes=? and o.mediaType=?", Synonymspic.toArray());
			System.out.println(listSynonymspic.get(0).getMediaUrl());
			mediaMap.put("Synonymspic", listSynonymspic.get(0).getMediaUrl());
			// 保存同义词音频到Map中
			List<Object> Synonymsaud = new ArrayList<Object>();
			Synonymsaud.add(questionId);
			Synonymsaud.add("1");
			Synonymsaud.add("同义词");
			Synonymsaud.add("音频");
			List<Answer> listSynonymsaud = answerService.getAllData(
					"o.questionId = ? and o.checked= ? and o.attributes=? and o.mediaType=?", Synonymsaud.toArray());
			System.out.println(listSynonymsaud.get(0).getMediaUrl());
			mediaMap.put("Synonymsaud", listSynonymsaud.get(0).getMediaUrl());
			// 保存同义词文本到Map中
			List<Object> Synonymstxt = new ArrayList<Object>();
			Synonymstxt.add(questionId);
			Synonymstxt.add("1");
			Synonymstxt.add("同义词");
			Synonymstxt.add("文本");
			List<Answer> listSynonymstxt = answerService.getAllData(
					"o.questionId = ? and o.checked= ? and o.attributes=? and o.mediaType=?", Synonymstxt.toArray());
			System.out.println(listSynonymstxt.get(0).getContent());
			mediaMap.put("Synonymstxt", listSynonymstxt.get(0).getContent());

			// 保存反义词图片到Map中
			List<Object> Antonympic = new ArrayList<Object>();
			Antonympic.add(questionId);
			Antonympic.add("1");
			Antonympic.add("反义词");
			Antonympic.add("图片");
			List<Answer> listAntonympic = answerService.getAllData(
					"o.questionId = ? and o.checked= ? and o.attributes=? and o.mediaType=?", Antonympic.toArray());
			System.out.println(listAntonympic.get(0).getMediaUrl());
			mediaMap.put("Antonympic", listAntonympic.get(0).getMediaUrl());
			// 保存反义词音频到Map中
			List<Object> Antonymaud = new ArrayList<Object>();
			Antonymaud.add(questionId);
			Antonymaud.add("1");
			Antonymaud.add("反义词");
			Antonymaud.add("音频");
			List<Answer> listAntonymaud = answerService.getAllData(
					"o.questionId = ? and o.checked= ? and o.attributes=? and o.mediaType=?", Antonymaud.toArray());
			System.out.println(listAntonymaud.get(0).getMediaUrl());
			mediaMap.put("Antonymaud", listAntonymaud.get(0).getMediaUrl());
			// 保存反义词文本到Map中
			List<Object> Antonymtxt = new ArrayList<Object>();
			Antonymtxt.add(questionId);
			Antonymtxt.add("1");
			Antonymtxt.add("反义词");
			Antonymtxt.add("文本");
			List<Answer> listAntonymtxt = answerService.getAllData(
					"o.questionId = ? and o.checked= ? and o.attributes=? and o.mediaType=?", Antonymtxt.toArray());
			System.out.println(listAntonymtxt.get(0).getContent());
			mediaMap.put("Antonymtxt", listAntonymtxt.get(0).getContent());

			// 保存拓展图片到Map中
			List<Object> Expandpic = new ArrayList<Object>();
			Expandpic.add(questionId);
			Expandpic.add("1");
			Expandpic.add("拓展");
			Expandpic.add("图片");
			List<Answer> listExpandpic = answerService.getAllData(
					"o.questionId = ? and o.checked= ? and o.attributes=? and o.mediaType=?", Expandpic.toArray());
			System.out.println(listExpandpic.get(0).getMediaUrl());
			mediaMap.put("Expandpic", listExpandpic.get(0).getMediaUrl());
			// 保存拓展音频到Map中
			List<Object> Expandaud = new ArrayList<Object>();
			Expandaud.add(questionId);
			Expandaud.add("1");
			Expandaud.add("拓展");
			Expandaud.add("音频");
			List<Answer> listExpandaud = answerService.getAllData(
					"o.questionId = ? and o.checked= ? and o.attributes=? and o.mediaType=?", Expandaud.toArray());
			System.out.println(listExpandaud.get(0).getMediaUrl());
			mediaMap.put("Expandaud", listExpandaud.get(0).getMediaUrl());
			// 保存拓展文本到Map中
			List<Object> Expandtxt = new ArrayList<Object>();
			Expandtxt.add(questionId);
			Expandtxt.add("1");
			Expandtxt.add("拓展");
			Expandtxt.add("文本");
			List<Answer> listExpandtxt = answerService.getAllData(
					"o.questionId = ? and o.checked= ? and o.attributes=? and o.mediaType=?", Expandtxt.toArray());
			System.out.println(listExpandtxt.get(0).getContent());
			mediaMap.put("Expandtxt", listExpandtxt.get(0).getContent());

			// 保存常用图片到Map中
			List<Object> CommonUsepic = new ArrayList<Object>();
			CommonUsepic.add(questionId);
			CommonUsepic.add("1");
			CommonUsepic.add("常用");
			CommonUsepic.add("图片");
			List<Answer> listCommonUsepic = answerService.getAllData(
					"o.questionId = ? and o.checked= ? and o.attributes=? and o.mediaType=?", CommonUsepic.toArray());
			System.out.println(listCommonUsepic.get(0).getMediaUrl());
			mediaMap.put("CommonUsepic", listCommonUsepic.get(0).getMediaUrl());
			// 保存常用音频到Map中
			List<Object> CommonUseaud = new ArrayList<Object>();
			CommonUseaud.add(questionId);
			CommonUseaud.add("1");
			CommonUseaud.add("常用");
			CommonUseaud.add("音频");
			List<Answer> listCommonUseaud = answerService.getAllData(
					"o.questionId = ? and o.checked= ? and o.attributes=? and o.mediaType=?", CommonUseaud.toArray());
			System.out.println(listCommonUseaud.get(0).getMediaUrl());
			mediaMap.put("CommonUseaud", listCommonUseaud.get(0).getMediaUrl());
			// 保存常用文本到Map中
			List<Object> CommonUsetxt = new ArrayList<Object>();
			CommonUsetxt.add(questionId);
			CommonUsetxt.add("1");
			CommonUsetxt.add("常用");
			CommonUsetxt.add("文本");
			List<Answer> listCommonUsetxt = answerService.getAllData(
					"o.questionId = ? and o.checked= ? and o.attributes=? and o.mediaType=?", CommonUsetxt.toArray());
			System.out.println(listCommonUsetxt.get(0).getContent());
			mediaMap.put("CommonUsetxt", listCommonUsetxt.get(0).getContent());

			mv.addObject("media", mediaMap);
			mv.setViewName("/front/search/wordresult/resourceshow2");
		}
		return mv;
	}

	/**
	 * 用户输入问句后首先进行判断，如果是单词跳转到查询单词的页面，如果是查询句子，跳转到查询句子的页面。
	 * 
	 * @param queryParams
	 * 
	 * @param request
	 * @param response
	 * @return 相关的页面
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/sentence")
	public ModelAndView searchSentence(HttpSession httpSession, String text) {
		System.out.println("searchSentence!!!!");
		ModelAndView mv = new ModelAndView();
		if (text.trim().length() < 15) {
			httpSession.setAttribute("text", text);
			Question question = questionService.find("o.content = ?", text);
			if (question != null) {
				System.out.println(question.getContent());
				// 获取了问题ID
				int questionId = question.getQuestionId();
				List<Object> list1 = new ArrayList<Object>();
				list1.add(questionId);
				// 查询答案信息列表
				List<Answer> Answer = answerService.getAllData("o.questionId = ?", list1.toArray());
				// 答案列表的长度
				int TT = Answer.size();
				System.out.println("长度是" + TT);
				// 创建一个list集合来存储数据
				/* List<Object> listnum=new ArrayList<Object>(); */
				// 创建一个map集合来存放数据
				Map<String, String> tempMap = new HashMap<String, String>();
				// 保存课文原句的text到Map中
				List<Object> propertyText = new ArrayList<Object>();
				propertyText.add(questionId);
				propertyText.add("1");
				propertyText.add("课文原句");
				List<Answer> listpropertyText = answerService
						.getAllData("o.questionId = ? and o.checked= ? and o.attributes=?", propertyText.toArray());
				System.out.println(listpropertyText.get(0).getContent());
				tempMap.put("propertyText", listpropertyText.get(0).getContent());
				// 保存情景段落的text到Map中
				List<Object> propertyScene = new ArrayList<Object>();
				propertyScene.add(questionId);
				propertyScene.add("1");
				propertyScene.add("情境段落");
				List<Answer> listpropertyScene = answerService
						.getAllData("o.questionId = ? and o.checked= ? and o.attributes=?", propertyScene.toArray());
				tempMap.put("propertyScene", listpropertyScene.get(0).getContent());
				System.out.println(listpropertyScene.get(0).getContent());
				// 保存延伸例句的text到Map中
				List<Object> propertyExtend = new ArrayList<Object>();
				propertyExtend.add(questionId);
				propertyExtend.add("1");
				propertyExtend.add("延伸例句");
				List<Answer> listpropertyExtend = answerService
						.getAllData("o.questionId = ? and o.checked= ? and o.attributes=?", propertyExtend.toArray());
				tempMap.put("propertyExtend", listpropertyExtend.get(0).getContent());
				System.out.println(listpropertyExtend.get(0).getContent());
				// 保存百科的text到Map中
				List<Object> propertyNcyclopedia = new ArrayList<Object>();
				propertyNcyclopedia.add(questionId);
				propertyNcyclopedia.add("1");
				propertyNcyclopedia.add("百科");
				List<Answer> listpropertyNcyclopedia = answerService.getAllData(
						"o.questionId = ? and o.checked= ? and o.attributes=?", propertyNcyclopedia.toArray());
				tempMap.put("propertyNcyclopedia", listpropertyNcyclopedia.get(0).getContent());
				System.out.println(listpropertyNcyclopedia.get(0).getContent());
				// 保存用法的text到Map中
				List<Object> propertyUse = new ArrayList<Object>();
				propertyUse.add(questionId);
				propertyUse.add("1");
				propertyUse.add("用法");
				List<Answer> listpropertyUse = answerService
						.getAllData("o.questionId = ? and o.checked= ? and o.attributes=?", propertyUse.toArray());
				tempMap.put("propertyUse", listpropertyUse.get(0).getContent());
				System.out.println(listpropertyUse.get(0).getContent());
				mv.addObject("listResource", tempMap);
				mv.setViewName("/front/search/wordresult/resourceshow");
			} else {
				// 在数据表中查询不到的情况下在本体库中查询
				mv.setViewName("/front/search/wordresult/resourceshow");
			}

		} else {
			System.out.println("进入句子数据库查询");
			Map<String, String> sentenceMap = new HashMap<String, String>();
			Question question = questionService.find("o.content = ?", text);
			if (question != null) {
				System.out.println(question.getContent());
				// 获取了问题ID
				int questionId = question.getQuestionId();
				List<Object> list1 = new ArrayList<Object>();
				list1.add(questionId);
				// 查询答案信息列表
				List<Answer> Answer = answerService.getAllData("o.questionId = ?", list1.toArray());
				// 答案列表的长度
				int TT = Answer.size();
				System.out.println("长度是" + TT);
				// 保存句子问题到Map中
				List<Object> sentencequestion = new ArrayList<Object>();
				sentencequestion.add(questionId);
				sentencequestion.add("1");
				sentencequestion.add("问题");
				List<Answer> listsentencequestion = answerService
						.getAllData("o.questionId = ? and o.checked= ? and o.attributes=?", sentencequestion.toArray());
				System.out.println(listsentencequestion.get(0).getContent());
				sentenceMap.put("sentencequestion", listsentencequestion.get(0).getContent());
				// 保存句子回答到Map中
				List<Object> sentenceanswer = new ArrayList<Object>();
				sentenceanswer.add(questionId);
				sentenceanswer.add("1");
				sentenceanswer.add("回答");
				List<Answer> listsentenceanswer = answerService
						.getAllData("o.questionId = ? and o.checked= ? and o.attributes=?", sentenceanswer.toArray());
				System.out.println(listsentenceanswer.get(0).getContent());
				sentenceMap.put("sentenceanswer", listsentenceanswer.get(0).getContent());
				// 保存句子情景对话到Map中
				List<Object> sentencedialogue = new ArrayList<Object>();
				sentencedialogue.add(questionId);
				sentencedialogue.add("1");
				sentencedialogue.add("情景对话");
				List<Answer> listsentencedialogue = answerService
						.getAllData("o.questionId = ? and o.checked= ? and o.attributes=?", sentencedialogue.toArray());
				System.out.println(listsentencedialogue.get(0).getContent());
				sentenceMap.put("sentencedialogue", listsentencedialogue.get(0).getContent());
				// 保存句子本课重要句型对话到Map中
				List<Object> sentencepattern = new ArrayList<Object>();
				sentencepattern.add(questionId);
				sentencepattern.add("1");
				sentencepattern.add("本课重要句型");
				List<Answer> listsentencepattern = answerService
						.getAllData("o.questionId = ? and o.checked= ? and o.attributes=?", sentencepattern.toArray());
				System.out.println(listsentencepattern.get(0).getContent());
				sentenceMap.put("sentencepattern", listsentencepattern.get(0).getContent());
				// 保存句子本课课后单词对话到Map中
				List<Object> sentencerelatedwords = new ArrayList<Object>();
				sentencerelatedwords.add(questionId);
				sentencerelatedwords.add("1");
				sentencerelatedwords.add("本课课后单词");
				List<Answer> listsentencerelatedwords = answerService
						.getAllData("o.questionId = ? and o.checked= ? and o.attributes=?", sentencerelatedwords.toArray());
				System.out.println(listsentencerelatedwords.get(0).getContent());
				sentenceMap.put("sentencerelatedwords", listsentencerelatedwords.get(0).getContent());
				mv.addObject("listsentence", sentenceMap);
				mv.setViewName("/front/search/wordresult/sentenceshow");
			} else {
				// 在二维数据表中无法查询的时候，在本体库中进行查询。根据句子进行查询
				System.out.println("根据句子本体库查询");
				ISentence sentence = sentenceSim.maxSimilar(text);
				if (sentence != null) {
					System.out.println("进入本体查询！");
					sentenceMap.put("sentencequestion",sentence.getInstanceLabel() );
					sentenceMap.put("sentenceanswer",sentence.getPropertyAnswer() );
					sentenceMap.put("sentencedialogue",sentence.getPropertyScene() );
					sentenceMap.put("sentencepattern",sentence.getPropertySentencePattern() );
					sentenceMap.put("sentencerelatedwords",sentence.getPropertyRelatedWords());
					mv.addObject("listsentence", sentenceMap);
					mv.setViewName("/front/search/wordresult/sentenceshow");
				} else {
					mv.setViewName("/front/search/help");
				}
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