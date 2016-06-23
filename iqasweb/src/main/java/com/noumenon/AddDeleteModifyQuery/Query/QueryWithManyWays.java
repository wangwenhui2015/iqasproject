package com.noumenon.AddDeleteModifyQuery.Query;

import com.hp.hpl.jena.query.ResultSet;

public interface QueryWithManyWays {

	/**
	 * ����Id���ҵ��ʼ������ԣ�����ResultSet----------------------------------
	 * 
	 * @param yourId
	 *            ������ID
	 * @return
	 */
	public ResultSet checkPropertyDependOnId(String yourId);

	/**
	 * ����Id���Ҿ��Ӽ������ԣ�����ֵResultSet---------------------------------
	 * 
	 * @param yourId
	 *            ������ID
	 * @return
	 */
	public ResultSet checkSentencePropertyDependOnId(String yourId);

	/**
	 * ����һ�����µ�����ʵ����ǩLabel������ֵResultSet--------------------------
	 * 
	 * @param yourClass
	 *            �����ʵ���
	 * @return
	 */
	public ResultSet checkInstance(String yourClass);
	
	/**
	 * ���Ҹø����µ�����ID������ֵResultSet-----------------------------------
	 * 
	 * @param yourClass
	 * @return
	 */
	public ResultSet checkIDDependOnClass(String yourClass);

	/**
	 * ���Ҹ�ʵ��������ʵ���������ԣ�����ֵResultSet-------------------------------
	 * 
	 * @param yourWord
	 *            ������
	 * @return
	 */
	public ResultSet checkProperty(String yourWord);
	
	/**
	 * ��ѯһ�����ʶ�Ӧ������ID
	 * 
	 * @param yourWord
	 *            ������
	 * @return
	 */
	public ResultSet checkAllIdOfAnWord(String yourWord);
	
	/**
	 * ��ѯһ�����Ӷ�Ӧ������ID
	 * 
	 * @param yourSentence
	 *                ������
	 * @return
	 */
	public ResultSet checkAllIdOfAnSentence(String yourSentence);

	/**
	 * ����ʵ�����Ʋ��Ҿ�����������ֵ������ֵResultSet-----------------------------
	 * 
	 * @param yourSentence
	 * @return
	 */
	public ResultSet checkSentenceProperty(String yourSentence);

	/**
	 * �ж����ݿ����Ƿ���ڴ�ʵ��������ֵboolean--------------------------------
	 * 
	 * @param Instance
	 * @return
	 */
	public boolean checkIfInDB(String Instance);

	/**
	 * ����������Ԫ�飺����ֵResultSet--------------------------------------
	 * 
	 * @return
	 */
	public ResultSet checkAllTriple();
	
	/**
	 * ���ҵȼ�sameAs��ϵ
	 * 
	 * @param yourInstance
	 * @return
	 */
	public ResultSet checkPropertySameAs(String yourInstance);
	
	/**
	 * ֻ��ʵ����Label
	 * 
	 * @param yourInstance
	 * @return
	 */
	public ResultSet checkOnlyInstanceURI(String yourInstance);
	
	/**
	 * ֻ����������
	 * 
	 * @param yourPropertyURI
	 * @return
	 */
	public ResultSet checkOnlyPropertyLabel(String yourPropertyURI);
	
	/**
	 * ����ID�����丸��+��ǩLabel+ID����Ԫ��(Ϊ�޸�������Ƶ�)
	 * 
	 * @param yourID
	 * @return
	 */
	public ResultSet checkClass_Label(String yourID);
	
	/**
	 * ���ݵ��ʲ���������������ֵ
	 * @param yourWord
	 * @return
	 */
	public ResultSet checkTopicValue(String yourWord);
	
	/**
	 * �����������Ա�ǣ��ҳ����а����ñ�ǵ�����ֵ��������ֵ�а�������ID
	 * @param yourThemeValueFlag1
	 * @param yourThemeValueFlag2
	 * @return
	 */
	public ResultSet checkBrotherID(String yourThemeValueFlag1, String yourThemeValueFlag2);
	
	/**
	 * �����������Ա�ǣ��ҳ����а����ñ�ǵ�����ֵ��������ֵ�а�������ID(����Ϊ�Լ������)
	 * @param yourTheme
	 * @return
	 */
	public ResultSet checkBrotherID2(String yourTheme);
	
	/**
	 * �����꼶����ҳ�5�����ʺ�2������
	 * @param yourGrade
	 * @return
	 */
	public ResultSet checkAllWordsOfThisGrade(String yourGrade);
	
	/**
	 * ��ѯ���еĵ��ʵ�ID
	 * @return
	 */
	public ResultSet checkIdOfAllWords();
	
	/**
	 * ��ѯ���еľ��ӵ�ID
	 * @return
	 */
	public ResultSet checkIdOfAllSentences();
	
	/**
	 * ��ѯĳ�꼶���е��ʵ�ID
	 * @return
	 */
	public ResultSet checkAllIdOfThisGrade(String yourGrade);
	
	/**
	 * ��ѯĳ����-������������е��ʵ�ID
	 * @param yourPropertyFunction
	 * @return
	 */
	public ResultSet checkAllIdOfThisPropertyFunction(String yourPropertyFunction);
	
	/**
	 * ���ݰ汾���Ҹð汾���е��ʣ�����ֵResultSet-------------------------------
	 * @param yourVersion
	 * @return
	 */
	public ResultSet checkAllWordsOfAVersion(String yourVersion);
	
	
	// -----------------------------------------------------------------------------------------
	/**
	 * ����ʵ��+ĳ����+����ֵ�����Ҹ���Ԫ�飺����ֵResultSet------------------------
	 * 
	 * @param yourInstance
	 *            ��ʵ����ǩ
	 * @param propertyLabel
	 *            ��������
	 * @param flag
	 *            ����־������Ϊ��31���� ����Ϊ��85����
	 * @return
	 */
	public ResultSet checkThisTriple(String yourInstance, String propertyLabel,
			String flag);

	/**
	 * ����ʵ��Label��������ID������ֵResultSet-------------------------------
	 * 
	 * @param yourInstance
	 *            ��ʵ����ǩ
	 * @param flag
	 * @return����־������Ϊ��31���� ����Ϊ��85����
	 */
	public ResultSet checkAllID(String yourInstance, String flag);
	

}
