package com.cnu.iqas.bean.iword;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	//主题名称，唯一不可空
	private String name;
	/**
	 * 是否有效,默认值为true：有效
	 */
	private boolean visible=true;
	//创建时间
	private Date createTime=new Date();
	
	public WordTheme() {
		super();
		// TODO Auto-generated constructor stub
	}
	public WordTheme( String name) {
		super();
		this.name = name;
	}
	@Id @GeneratedValue(generator="uuidGenderator")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(nullable=false,length=20,unique=true)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
}
