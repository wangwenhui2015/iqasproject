package com.cnu.iqas.controller.web.admin;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;
import com.cnu.iqas.bean.Recommend.Answer;
import com.cnu.iqas.constant.PageViewConstant;
import com.cnu.iqas.service.Recommend.AnswerService;

@Controller
@RequestMapping(value = "/admin/control/qa/")
public class QAController implements ServletContextAware {
	private Logger log = LogManager.getLogger(QAController.class);
	// 应用对象
	private ServletContext servletContext;
	private AnswerService answerService;
	private List<Answer> listcheckMessage;

	@RequestMapping(value = "listanswermessage")
	public ModelAndView listanswermessage(Answer answer) {

		System.out.println("listanswermessage");
		ModelAndView mv = new ModelAndView(PageViewConstant.ANSWER_LIST);
		List<Object> checkMessage = new ArrayList<Object>();
		checkMessage.add("2");
		listcheckMessage = answerService.getAllData("o.checked= ?", checkMessage.toArray());
		if (listcheckMessage != null && listcheckMessage.size() > 0) {
			System.out.println(listcheckMessage.get(0).getContent());
		}
		mv.addObject("listcheckMessage", listcheckMessage);
		return mv;
	}

	@RequestMapping(value = "loadMessage")
	public ModelAndView loadMessage(int id) {
		ModelAndView mv = new ModelAndView(PageViewConstant.UPDATE_MESSAGE);
		Answer answer = answerService.find("o.answerId=?", id);
		mv.addObject("answer", answer);
		System.out.println("loadMessage");
		System.out.println(id);
		return mv;
	}

	@RequestMapping(value = "updatemessage")
	public ModelAndView updatemessage(Answer answer) {
		System.out.println("updatemessage!!!!");
		System.out.println(answer.getAnswerId());
		ModelAndView mv = new ModelAndView(PageViewConstant.SUCCESS);
		answerService.update(answer);
		return mv;
	}

	public ServletContext getServletContext() {
		return servletContext;
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		// TODO Auto-generated method stub
	}

	public AnswerService getAnswerService() {
		return answerService;
	}

	@Resource
	public void setAnswerService(AnswerService answerService) {
		this.answerService = answerService;
	}
}
