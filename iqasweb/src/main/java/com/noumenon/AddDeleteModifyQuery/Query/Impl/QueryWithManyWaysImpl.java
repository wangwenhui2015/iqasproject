package com.noumenon.AddDeleteModifyQuery.Query.Impl;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.vocabulary.OWL;
import com.noumenon.AddDeleteModifyQuery.Query.QueryWithManyWays;

public class QueryWithManyWaysImpl implements QueryWithManyWays {

	// ����fuseki���ݿ����ӵ�ַ/EnglishLearningDataset query
	// private static String SERVER =
	// "http://localhost:3030/EnglishLearningDataset/query";
	private static String SERVER = "http://localhost:3030/EnglishLearningDataset_FourthGrade/query";

	// �������������ʵ��Label
	@Override
	public ResultSet checkInstance(String yourClass) {
		String string1 = "SELECT ?instanceLabel WHERE{?class <http://www.w3.org/2000/01/rdf-schema#label> \"";
		String string2 = "\"@zh."
				+ "?instance <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> ?class."
				+ "?instance <http://www.w3.org/2000/01/rdf-schema#label> ?instanceLabel}";
		String sparqlInstance = string1 + yourClass + string2;
		// System.out.println(sparqlInstance);

		ResultSet resultsInstance = Result(sparqlInstance);
		return resultsInstance;
	}

	// ���Ҹø����µ�����ID
	public ResultSet checkIDDependOnClass(String yourClass) {
		String string1 = "SELECT ?propertyID WHERE{?class <http://www.w3.org/2000/01/rdf-schema#label> \"";
		String string2 = "\"@zh."
				+ "?instance <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> ?class."
				+ "?relationID <http://www.w3.org/2000/01/rdf-schema#label> \"����ID\"@zh." 
				+ "?instance ?relationID ?propertyID.}";
		String sparqlIDDependOnClass = string1 + yourClass + string2;
		// System.out.println(sparqlInstance);

		ResultSet resultsIDDependOnClass = Result(sparqlIDDependOnClass);
		return resultsIDDependOnClass;
	}

