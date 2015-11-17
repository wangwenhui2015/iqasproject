package com.cnu.iqas.bean.ontology;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
* @author 周亮 
* @version 创建时间：2015年11月9日 下午9:45:48
* 类说明:单词类。
*/
@Entity
@Table(name="t_iword")
public class Iword {
	/**
	 * 单词id
	 */
	private String id;
	//单词内容
	private String content;
	/*单词所拥有资源 1对多
	private Set<Resource> resources = new HashSet<Resource>();
	//单词属性所拥有的资源 1对多
	private Set<AttributeResource> attributeresources = new HashSet<AttributeResource>();*/
	//中文意思
	private String propertyChinese;
	//功能
	private String propertyFunction;
	//反义词
	private String propertyAntonym;
	//同义词
	private String propertySynonyms;
	
	
	public Iword() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Iword(String propertyChinese, String propertyFunction, String propertyAntonym, String propertySynonyms) {
		super();
		this.propertyChinese = propertyChinese;
		this.propertyFunction = propertyFunction;
		this.propertyAntonym = propertyAntonym;
		this.propertySynonyms = propertySynonyms;
	}
	
	@Id @Column(nullable=false)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	/*@OneToMany(cascade={CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.MERGE},mappedBy="iword")//有Resources一方来维护关系，
	@JoinColumn(name="iwordid")
	public Set<Resource> getResources() {
		return resources;
	}
	public void setResources(Set<Resource> resources) {
		this.resources = resources;
	}
	@OneToMany(cascade={CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.MERGE},mappedBy="iword")//有Resources一方来维护关系，
	@JoinColumn(name="iwordid")
	public Set<AttributeResource> getAttributeresources() {
		return attributeresources;
	}
	public void setAttributeresources(Set<AttributeResource> attributeresources) {
		this.attributeresources = attributeresources;
	}*/
	public String getPropertyChinese() {
		return propertyChinese;
	}
	public void setPropertyChinese(String propertyChinese) {
		this.propertyChinese = propertyChinese;
	}
	public String getPropertyFunction() {
		return propertyFunction;
	}
	public void setPropertyFunction(String propertyFunction) {
		this.propertyFunction = propertyFunction;
	}
	public String getPropertyAntonym() {
		return propertyAntonym;
	}
	public void setPropertyAntonym(String propertyAntonym) {
		this.propertyAntonym = propertyAntonym;
	}
	public String getPropertySynonyms() {
		return propertySynonyms;
	}
	public void setPropertySynonyms(String propertySynonyms) {
		this.propertySynonyms = propertySynonyms;
	}
	
	
	
}
