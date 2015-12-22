package com.cnu.iqas.bean.store;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
* @author 周亮 
* @version 创建时间：2015年12月22日 下午1:05:09
* 类说明 用户商品关系表
*/
public class UserCommodityRel {
	
	/**
	 * 关系id
	 */
	private String id;
	/**
	 * 商品id
	 */
	private String coId;
	/**
	 * 用户id
	 */
	private String userId;
	/**
	 * 用户拥有该商品的数量
	 */
	private Integer count;
	/**
	 * 商品类型id
	 */
	private String typeId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCoId() {
		return coId;
	}
	public void setCoId(String coId) {
		this.coId = coId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	
	
	
	
}