	// ����ʵ�����Ʋ��ҵ�����������ֵ
	@Override
	public ResultSet checkProperty(String yourWord) {
		if (yourWord.contains("@")) {
			yourWord = yourWord.substring(0, yourWord.indexOf("@"));
		}

		// ������ʵ��
		String string1 = "SELECT ?instanceLabel ?propertyClass ?propertyID ?propertyChinese ?propertyFunction ?propertyTopic ?propertyBook ?propertyAntonym ?propertySynonyms ?propertyCommonUse ?propertyExtend ?propertyScene ?propertyExpand ?propertyVersion ?propertyUse ?propertyNcyclopedia ?propertyAssociate ?propertyPartsOfSpeech ?propertyWordProperty ?propertyText ?propertyDifficulty "
				+ "WHERE{?instance <http://www.w3.org/2000/01/rdf-schema#label> \"";// ע�ⲻҪ���˿ո�
		String string2 = "\"@zh."
				+ "?instance <http://www.w3.org/2000/01/rdf-schema#label> ?instanceLabel."

				+ "?instance <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> ?class."
				+ "?class <http://www.w3.org/2000/01/rdf-schema#label> ?propertyClass."

				+ "?relationID <http://www.w3.org/2000/01/rdf-schema#label> \"����ID\"@zh."
				+ "?instance ?relationID ?propertyID."

				+ "?relationChinese <http://www.w3.org/2000/01/rdf-schema#label> \"���ĺ���\"@zh."
				+ "?instance ?relationChinese ?propertyChinese."

				+ "?relationFunction <http://www.w3.org/2000/01/rdf-schema#label> \"����-��������\"@zh."
				+ "?instance ?relationFunction ?propertyFunction."

				+ "?relationTopic <http://www.w3.org/2000/01/rdf-schema#label> \"����-����\"@zh."
				+ "?instance ?relationTopic ?propertyTopic."

				+ "?relationBook <http://www.w3.org/2000/01/rdf-schema#label> \"���ʲ���\"@zh."
				+ "?instance ?relationBook ?propertyBook."

				+ "?relationAntonym <http://www.w3.org/2000/01/rdf-schema#label> \"�����\"@zh."
				+ "?instance ?relationAntonym ?propertyAntonym."

				+ "?relationSynonyms <http://www.w3.org/2000/01/rdf-schema#label> \"ͬ���\"@zh."
				+ "?instance ?relationSynonyms ?propertySynonyms."

				+ "?relationCommonUse <http://www.w3.org/2000/01/rdf-schema#label> \"����\"@zh."
				+ "?instance ?relationCommonUse ?propertyCommonUse."

				+ "?relationExtend <http://www.w3.org/2000/01/rdf-schema#label> \"��������\"@zh."
				+ "?instance ?relationExtend ?propertyExtend."

				+ "?relationScene <http://www.w3.org/2000/01/rdf-schema#label> \"�龳����\"@zh."
				+ "?instance ?relationScene ?propertyScene."

				+ "?relationExpand <http://www.w3.org/2000/01/rdf-schema#label> \"��չ\"@zh."
				+ "?instance ?relationExpand ?propertyExpand."

				+ "?relationVersion <http://www.w3.org/2000/01/rdf-schema#label> \"���ʽ̲İ汾\"@zh."
				+ "?instance ?relationVersion ?propertyVersion."

				+ "?relationUse <http://www.w3.org/2000/01/rdf-schema#label> \"�÷�\"@zh."
				+ "?instance ?relationUse ?propertyUse."

				+ "?relationNcyclopedia <http://www.w3.org/2000/01/rdf-schema#label> \"�ٿ�\"@zh."
				+ "?instance ?relationNcyclopedia ?propertyNcyclopedia."

				+ "?relationAssociate <http://www.w3.org/2000/01/rdf-schema#label> \"����\"@zh."
				+ "?instance ?relationAssociate ?propertyAssociate."

				+ "?relationPartsOfSpeech <http://www.w3.org/2000/01/rdf-schema#label> \"����\"@zh."
				+ "?instance ?relationPartsOfSpeech ?propertyPartsOfSpeech."

				+ "?relationWordProperty <http://www.w3.org/2000/01/rdf-schema#label> \"��������\"@zh."
				+ "?instance ?relationWordProperty ?propertyWordProperty."

				+ "?relationText <http://www.w3.org/2000/01/rdf-schema#label> \"����ԭ��\"@zh."
				+ "?instance ?relationText ?propertyText."

				+ "?relationDifficulty <http://www.w3.org/2000/01/rdf-schema#label> \"�Ѷ�\"@zh."
				+ "?instance ?relationDifficulty ?propertyDifficulty." + "}";
		String sparqlInstance = string1 + yourWord + string2;
		// System.out.println(sparqlInstance);

		// Results from a query in a table-like manner for SELECT queries.
		// ��SELECT��ѯ��������еĲ�ѯ���
		// Each row corresponds to a set of bindings which fulfil the conditions
		// of the query.
		// ÿһ�ж�Ӧһ���󶨼�������ִ�в�ѯ����
		// Access to the results is by variable name.
		// ͨ�����������ʽ��
		ResultSet resultsInstance = Result(sparqlInstance);

		return resultsInstance;
	}

	// ��ѯһ�����ʶ�Ӧ������ID
	@Override
	public ResultSet checkAllIdOfAnWord(String yourWord) {
		String string1 = "SELECT ?propertyID WHERE{?instance <http://www.w3.org/2000/01/rdf-schema#label> \"";
		String string2 = "\"@zh."
				+ "?relationID <http://www.w3.org/2000/01/rdf-schema#label> \"����ID\"@zh."
				+ "?instance ?relationID ?propertyID.}";
		String sparqlInstance = string1 + yourWord + string2;
		System.out.println(sparqlInstance);

		ResultSet resultsAllIdOfAInstance = Result(sparqlInstance);
		return resultsAllIdOfAInstance;
	}

	// ��ѯһ�����ʶ�Ӧ������ID
	@Override
	public ResultSet checkAllIdOfAnSentence(String yourSentence) {
		String string1 = "SELECT ?propertyID WHERE{?instance <http://www.w3.org/2000/01/rdf-schema#label> \"";
		String string2 = "\"@zh."
				+ "?relationID <http://www.w3.org/2000/01/rdf-schema#label> \"����ID\"@zh."
				+ "?instance ?relationID ?propertyID.}";
		String sparqlInstance = string1 + yourSentence + string2;
		// System.out.println(sparqlInstance);

		ResultSet resultsAllIdOfAnSentence = Result(sparqlInstance);
		return resultsAllIdOfAnSentence;
	}

