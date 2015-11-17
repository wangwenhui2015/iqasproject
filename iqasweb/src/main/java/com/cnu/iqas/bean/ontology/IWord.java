package com.cnu.iqas.bean.ontology;

public class IWord {

	private String propertyChinese;
	private String propertyFunction;
	private String propertyAntonym;
	private String propertySynonyms;
	
	public IWord() {
		super();
		// TODO Auto-generated constructor stub
	}
	public IWord(String propertyChinese, String propertyFunction, String propertyAntonym, String propertySynonyms) {
		super();
		this.propertyChinese = propertyChinese;
		this.propertyFunction = propertyFunction;
		this.propertyAntonym = propertyAntonym;
		this.propertySynonyms = propertySynonyms;
	}
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
