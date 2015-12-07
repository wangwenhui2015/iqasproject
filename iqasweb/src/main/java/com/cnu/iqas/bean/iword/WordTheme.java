package com.cnu.iqas.bean.iword;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
/**
* @author 周亮 
* @version 创建时间：2015年12月7日 上午9:00:07
* 类说明
*/
@Entity
@Table(name="t_wordtheme")
@GenericGenerator(name="uuidGenderator",strategy="uuid")
public class WordTheme {
	//id
	private String id;
	//类型
	private Integer type;
	
	public WordTheme() {
		super();
		// TODO Auto-generated constructor stub
	}
	public WordTheme( int type) {
		super();
		this.type = type;
	}
	@Id @GeneratedValue(generator="uuidGenderator")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(nullable=false)
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
}
