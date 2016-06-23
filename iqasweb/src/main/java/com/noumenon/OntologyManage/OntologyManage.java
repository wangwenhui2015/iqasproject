package com.noumenon.OntologyManage; 
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.hp.hpl.jena.query.ResultSet;

import jxl.read.biff.BiffException;


public interface OntologyManage {
	/**���һ�����ʼ������ԣ��޷���ֵ----------------------------------------------------------
	 * 
	 * @param parameter
	 */
	@Deprecated
	public void Add(String[] parameter);

	/**���һ�����Ӽ������ԣ��޷���ֵ----------------------------------------------------------
	 * 
	 * @param parameter
	 */
	public void AddSentence(String[] parameter);
	
	/**��Excel��������ӵ��ʣ��޷���ֵ--------------------------------------------------------
	 * 
	 * @param yourPath
	 * @throws BiffException
	 * @throws IOException
	 */
	@Deprecated
	public void AddBatch(InputStream yourPath) throws BiffException, IOException;
	
	/**��Excel��������ӵ��ʣ��޷���ֵ--------------------------------------------------------
	 * 
	 * @param yourPath
	 * @throws BiffException
	 * @throws IOException
	 */
	public void AddWordBatch(InputStream yourPath) throws BiffException, IOException;

	/**��Excel��������Ӿ��ӣ��޷���ֵ--------------------------------------------------------
	 * 
	 * @param yourPath
	 * @throws BiffException
	 * @throws IOException
	 */
	public void AddSentenceBatch(InputStream yourPath) throws BiffException, IOException;

	/**ɾ��һ�����ʼ������ԣ��޷���ֵ----------------------------------------------------------
	 * 
	 * @param yourInstanceID
	 */
	public void Delete(String yourInstanceID);
	
	/**ɾ��һ�����Ӽ������ԣ��޷���ֵ----------------------------------------------------------
	 * 
	 * @param yourInstanceID
	 */
	public void DeleteSentence(String yourInstanceID);

	/**�޸�һ�����ʵ�ĳ�����ԣ��޷���ֵ----------------------------------------------------------
	 * 
	 * @param yourProperty
	 * @param yourPropertyLabel
	 * @param yourSPARQLProperty
	 * @param yourRelationProperty
	 */
	public void Modify(String yourProperty, String yourPropertyLabel, String yourSPARQLProperty, String yourRelationProperty);
	
	/**�޸�һ�����Ӽ������ԣ��޷���ֵ----------------------------------------------------------
	 * 
	 * @param yourProperty
	 * @param yourPropertyLabel
	 * @param yourSPARQLProperty
	 * @param yourRelationProperty
	 */
	public void ModifySentence(String yourProperty, String yourPropertyLabel, String yourSPARQLProperty, String yourRelationProperty);
	
	/**������������µ����е���Label�����ؽ����ResultSet-----------------------------------------
	 * 
	 * @param yourClass
	 * @return
	 */
	public ResultSet QueryWord(String yourClass);
	
	/**����HowNet����鿴���е��ʼ������ԣ����ؽ����ResultSet-----------------------------------------
	 * 
	 * @param yourClass
	 * @return
	 */
	public List<ResultSet> QueryWordAndPropertiesDependOnClass(String yourClass);

	/**����ID���Ҹõ��ʼ����������ԣ����ؽ����ResultSet-------------------------------------------
	 * 
	 * @param yourID
	 * @return
	 */
	public ResultSet QueryIndividualDependOnId(String yourID);

	/**��һ�����ʵ��������ԣ����ؽ����ResultSet------------------------------------------------
	 * 
	 * @param yourWord
	 * @return
	 */
	public ResultSet QueryIndividual(String yourWord);
	
	/**��ѯ���ʶ�Ӧ������ID-----------------------------------------------------------------
	 * 
	 * @param yourWord
	 * @return
	 */
	public ResultSet QueryAWordAllId(String yourWord);
	
	/**��ѯ���Ӷ�Ӧ������ID-----------------------------------------------------------------
	 * 
	 * @param yourSentence
	 * @return
	 */
	public ResultSet QueryASentenceAllId(String yourSentence);
		
	/**����ID���Ҹþ��Ӽ����������ԣ����ؽ����ResultSet-------------------------------------------
	 * 
	 * @param yourID
	 * @return
	 */
	public ResultSet QuerySentenceIndividualDependOnId(String yourID);