	// ����ʵ�����Ʋ��Ҿ�����������ֵ
	@Override
	public ResultSet checkSentenceProperty(String yourSentence) {
		if (yourSentence.contains("@")) {
			yourSentence = yourSentence.substring(0, yourSentence.indexOf("@"));
		}

		// ������ʵ������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������
		String string1 = "SELECT ?instance ?instanceLabel ?propertyClass ?propertyAnswer ?propertyID ?propertyVersion ?propertyBook ?propertyScene ?propertySentencePattern ?propertyRelatedWords ?relationAnswer ?relationID ?relationVersion ?relationBook ?relationScene ?relationSentencePattern ?relationRelatedWords WHERE{?instance <http://www.w3.org/2000/01/rdf-schema#label> \"";
		String string2 = "\"@zh."
				+ "?instance <http://www.w3.org/2000/01/rdf-schema#label> ?instanceLabel."

				+ "?instance <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> ?class."
				+ "?class <http://www.w3.org/2000/01/rdf-schema#label> ?propertyClass."

				+ "?relationAnswer <http://www.w3.org/2000/01/rdf-schema#label> \"�ش�\"@zh."
				+ "?instance ?relationAnswer ?propertyAnswer."

				+ "?relationID <http://www.w3.org/2000/01/rdf-schema#label> \"����ID\"@zh."
				+ "?instance ?relationID ?propertyID."

				+ "?relationVersion <http://www.w3.org/2000/01/rdf-schema#label> \"���ӽ̲İ汾\"@zh."
				+ "?instance ?relationVersion ?propertyVersio."

				+ "?relationBook <http://www.w3.org/2000/01/rdf-schema#label> \"���Ӳ���\"@zh."
				+ "?instance ?relationBook ?propertyBook."

				+ "?relationScene <http://www.w3.org/2000/01/rdf-schema#label> \"�龳�Ի�\"@zh."
				+ "?instance ?relationScene ?propertyScene."

				+ "?relationSentencePattern <http://www.w3.org/2000/01/rdf-schema#label> \"��Ҫ����\"@zh."
				+ "?instance ?relationSentencePattern ?propertySentencePattern."

				+ "?relationRelatedWords <http://www.w3.org/2000/01/rdf-schema#label> \"��ص���\"@zh."
				+ "?instance ?relationRelatedWords ?propertyRelatedWords.}";

		String sparqlInstance = string1 + yourSentence + string2;
		// System.out.println(sparqlInstance);

		// Results from a query in a table-like manner for SELECT queries.
		// ��SELECT��ѯ��������еĲ�ѯ���
		// Each row corresponds to a set of bindings which fulfil the conditions
		// of the query.
		// ÿһ�ж�Ӧһ���󶨼�������ִ�в�ѯ����
		// Access to the results is by variable name.
		// ͨ�����������ʽ��
		ResultSet resultsInstance = Result(sparqlInstance);

		return resultsInstance;
	}

