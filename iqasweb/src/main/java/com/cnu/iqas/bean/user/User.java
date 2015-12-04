package com.cnu.iqas.bean.user;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

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
@GenericGenerator(name="uuidGenderator",strategy="uuid")
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
	private String grade;
	//班级
	private int clas;
	//出生年份
	private Date birthYear;
	//学校
	private String school;
	//身份
	private int role;
	//测试一下
	@Id @GeneratedValue(generator="uuidGenderator")
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Column(length=15,nullable=false,unique=true)
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
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public int getClas() {
		return clas;
	}
	public void setClas(int clas) {
		this.clas = clas;
	}
	public Date getBirthYear() {
		return birthYear;
	}
	public void setBirthYear(Date birthYear) {
		this.birthYear = birthYear;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	
	
}
