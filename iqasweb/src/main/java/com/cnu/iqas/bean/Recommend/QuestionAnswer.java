package com.cnu.iqas.bean.Recommend;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author 王文辉
 * @version 创建时间：2016年4月20日   问题-答案表
 */
@Entity
@Table(name = "t_questionAnswer")
@GenericGenerator(strategy = "uuid", name = "uuidGenerator")
public class QuestionAnswer {
	/**
	 * id，问题标识
	 */
	private String Id;
	/**
	 * 问题主键
	 */
	private String questionId;
	/**
	 * 回答主键
	 */
	private String answerId;
	/**
	 * 创建年月日
	 */
	private Date createDate= new Date();
	@Id
	@GeneratedValue(generator = "uuidGenerator")
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	public String getAnswerId() {
		return answerId;
	}
	public void setAnswerId(String answerId) {
		this.answerId = answerId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
