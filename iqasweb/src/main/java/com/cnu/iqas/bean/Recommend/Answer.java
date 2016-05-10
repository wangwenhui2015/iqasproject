package com.cnu.iqas.bean.Recommend;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author 王文辉
 * @version 创建时间：2016年4月20日  答案表
 */
@Entity
@Table(name = "t_answer")
@GenericGenerator(strategy = "uuid", name = "uuidGenerator")
public class Answer {
	/**
	 * answerId，答案标识
	 */
	 private String answerId;
	 /**
		 * 内容
		 */
	private String content;
	 /**
	 * 属性
	 */
	private String attributes;
	/**
	 * 媒体类型1.文本 2、视频 3、MP3 4、绘本
	 */
	private String mediaType;
	/**
	 * 难度值       1.高 2.中 3.低
	 */
	private String difficulty;
	/**
	 * 是否通过检查
	 */
	private boolean checked;
	/**
	 * 添加类型  1.自动      2.人工
	 */
	private String addType;
	/**
	 * 创建日期
	 */
	private Date createDate= new Date();
	@Id
	@GeneratedValue(generator = "uuidGenerator")
	public String getAnswerId() {
		return answerId;
	}
	public void setAnswerId(String answerId) {
		this.answerId = answerId;
	}
	public String getAttributes() {
		return attributes;
	}
	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getMediaType() {
		return mediaType;
	}
	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}
	public String getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}
	public String getAddType() {
		return addType;
	}
	public void setAddType(String addType) {
		this.addType = addType;
	}
	
	
}
