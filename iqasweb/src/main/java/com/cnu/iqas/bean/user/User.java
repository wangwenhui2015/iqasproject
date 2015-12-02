package com.cnu.iqas.bean.user;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * userId	int			主键
loginName	varchar	登录名	学生：学号
password	varchar	密码	默认：000000	
realName	varchar	姓名		
sex	int	性别	1：男
2：女	
grade	int	年级		
class	int	班级		
birthyear	int	出生年份		
golden	int	金币数	默认都置0	
rank	int	排行	默认都置0	
medal	int	勋章	默认都置0	
loginTimes	int	登录次数	默认0	
wordcount	int	已学习单词个数	默认0	
workcount	int	作品个数	默认0	
school	int	学校		
role	int	身份		

 * @author dell
 *
 */
@Entity
@Table(name="t_user")
public class User {
	//主键
	private String userId;
	//用户名
	private String userName;
	//密码
	private String password;
	//姓名
	private String realName;
	//性别
	private int sex;
	//年级
	private int grade;
	//班级
	private int clas;
	//出生年份
	private Date birthYear;
	//学校
	private String school;
	//身份
	private int role;
	
	@Id  @Column(length=15,nullable=false)
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Column(length=32,nullable=false)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