	// ����ID��ѯ���Ӽ�������URI+����ֵ
	@Override
	public ResultSet checkSentencePropertyDependOnId(String yourId) {
		if (yourId.contains("@")) {
			yourId = yourId.substring(0, yourId.indexOf("@"));
		}

		// ������ʵ������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������
		String string1 = "SELECT ?instanceLabel ?propertyClass ?propertyAnswer ?propertyID ?propertyVersion ?propertyBook ?propertyScene ?propertySentencePattern ?propertyRelatedWords ?relationAnswer ?relationID ?relationVersion ?relationBook ?relationScene ?relationSentencePattern ?relationRelatedWords WHERE{ ?relationID <http://www.w3.org/2000/01/rdf-schema#label> \"����ID\"@zh. ?instance ?relationID \"";
		String string2 = "\"@zh."
				+ "?instance <http://www.w3.org/2000/01/rdf-schema#label> ?instanceLabel."

				+ "?instance <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> ?class."
				+ "?class <http://www.w3.org/2000/01/rdf-schema#label> ?propertyClass."

				+ "?relationAnswer <http://www.w3.org/2000/01/rdf-schema#label> \"�ش�\"@zh."
				+ "?instance ?relationAnswer ?propertyAnswer FILTER regex(?propertyAnswer, \""
				+ yourId
				+ "\")."

				+ "?relationID <http://www.w3.org/2000/01/rdf-schema#label> \"����ID\"@zh."
				+ "?instance ?relationID ?propertyID FILTER regex(?propertyID, \""
				+ yourId
				+ "\")."

				+ "?relationVersion <http://www.w3.org/2000/01/rdf-schema#label> \"���ӽ̲İ汾\"@zh."
				+ "?instance ?relationVersion ?propertyVersion FILTER regex(?propertyVersion, \""
				+ yourId
				+ "\")."

				+ "?relationBook <http://www.w3.org/2000/01/rdf-schema#label> \"���Ӳ���\"@zh."
				+ "?instance ?relationBook ?propertyBook FILTER regex(?propertyBook, \""
				+ yourId
				+ "\")."

				+ "?relationScene <http://www.w3.org/2000/01/rdf-schema#label> \"�龳�Ի�\"@zh."
				+ "?instance ?relationScene ?propertyScene FILTER regex(?propertyScene, \""
				+ yourId
				+ "\")."

				+ "?relationSentencePattern <http://www.w3.org/2000/01/rdf-schema#label> \"��Ҫ����\"@zh."
				+ "?instance ?relationSentencePattern ?propertySentencePattern FILTER regex(?propertySentencePattern, \""
				+ yourId
				+ "\")."

				+ "?relationRelatedWords <http://www.w3.org/2000/01/rdf-schema#label> \"��ص���\"@zh."
				+ "?instance ?relationRelatedWords ?propertyRelatedWords FILTER regex(?propertyRelatedWords, \""
				+ yourId + "\")." + "}";

		String sparqlInstance = string1 + yourId + string2;
		// System.out.println(sparqlInstance);

		// Results from a query in a table-like manner for SELECT queries.
		// ��SELECT��ѯ��������еĲ�ѯ���
		// Each row corresponds to a set of bindings which fulfil the conditions
		// of the query.
		// ÿһ�ж�Ӧһ���󶨼�������ִ�в�ѯ����
		// Access to the results is by variable name.
		// ͨ�����������ʽ��
		ResultSet resultsInstance = Result(sparqlInstance);

		return resultsInstance;
	}

