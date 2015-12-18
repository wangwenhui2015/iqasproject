package com.cnu.iqas.formbean.iword;

import com.cnu.iqas.formbean.BaseForm;

/**
* @author 周亮 
* @version 创建时间：2015年12月8日 上午11:11:36
* 类说明 单词主题表单类，负责接受页面数据
*/
public class WordThemeForm extends BaseForm {
	//单词uuid
	private String uuid;
	//主题id
	private String id;
	//主题名称
	private String name;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	/**
	 * 校验添加的主题名称是否符合规范，不为空长度在1-20
	 * @return 正确返回true,错误返回false并在errors集合中存放了key值为name的错误原因。
	 */
	public boolean validateThemeName(){
		String nameReg = "\\S{1,20}";
		if( name!=null && name.matches(nameReg)){
			return true;
		}
		getErrors().put("name", "主题长度为1-20位");
		return false;
	}
	
	/**
	 * 主题的id和名称基本格式是否符合要求，符合返回true,否则返回false;
	 * @return
	 */
	public boolean validateIdAndName(){
		if( BaseForm.validate(id) &&validateThemeName())
			return true;
		return false;
	}
}
