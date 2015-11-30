package com.cnu.iqas.bean.iword;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.GenericGenerator;

/**
* @author 周亮 
* @version 创建时间：2015年11月12日 下午9:45:48
* 类说明:单词的资源。
*/
@Entity
@Table(name="t_resource")
@GenericGenerator(name="uuidGenderator",strategy="uuid")
public class WordResource {
	/**
	 * 资源id
	 */
	private String id;
	/**
	 * 资源名称
	 */
	private String name;
	/**
	 * 所属单词，单向多对一的关系
	 */
	private Iword iword;
	/**
	 * 资源类型,有1图片、2视频、3声音、4绘本
	 */
	private int type;
	/**
	 * 资源保存路径集合，多条路径以英文分号隔开
	 */
	private String savepath;
	/**
	 * 资源是否被屏蔽,默认可见
	 */
	private boolean visible=true;
	
	
	
	@Id @Column(nullable=false)@GeneratedValue(generator="uuidGenderator")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(nullable=false,length=32)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 单向N-1,而且只级联刷新操作，即当资源插入时，该资源所属单词必须存在，不然抛异常
	 * @return
	 */
	@ManyToOne(cascade={CascadeType.REFRESH},optional=false) 
	@JoinColumn(name="iworduuid")
	@ForeignKey(name="FK_IWord_WordResource")
	public Iword getIword() {
		return iword;
	}
	public void setIword(Iword iword) {
		this.iword = iword;
	}
	@Column(nullable=false)
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	@Column(nullable=false,length=255)
	public String getSavepath() {
		return savepath;
	}
	public void setSavepath(String savepath) {
		this.savepath = savepath;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	@Override
	public String toString() {
		return "WordResource [id=" + id + ", name=" + name + ", iword=" + iword + ", type=" + type + ", savepath="
				+ savepath + ", visible=" + visible + "]";
	}
	
}
