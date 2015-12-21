package com.cnu.iqas.formbean.store;

import com.cnu.iqas.formbean.BaseForm;

/**
* @author 周亮 
* @version 创建时间：2015年12月21日 下午8:13:03
* 类说明
*/
public class StoreForm extends BaseForm {
	/**
	 * 商品类型id或者商品id
	 */
	private String id;
	/**
	 * 商品类型名
	 */
	private String name;
	/**
	 * 商品类型等级
	 */
	private Integer grade;
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
	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	
}