	// ����ID��ѯ���ʼ�������URI+����ֵ
	@Override
	public ResultSet checkPropertyDependOnId(String yourId) {

		if (yourId.contains("@")) {
			yourId = yourId.substring(0, yourId.indexOf("@"));
		}

		String string1 = "SELECT ?instance ?instanceLabel ?propertyClass ?propertyID ?propertyChinese ?propertyFunction ?propertyTopic ?propertyBook ?propertyAntonym ?propertySynonyms ?propertyCommonUse ?propertyExtend ?propertyScene ?propertyExpand ?propertyVersion ?propertyUse ?propertyNcyclopedia ?propertyAssociate ?propertyPartsOfSpeech ?propertyWordProperty ?propertyText ?propertyDifficulty ?relationID ?relationChinese ?relationFunction ?relationTopic ?relationBook ?relationAntonym ?relationSynonyms ?relationCommonUse ?relationExtend ?relationScene ?relationExpand ?relationVersion ?relationUse ?relationNcyclopedia ?relationAssociate ?relationPartsOfSpeech ?relationWordProperty ?relationText ?relationDifficulty WHERE{?relationID <http://www.w3.org/2000/01/rdf-schema#label> \"����ID\"@zh. ?instance ?relationID \"";
		String string2 = "\"@zh."
				+ "?instance <http://www.w3.org/2000/01/rdf-schema#label> ?instanceLabel."

				+ "?instance <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> ?class."
				+ "?class <http://www.w3.org/2000/01/rdf-schema#label> ?propertyClass."

				+ "?relationID <http://www.w3.org/2000/01/rdf-schema#label> \"����ID\"@zh."
				+ "?instance ?relationID ?propertyID FILTER regex(?propertyID, \""
				+ yourId
				+ "\")."

				+ "?relationChinese <http://www.w3.org/2000/01/rdf-schema#label> \"���ĺ���\"@zh."
				+ "?instance ?relationChinese ?propertyChinese FILTER regex(?propertyChinese, \""
				+ yourId
				+ "\")."

				+ "?relationFunction <http://www.w3.org/2000/01/rdf-schema#label> \"����-��������\"@zh."
				+ "?instance ?relationFunction ?propertyFunction FILTER regex(?propertyFunction, \""
				+ yourId
				+ "\")."

				+ "?relationTopic <http://www.w3.org/2000/01/rdf-schema#label> \"����-����\"@zh."
				+ "?instance ?relationTopic ?propertyTopic FILTER regex(?propertyTopic, \""
				+ yourId
				+ "\")."

				+ "?relationBook <http://www.w3.org/2000/01/rdf-schema#label> \"���ʲ���\"@zh."
				+ "?instance ?relationBook ?propertyBook FILTER regex(?propertyBook, \""
				+ yourId
				+ "\")."

				+ "?relationAntonym <http://www.w3.org/2000/01/rdf-schema#label> \"�����\"@zh."
				+ "?instance ?relationAntonym ?propertyAntonym FILTER regex(?propertyAntonym, \""
				+ yourId
				+ "\")."

				+ "?relationSynonyms <http://www.w3.org/2000/01/rdf-schema#label> \"ͬ���\"@zh."
				+ "?instance ?relationSynonyms ?propertySynonyms FILTER regex(?propertySynonyms, \""
				+ yourId
				+ "\")."

				+ "?relationCommonUse <http://www.w3.org/2000/01/rdf-schema#label> \"����\"@zh."
				+ "?instance ?relationCommonUse ?propertyCommonUse FILTER regex(?propertyCommonUse, \""
				+ yourId
				+ "\")."

				+ "?relationExtend <http://www.w3.org/2000/01/rdf-schema#label> \"��������\"@zh."
				+ "?instance ?relationExtend ?propertyExtend FILTER regex(?propertyExtend, \""
				+ yourId
				+ "\")."

				+ "?relationScene <http://www.w3.org/2000/01/rdf-schema#label> \"�龳����\"@zh."
				+ "?instance ?relationScene ?propertyScene FILTER regex(?propertyScene, \""
				+ yourId
				+ "\")."

				+ "?relationExpand <http://www.w3.org/2000/01/rdf-schema#label> \"��չ\"@zh."
				+ "?instance ?relationExpand ?propertyExpand FILTER regex(?propertyExpand, \""
				+ yourId
				+ "\")."

				+ "?relationVersion <http://www.w3.org/2000/01/rdf-schema#label> \"���ʽ̲İ汾\"@zh."
				+ "?instance ?relationVersion ?propertyVersion FILTER regex(?propertyVersion, \""
				+ yourId
				+ "\")."

				+ "?relationUse <http://www.w3.org/2000/01/rdf-schema#label> \"�÷�\"@zh."
				+ "?instance ?relationUse ?propertyUse FILTER regex(?propertyUse, \""
				+ yourId
				+ "\")."

				+ "?relationNcyclopedia <http://www.w3.org/2000/01/rdf-schema#label> \"�ٿ�\"@zh."
				+ "?instance ?relationNcyclopedia ?propertyNcyclopedia FILTER regex(?propertyNcyclopedia, \""
				+ yourId
				+ "\")."

				+ "?relationAssociate <http://www.w3.org/2000/01/rdf-schema#label> \"����\"@zh."
				+ "?instance ?relationAssociate ?propertyAssociate FILTER regex(?propertyAssociate, \""
				+ yourId
				+ "\")."

				+ "?relationPartsOfSpeech <http://www.w3.org/2000/01/rdf-schema#label> \"����\"@zh."
				+ "?instance ?relationPartsOfSpeech ?propertyPartsOfSpeech FILTER regex(?propertyPartsOfSpeech, \""
				+ yourId
				+ "\")."

				+ "?relationWordProperty <http://www.w3.org/2000/01/rdf-schema#label> \"��������\"@zh."
				+ "?instance ?relationWordProperty ?propertyWordProperty FILTER regex(?propertyWordProperty, \""
				+ yourId
				+ "\")."

				+ "?relationText <http://www.w3.org/2000/01/rdf-schema#label> \"����ԭ��\"@zh."
				+ "?instance ?relationText ?propertyText FILTER regex(?propertyText, \""
				+ yourId
				+ "\")."

				+ "?relationDifficulty <http://www.w3.org/2000/01/rdf-schema#label> \"�Ѷ�\"@zh."
				+ "?instance ?relationDifficulty ?propertyDifficulty FILTER regex(?propertyDifficulty, \""
				+ yourId + "\")." + "}";

		String sparqlInstance = string1 + yourId + string2;
		// System.out.println(sparqlInstance);

		// Results from a query in a table-like manner for SELECT queries.
		// ��SELECT��ѯ��������еĲ�ѯ���
		// Each row corresponds to a set of bindings which fulfil the conditions
		// of the query.
		// ÿһ�ж�Ӧһ���󶨼�������ִ�в�ѯ����
		// Access to the results is by variable name.
		// ͨ�����������ʽ��
		ResultSet resultsInstance = Result(sparqlInstance);

		return resultsInstance;
	}

