package com.cnu.iqas.formbean.user;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.cnu.iqas.formbean.BaseForm;

//关于用户的表单类
public class UserForm extends BaseForm{
	
	private String userName;
	private String password;
	private int sex;
	private String grade;
	
	@NotEmpty(message="用户名不能为空")
	//@Pattern(regexp="\\w{3,15}",message="用户名长度为3~15")//通过正则表达式进行校验，匹配4~15个数字和字母以及下划线的字符
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	//@Pattern(regexp="S{4,10}")//通过正则表达式进行校验，匹配4~10个非空白的字符
	
	//@NotEmpty(message="不能为null")
	//@NotNull(message="null")
	//@Size(min=5,max=15,message="用户名长度范围在5~15")
	@NotEmpty(message="密码不能为空")
	@Pattern(regexp="\\S{3,15}",message="密码长度为3~15")//通过正则表达式进行校验，匹配4~15个非空白的字符
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	@NotEmpty(message="班级不能为空")
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public boolean validateNameAndPass(){
		String passre = "\\S{3,15}";
		String namere ="\\w{3,15}";
		if(BaseForm.validate(userName)&&BaseForm.validate(password)){
			if(userName.matches(namere)&&passre.matches(passre))
			{
				return true;
			}
		}
		return false;
	}
	
	
	
}
