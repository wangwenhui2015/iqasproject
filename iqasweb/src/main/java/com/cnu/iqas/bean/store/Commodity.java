package com.cnu.iqas.bean.store;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
* @author 周亮 
* @version 创建时间：2015年12月18日 下午1:58:49
* 类说明  商品
*/
@Entity
@Table(name="t_commodity")
@GenericGenerator(strategy="uuid",name="uuidGenerator")
public class Commodity {
	/**
	 * id，标识
	 */
	private String id;
	/**
	 * 所属商品类型id
	 */
	private String typeid;
	/**
	 * 商品类型名称
	 */
	private String name;
	/**
	 * 商品需要金币数
	 */
	private Integer coinCount;
	/**
	 * 是否可见，默认可见值为true
	 */
	private boolean visible=true;
	/**
	 * 创建日期,默认当前日期
	 */
	private Date createTime=new Date();
	@Id @GeneratedValue(generator="uuidGenerator")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(nullable=false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(nullable=false)
	public String getTypeid() {
		return typeid;
	}
	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}

	@Column(nullable=false)
	public Integer getCoinCount() {
		return coinCount;
	}
	public void setCoinCount(Integer coinCount) {
		this.coinCount = coinCount;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
