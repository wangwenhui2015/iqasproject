package com.cnu.iqas.bean.iword;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
* @author 周亮 
* @version 创建时间：2015年11月9日 下午9:45:48
* 类说明:单词类。
*/
@Entity
@Table(name="t_iword")
@GenericGenerator(name="uuidGenderator",strategy="uuid")
public class Iword {
	//单词存储uuid
	private String uuid;
	/**
	 * 单词标识，由版本+册数+单元+序号 组成：如2/3/6/1 即北京版第3册第6单元的第一个单词
	 */
	private String id;
	//单词内容
	private String content;
	/**
	 * 版本
	 * 1：北师大版
	 * 2：北京版
	 * 3：外研社新标准
	 * 4：外研社一年级起
	 * 5：人教版
	 * 6：朗文版
	 */
	private int version;
	//册数
	private int book;
	//单元
	private int unit;
	//序号,单词在本单元的序号
	private int rank;
	
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

	//创建时间
	private Date createtime=new Date();
	
	
	public Iword(String id, String content) {
		super();
		this.id = id;
		this.content = content;
		String[] cols= this.id.split("/");
		this.version=Integer.parseInt(cols[0]);
		this.book=Integer.parseInt(cols[1]);
		this.unit=Integer.parseInt(cols[2]);
		this.rank=Integer.parseInt(cols[3]);
		
	}
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
	@Id @Column(nullable=false) @GeneratedValue(generator="uuidGenderator")
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	@Column(nullable=false)
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
	
	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	/**
	 * 版本
	 * 1：北师大版
	 * 2：北京版
	 * 3：外研社新标准
	 * 4：外研社一年级起
	 * 5：人教版
	 * 6：朗文版
	 */
	@Column(nullable=false)
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	@Column(nullable=false)
	public int getBook() {
		return book;
	}
	public void setBook(int book) {
		this.book = book;
	}
	@Column(nullable=false)
	public int getUnit() {
		return unit;
	}
	public void setUnit(int unit) {
		this.unit = unit;
	}
	
	@Column(nullable=false)
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public String toString() {
		return "Iword [id=" + id + ", content=" + content + ", version=" + version + ", book=" + book + ", unit=" + unit
				+ ", rank=" + rank + ", createtime=" + createtime + "]";
	}

	
}
