package com.cnu.iqas.bean.user;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
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
	/**
	 * 用户在自适应学习系统中可查看的商品类型等级，默认值0
	 */
	private Integer storeGrade=0;
	
	/**
	 * 用户在自适应学习系统中的获得的金币总数，初始值为0
	 */
	private Integer allCoins=0;
	
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
	public Integer getStoreGrade() {
		return storeGrade;
	}
	public void setStoreGrade(Integer storeGrade) {
		this.storeGrade = storeGrade;
	}
	public Integer getAllCoins() {
		return allCoins;
	}
	public void setAllCoins(Integer allCoins) {
		this.allCoins = allCoins;
	}
	
	
	
}
