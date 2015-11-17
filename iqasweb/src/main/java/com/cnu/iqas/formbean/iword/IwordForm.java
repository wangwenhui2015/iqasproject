package com.cnu.iqas.formbean.iword;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import com.cnu.iqas.formbean.BaseForm;

/**
* @author 周亮 
* @version 创建时间：2015年11月17日 下午3:23:19
* 类说明
*/
public class IwordForm extends BaseForm {
	/**
	 * 单词id
	 */
	private String id;
	//单词内容
	private String content;
	
	@NotEmpty(message="单词id不能为空")
	@Pattern(regexp="\\S{1,15}",message="单词id长度为1~15")//通过正则表达式进行校验，匹配4~15个非空白的字符
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@NotEmpty(message="单词不能为空")
	@Pattern(regexp="\\S{1,15}",message="单词长度为1~15")//通过正则表达式进行校验，匹配4~15个非空白的字符
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