	/**��һ�����ӵ��������ԣ����ؽ����ResultSet-------------------------------------------------
	 * 
	 * @param yourSentence
	 * @return
	 */
	public ResultSet QuerySentenceIndividual(String yourSentence);
	
	/**���ݵ��ʲ鿴����ͬ�����ʼ������ԣ����ؽ����ResultSet-----------------------------------------
	 * 
	 * @param yourTheme
	 * @return
	 */
	public List<ResultSet> QueryBrotherIndividual(String yourTheme);
	
	/**���ݰ汾�͵�Ԫ��ѯ���е��ʼ�������
	 * 
	 * @param yourBook
	 * @param yourGrade
	 * @param yourUnit
	 * @return
	 */
	public List<ResultSet> QueryAllWordsOfAUnit(String yourBook, String yourGrade, String yourUnit);
	

	// ��������---------------------------------------------------------------------------------
	/**�����꼶����ҳ�5������----------------------------------------------------------------
	 * 
	 * @param yourGrade
	 * @return
	 */
	public List<ResultSet> QueryFiveWordsOfThisGrade(String yourGrade);
	
	/**�����꼶����ҳ�2������----------------------------------------------------------------
	 * 
	 * @param yourGrade
	 * @return
	 */
	public List<ResultSet> QueryTwoSentencesOfThisGrade(String yourGrade);
	
	/**�����꼶����ҳ�3������----------------------------------------------------------------
	 * 
	 * @param yourGrade
	 * @return
	 */
	public List<ResultSet> QueryThreeSentencesOfThisGrade(String yourGrade);
	
	/**�������ʴ��ҳ������Լ�ID----------------------------------------------------------------
	 * 
	 * @param yourClass
	 * @return
	 */
	public Map<String, String>QuerySentenceAndId(String yourClass);
	
	/**�����꼶��ѯ��õ������ⲻͬ�����2������----------------------------------------------------------
	 * 
	 * @param yourGrade
	 * @param yourWord
	 * @return
	 */
	public List<String> QueryTwoDifferentThemeWordsOfThisGrade(String yourGrade, String yourWord);
	
	/**�����꼶���������ʵ��Ѷ����������������---------------------------------------------------------
	 * 
	 * @param yourGrade
	 * @param yourDifficultyOfWord1
	 * @param yourDifficultyOfWord2
	 * @return
	 */
	public List<String> QueryTwoWordsDependOnDifficulty(String yourGrade, String yourDifficultyOfWord1, String yourDifficultyOfWord2);
	
	/**�����꼶���������������--------------------------------------------------------------------
	 * 
	 * @param yourGrade
	 * @return
	 */
	public List<ResultSet> TwoRandomWordsOfThisGrade(String yourGrade);
	
	/**�ж�ĳ�����Ƿ�����ڱ������
	 * 
	 * @return
	 */
	public Boolean IfExistInFuseki(String yourWord);
	
	/**��ѯ��õ��ʾ���һ���꼶�������������������
	 * 
	 * @param yourWord
	 * @return
	 */
	public List<String> QueryTheTextOFThisWord(String yourWord, String yourGrade, String themeOfThisWord);
	
	
	//-----------------------------------------------------------------------------------------
	/**��Fuseki��д��OWL�ļ��У��ҵ��ʺ;��ӷֿ����޷���ֵ-------------------------------------------
	 * 
	 * @throws IOException
	 */
	@Deprecated
	public void WriteBackToOwl() throws IOException;
	
	/**��Fuseki��д��OWL�ļ��У��ҵ��ʺ;��ӷֿ����޷���ֵ-------------------------------------------
	 * 
	 * @throws IOException
	 */
	public void WriteBackToRespectiveOwl() throws IOException;

	/**��Excel����ӵȼ۹�ϵSameAs���޷���ֵ---------------------------------------------------
	 * 
	 * @param yourPath
	 * @throws BiffException
	 * @throws IOException
	 */
	public void InsertRelationSameAs(InputStream yourPath) throws BiffException, IOException;
	
	/**
	 * ����ȼ۹�ϵ
	 * 
	 * @param yourWord
	 * @return
	 */
	public ArrayList<String> ReasonSameAs(String yourSentence);

}
