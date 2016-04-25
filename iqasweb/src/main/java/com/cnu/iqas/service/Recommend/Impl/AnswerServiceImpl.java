package com.cnu.iqas.service.Recommend.Impl;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.cnu.iqas.bean.Recommend.Answer;
import com.cnu.iqas.dao.Recommend.AnswerDao;
import com.cnu.iqas.service.Recommend.AnswerService;
@Service("answerService")
public class AnswerServiceImpl implements AnswerService{
	
    private AnswerDao answerDao;
    @Override
	public void save(Answer answer) {
		System.out.println("answer save");
		answerDao.save(answer);
		System.out.println("answer saved!!!!!");
		}
	public AnswerDao getAnswerDao() {
		return answerDao;
	}
	@Resource
	public void setAnswerDao(AnswerDao answerDao) {
		this.answerDao = answerDao;
	}
	
}