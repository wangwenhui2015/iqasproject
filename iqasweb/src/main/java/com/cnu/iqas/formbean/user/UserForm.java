package com.cnu.iqas.formbean.user;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.cnu.iqas.formbean.BaseForm;

//关于用户的表单类
public class UserForm extends BaseForm{
	
	private String username;
	private String password;
	
	@NotEmpty(message="用户名不能为空")
	@Pattern(regexp="\\w{3,15}",message="用户名长度为3~15")//通过正则表达式进行校验，匹配4~15个数字和字母以及下划线的字符
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	
	
	public boolean validateNameAndPass(){
		String passre = "\\S{3,15}";
		String namere ="\\w{3,15}";
		if(BaseForm.validate(username)&&BaseForm.validate(password)){
			if(username.matches(namere)&&passre.matches(passre))
			{
				return true;
			}
		}
		return false;
	}
	
	
	
}