	// �ж����ݿ����Ƿ���ڴ�ʵ��
	@Override
	public boolean checkIfInDB(String Instance) {
		String sqarqlCheckIfInDB = "SELECT ?instance WHERE{?instance <http://www.w3.org/2000/01/rdf-schema#label> \""
				+ Instance + "\"@zh.}";
		ResultSet result = Result(sqarqlCheckIfInDB);

		if (result.hasNext()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public ResultSet checkAllTriple() {

		String sparql = "SELECT ?s ?o WHERE{?s <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> ?o}";
		ResultSet resultsAllTriple = Result(sparql);// �Ѳ�ѯ��������ʵ��?s����?o���ڽ������

		return resultsAllTriple;
	}

	public ResultSet checkAllSameVersion_SameBook_SameUnit_ID(
			String yourInstance) {
		String sparql = "SELECT ?s ?o WHERE{?s <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> ?o}";
		ResultSet resultsAllTriple = Result(sparql);// �Ѳ�ѯ��������ʵ��?s����?o���ڽ������

		return resultsAllTriple;
	}

	public ResultSet checkPropertySameAs(String yourInstance) {

		if (yourInstance.contains("@")) {
			yourInstance = yourInstance.substring(0, yourInstance.indexOf("@"));
		}

		String sparql = "SELECT ?objectSameAs WHERE{?instance <http://www.w3.org/2000/01/rdf-schema#label> \""
				+ yourInstance
				+ "\"@zh. ?instance <"
				+ OWL.sameAs
				+ "> ?objectSameAs}";
		ResultSet resultsAllTriple = Result(sparql);// �Ѳ�ѯ��������ʵ��?s����?o���ڽ������

		return resultsAllTriple;

	}

	// ֻ����������
	@Override
	public ResultSet checkOnlyPropertyLabel(String yourPropertyURI) {

		String sparql = "SELECT ?propertyLabel WHERE{<"
				+ yourPropertyURI
				+ "> <http://www.w3.org/2000/01/rdf-schema#label> ?propertyLabel}";
		ResultSet resultsPropertyLabel = Result(sparql);// �Ѳ�ѯ��������ʵ��?s����?o���ڽ������

		return resultsPropertyLabel;
	}

	// ����ID�����丸��+��ǩLabel+ID����Ԫ��(Ϊ�޸�������Ƶ�)
	@Override
	public ResultSet checkClass_Label(String yourID) {

		if (yourID.contains("@")) {
			yourID = yourID.substring(0, yourID.indexOf("@"));
		}

		String string1 = "SELECT ?instance ?instanceLabel ?propertyClass ?relationID ?propertyID WHERE{ ?relationID <http://www.w3.org/2000/01/rdf-schema#label> \"����ID\"@zh. ?instance ?relationID \"";
		String string2 = "\"@zh."
				+ "?instance <http://www.w3.org/2000/01/rdf-schema#label> ?instanceLabel."

				+ "?instance <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> ?class."
				+ "?class <http://www.w3.org/2000/01/rdf-schema#label> ?propertyClass."

				+ "?instance ?relationID ?propertyID.}";

		String sparqlClass_Label = string1 + yourID + string2;

		ResultSet resultsClass_Label = Result(sparqlClass_Label);

		return resultsClass_Label;
	}

	// �����꼶���е���
	@Override
	public ResultSet checkAllWordsOfThisGrade(String yourGrade) {
		String string1 = "SELECT ?propertyBook ?instanceLabel WHERE{?relationBook <http://www.w3.org/2000/01/rdf-schema#label> \"���ʲ���\"@zh. ?instance ?relationBook ?propertyBook FILTER regex(?propertyBook, \"";

		String string2 = "\"\"). ?instance <http://www.w3.org/2000/01/rdf-schema#label> ?instanceLabel.}";

		String sparqlAllWordsOfThisGrade = string1 + yourGrade + string2;

		ResultSet resultsAllWordsOfThisGrade = Result(sparqlAllWordsOfThisGrade);

		return resultsAllWordsOfThisGrade;
	}

	// ��ѯ���еĵ��ʵ�ID
	public ResultSet checkIdOfAllWords() {
		String sparqlIdOfAllWords = "SELECT ?propertyID WHERE{?relationID <http://www.w3.org/2000/01/rdf-schema#label> \"����ID\"@zh. ?instance ?relationID ?propertyID.}";

		ResultSet resultsAllWordsOfThisGrade = Result(sparqlIdOfAllWords);

		return resultsAllWordsOfThisGrade;
	}

	// ��ѯ���еľ��ӵ�ID
	public ResultSet checkIdOfAllSentences() {
		String sparqlIdOfAllWords = "SELECT ?propertyID WHERE{?relationID <http://www.w3.org/2000/01/rdf-schema#label> \"����ID\"@zh. ?instance ?relationID ?propertyID.}";

		ResultSet resultsAllWordsOfThisGrade = Result(sparqlIdOfAllWords);

		return resultsAllWordsOfThisGrade;
	}

	// ��ѯĳ�꼶�����е���ID
	public ResultSet checkAllIdOfThisGrade(String yourGrade) {
		int yourGradeInt = Integer.parseInt(yourGrade);
		yourGradeInt = yourGradeInt * 2;
		String sparqlIdOfAllIdOfThisGrade = "SELECT ?propertyBook"
				+ "WHERE{?relationBook <http://www.w3.org/2000/01/rdf-schema#label> \"���ʲ���\"@zh."
				+ "?instance ?relationBook ?propertyBook.FILTER regex(?propertyBook,\""
				+ Integer.valueOf(yourGradeInt - 1)
				+ "\").FILTER regex(?propertyBook,\""
				+ Integer.valueOf(yourGradeInt) + "\").}";

		ResultSet resultsIdOfAllIdOfThisGrade = Result(sparqlIdOfAllIdOfThisGrade);

		return resultsIdOfAllIdOfThisGrade;
	}

	// ��ѯĳ����-������������е��ʵ�ID
	public ResultSet checkAllIdOfThisPropertyFunction(
			String yourPropertyFunction) {
		String sparqlAllIdOfThisPropertyFunction = "SELECT ?instance ?propertyFunction"
				+ "WHERE{?relationFunction <http://www.w3.org/2000/01/rdf-schema#label> \"����-��������\"@zh."
				+ "?instance ?relationFunction ?propertyFunction.FILTER regex(?propertyFunction,\""
				+ yourPropertyFunction + "\").}";
		ResultSet resultAllIdOfThisPropertyFunction = Result(sparqlAllIdOfThisPropertyFunction);

		return resultAllIdOfThisPropertyFunction;
	}

	// ��ѯĳ����-��������е��ʵ�ID
	public ResultSet checkAllIdOfThisPropertyTopic(String yourPropertyTopic) {
		String sparqlAllIdOfThisPropertyTopic = "SELECT ?instance ?propertyTopic"
				+ "WHERE{?relationTopic <http://www.w3.org/2000/01/rdf-schema#label> \"����-����\"@zh."
				+ "?instance ?relationTopic ?propertyTopic.FILTER regex(?propertyTopic,\""
				+ yourPropertyTopic + "\").}";
		ResultSet resultAllIdOfThisPropertyTopic = Result(sparqlAllIdOfThisPropertyTopic);

		return resultAllIdOfThisPropertyTopic;
	}

	// ���ݰ汾���Ҹð汾���е���
	public ResultSet checkAllWordsOfAVersion(String yourVersion) {
		String sparqlAllWordsOfAVersion = "SELECT ?propertyVersion"
				+ " WHERE{?relationVersion <http://www.w3.org/2000/01/rdf-schema#label> \"���ʽ̲İ汾\"@zh."
				+ "?instance ?relationVersion ?propertyVersion FILTER regex(?propertyVersion, \""
				+ yourVersion + "\").}";
		ResultSet resultAllWordsOfAVersion = Result(sparqlAllWordsOfAVersion);

		return resultAllWordsOfAVersion;
	}

	// ----------------------------------------------------------------------------------------------------------------
	// ����ʵ��+ĳ����+����ֵ�����Ҹ���Ԫ��
	@Override
	public ResultSet checkThisTriple(String yourID, String propertyLabel,
			String flag) {
		String sparql = "SELECT ?propertyLabel ?instance ?relation ?instanceLabel WHERE{"
				+ "?relationID <http://www.w3.org/2000/01/rdf-schema#label> \""
				+ flag
				+ "ID\"@zh."
				+ "?instance ?relationID \""
				+ yourID
				+ "\"@zh."
				+ "?instance <http://www.w3.org/2000/01/rdf-schema#label> ?propertyLabel."
				+ "?relation <http://www.w3.org/2000/01/rdf-schema#label> \""
				+ propertyLabel
				+ "\"@zh."
				+ "?instance ?relation ?instanceLabel.}";

		ResultSet result = Result(sparql);

		return result;
	}

	// ����ʵ��Label��������ID
	@Override
	public ResultSet checkAllID(String yourInstance, String flag) {

		String sparql = null;
		if (flag.equals("31")) {
			sparql = "SELECT ?allID WHERE{?instance <http://www.w3.org/2000/01/rdf-schema#label> \""
					+ yourInstance
					+ "\"@zh."
					+ "?relation <http://www.w3.org/2000/01/rdf-schema#label> \"����ID\"@zh."
					+ "?instance ?relation ?allID.}";
		} else {
			sparql = "SELECT ?allID WHERE{?instance <http://www.w3.org/2000/01/rdf-schema#label> \""
					+ yourInstance
					+ "\"@zh."
					+ "?relation <http://www.w3.org/2000/01/rdf-schema#label> \"����ID\"@zh."
					+ "?instance ?relation ?allID.}";
		}

		ResultSet result = Result(sparql);

		return result;
	}

	@Override
	public ResultSet checkOnlyInstanceURI(String yourInstance) {

		String sparql = "SELECT ?instance WHERE{?instance <http://www.w3.org/2000/01/rdf-schema#label> \""
				+ yourInstance + "\"@zh.}";
		ResultSet result = Result(sparql);

		return result;
	}

	@Override
	public ResultSet checkTopicValue(String yourWord) {

		String sparqlTopicValue = "SELECT ?propertyTopic ?propertyFunction ?relationTopic ?relationFunction WHERE{?instance <http://www.w3.org/2000/01/rdf-schema#label> \""
				+ yourWord
				+ "\"@zh. ?relationTopic <http://www.w3.org/2000/01/rdf-schema#label> \"����-����\"@zh. ?instance ?relationTopic ?propertyTopic."
				+ "?relationFunction <http://www.w3.org/2000/01/rdf-schema#label> \"����-��������\"@zh."
				+ "?instance ?relationFunction ?propertyFunction.}";
		ResultSet resultTopicValue = Result(sparqlTopicValue);
		return resultTopicValue;
	}

	@Override
	public ResultSet checkBrotherID(String yourThemeValueFlag1,
			String yourThemeValueFlag2) {
		String sparqlBrotherIndividual = "SELECT ?propertyTheme WHERE{?instance ?relationTheme ?propertyTheme.FILTER regex(?propertyTheme,\""
				+ yourThemeValueFlag1
				+ "\") FILTER regex(?propertyTheme,\""
				+ yourThemeValueFlag2 + "\").}";

		ResultSet resultBrotherIndividual = Result(sparqlBrotherIndividual);
		return resultBrotherIndividual;
	}

	@Override
	public ResultSet checkBrotherID2(String yourTheme) {
		String sparqlBrotherIndividual = "SELECT ?propertyTheme WHERE{?instance ?relationTheme ?propertyTheme.FILTER regex(?propertyTheme,\""
				+ yourTheme + "\").}";

		ResultSet resultBrotherIndividual = Result(sparqlBrotherIndividual);
		return resultBrotherIndividual;
	}

	// ��̬����������SPARQL����ѯ����������Ӧ�����-----------------------------------------------------------------------------------------------------------
	private static ResultSet Result(String sparql) {
		// public static Query create(String queryString)
		// Create a SPARQL query from the given string.
		// �Ӹ�����string�д���һ��SPARQL��ѯ
		Query queryInstance = QueryFactory.create(sparql);
		// public static QueryExecution sparqlService(String service,Query
		// query)
		// Create a QueryExecution that will access a SPARQL service over HTTP
		// ����һ��QueryExecution��������HTTP�Ϸ���SPARQL����
		QueryExecution qexecInstance = QueryExecutionFactory.sparqlService(
				SERVER, queryInstance);
		// ResultSet execSelect()
		// Execute a SELECT query
		// ִ��һ��SELECT��ѯ
		// Important: The name of this method is somewhat of a misnomer in that
		// depending on the underlying implementation this typically does not
		// execute the SELECT query but rather answers a wrapper over an
		// internal data structure that can be used to answer the query. In
		// essence calling this method only returns a plan for executing this
		// query which only gets evaluated when you actually start iterating
		// over the results.
		ResultSet results = qexecInstance.execSelect();
		return results;
	}

}
