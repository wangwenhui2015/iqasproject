package com.noumenon.OntologyManage.Impl; 
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.vocabulary.OWL;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.RDFS;
import com.noumenon.AddDeleteModifyQuery.Add.AddIndividualAndProperty;
import com.noumenon.AddDeleteModifyQuery.Add.Impl.AddIndividualAndPropertyImpl;
import com.noumenon.AddDeleteModifyQuery.Delete.DeleteIndividual;
import com.noumenon.AddDeleteModifyQuery.Delete.Impl.DeleteIndividualImpl;
import com.noumenon.AddDeleteModifyQuery.Query.QueryWithManyWays;
import com.noumenon.AddDeleteModifyQuery.Query.Impl.QueryWithManyWaysImpl;
import com.noumenon.AddDeleteModifyQuery.WriteOwl.WriteOwl;
import com.noumenon.AddDeleteModifyQuery.WriteOwl.Impl.WriteOwlImpl;
import com.noumenon.MyReasoner.MyReasoner;
import com.noumenon.MyReasoner.Impl.MyReasonerImpl;
import com.noumenon.OntologyManage.OntologyManage;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class OntologyManageImpl implements OntologyManage {

	private static String[] propertyLabel = new String[] { "����ID", "����",
			"����-��������", "����-����", "Hownet�еĸ���", "����", "��������", "���ĺ���", "���ʽ̲İ汾",
			"���ʲ���", "�Ѷ�", "����ԭ��", "�龳����", "����", "ͬ���", "�����", "��չ", "�ٿ�", "�÷�",
			"��������", "����" };
	private static String[] propertySPARQLValue = { "?propertyID",
			"?instanceLabel", "?propertyFunction", "?propertyTopic",
			"?propertyClass", "?propertyPartsOfSpeech",
			"?propertyWordProperty", "?propertyChinese", "?property",
			"?propertyBook", "?propertyDifficulty", "?propertyText",
			"?propertyScene", "?propertyAssociate", "?propertyAntonym",
			"?propertySynonyms", "?propertyExtend", "?propertyNcyclopedia",
			"?propertyUse", "?propertyExpand", "?propertyCommonUse" };
	private static String[] propertyRelation = { "?relationID",
			"?relationInstance", "?relationFunction", "?relationTopic",
			"?relationClass", "?relationPartsOfSpeech",
			"?relationWordProperty", "?relationChinese", "?relationVersion",
			"?relationBook", "?relationDifficulty", "?relationText",
			"?relationScene", "?relationAssociate", "?relationAntonym",
			"?relationSynonyms", "?relationExtend", "?relationNcyclopedia",
			"?relationUse", "?relationExpand", "?relationCommonUse" };
	private static String[] sentencePropertyLabel = new String[] { "����ID",
			"���ӽ̲İ汾", "���Ӳ���", "����", "�������", "�ش�", "�龳�Ի�", "��Ҫ����", "��ص���" };
	private static String[] sentencePropertySPARQLValue = { "?propertyID",
			"?propertyVersion", "?propertyBook", "?propertyClass",
			"?instanceLabel", "?propertyAnswer", "?propertyScene",
			"?propertySentencePattern", "?propertyRelatedWords" };
	private static String[] sentencePropertyRelation = { "?relationID",
			"?relationVersion", "?relationBook", "?relationClass",
			"?instanceLabel", "?relationAnswer", "?relationScene",
			"?relationSentencePattern", "?relationRelatedWords" };

	// ���캯��
	public OntologyManageImpl() {

	}

	@SuppressWarnings("rawtypes")
	public static HashMap<String, Vector> ALLWORDS;
	@SuppressWarnings("rawtypes")
	public static HashMap<String, Vector> ALLMEANS;

	private static AddIndividualAndProperty addIndividualAndProperty = new AddIndividualAndPropertyImpl();
	private static DeleteIndividual deleteIndividual = new DeleteIndividualImpl();;
	private static QueryWithManyWays queryWithManyWays = new QueryWithManyWaysImpl();
	private static WriteOwl writeOwl = new WriteOwlImpl();
	private static MyReasoner myReasoner = new MyReasonerImpl();

	@Override
	public void Add(String[] parameter) {

		// ��ӵ��ʸ����Label
		addIndividualAndProperty.addClass(parameter[4], parameter[1]);
		addIndividualAndProperty.addLabel(parameter[1]);

		// ��ӵ�������
		int i = 0;
		for (i = 0; i < parameter.length; i++) {
			System.out.println(i + parameter[i]);
			if (i == 1 || i == 4) {

			} else {
				// ����public void addProperty(String propertyLabel, String
				// yourInstance,String yourProperty)
				addIndividualAndProperty.addProperty(propertyLabel[i],
						parameter[1], parameter[i]);
			}
		}

	}

	@Override
	public void AddSentence(String[] parameter) {

		// ��ӵ��ʸ����Label
		addIndividualAndProperty.addSentenceClass(parameter[4], parameter[3]);
		addIndividualAndProperty.addSentenceLabel(parameter[3]);

		// ��ӵ�������
		int i = 0;
		for (i = 0; i < parameter.length; i++) {
			System.out.println(i + parameter[i]);
			if (i == 3 || i == 4) {

			} else {
				// ����public void addProperty(String propertyLabel, String
				// yourInstance,String yourProperty)
				addIndividualAndProperty.addSentenceProperty(
						sentencePropertyLabel[i], parameter[3], parameter[i]);
			}
		}
	}

	@Override
	public void AddBatch(InputStream yourPath) throws BiffException,
			IOException {
		List<String[]> list = readExcel(yourPath);

		String[] str = null;
		for (int i = 0; i < list.size(); i++) {
			str = (String[]) list.get(i);
			for (int j = 0; j < str.length; j++) {
				System.out.println("Ҫ��ӵ�Excel�е�" + propertyLabel[j] + "����:"
						+ str[j]);
			}

			// ���Լ���excel��Ҫע�͵���������
			String yourClass = null;
			yourClass = str[4];
			System.out.println("������ӵ���:" + yourClass);

			String yourInstance = null;
			yourInstance = str[1];
			System.out.println("������ӵ�ʵ��:" + yourInstance);

			// ���Լ���excel��Ҫע�͵�����
			if (yourClass.equals("���Ȳ���Prot��g������д��")) {

			} else {
				addIndividualAndProperty.addClass(yourClass, yourInstance);
				addIndividualAndProperty.addLabel(yourInstance);
				String yourProperty;
				for (int k = 0; k < 21; k++) {
					if (propertyLabel[k].equals("����")
							|| propertyLabel[k].equals("Hownet�еĸ���")) {
						// ��Ϊ�����ʡ���Hownet�еĸ��ࡱʱ��ʲô������
					} else {
						yourProperty = null;
						yourProperty = str[k];
						addIndividualAndProperty.addProperty(propertyLabel[k],
								yourInstance, yourProperty);
					}
				}
			}
			// ������

			// ���Լ���excelҪע�͵�����
			// String yourClass = findDEF(yourInstance);
			// System.out.println("��ѡ��ĸ��ࣨ���أ���" + yourClass);

			// if (yourClass == null) {
			// System.out.println(yourInstance + "�ĸ��಻��HowNet��");
			// } else {
			// addIndividualAndProperty.addInstance(yourClass, yourInstance);
			// }
			// ������
		}
		System.out.println("�������ʲ���ɹ���");
	}

	@Override
	public void AddWordBatch(InputStream yourPath) throws BiffException,
			IOException {
		List<String[]> list = readExcel(yourPath);

		String[] str = null;
		for (int i = 0; i < list.size(); i++) {
			str = (String[]) list.get(i);
			for (int j = 0; j < str.length; j++) {
				System.out.println("Ҫ��ӵ�Excel�е�����" + str[j]);
			}

			// ���Լ���excel��Ҫע�͵���������
			String yourClass = null;
			yourClass = str[4];
			System.out.println("������ӵ���:" + yourClass);

			String yourInstance = null;
			yourInstance = str[1];
			yourInstance = yourInstance.replace(" ", "_");// ���ʵ�����пո��򽫿ո��滻���»���
			System.out.println("������ӵ�ʵ��:" + yourInstance);

			// ���Լ���excel��Ҫע�͵�����
			// if (yourClass.equals("���Ȳ���Prot��g������д��")) {
			if (yourClass.equals("��")) {
				// ���HowNet�ĸ���Ϊ���ޡ�����ʲô������
			} else {
				// addIndividualAndProperty.addClass(yourClass, yourInstance);
				// addIndividualAndProperty.addLabel(yourInstance);
				
				/*addIndividualAndProperty.addWordClass(yourClass, yourInstance);
				addIndividualAndProperty.addWordLabel(yourInstance);
*/
				String yourProperty;
				for (int k = 0; k < 21; k++) {
					if (propertyLabel[k].equals("����")
							|| propertyLabel[k].equals("Hownet�еĸ���")) {
						// ��Ϊ�����ʡ���Hownet�еĸ��ࡱʱ��ʲô������
					} else {
						yourProperty = null;
						yourProperty = str[k];
						yourProperty = yourProperty.replace("\n", "");// ��������д��ڻس����򽫻س�ɾ��
						addIndividualAndProperty.addProperty(propertyLabel[k],
								yourInstance, yourProperty);
					}
				}
			}
			// ������

			// ���Լ���excelҪע�͵�����
			// String yourClass = findDEF(yourInstance);
			// System.out.println("��ѡ��ĸ��ࣨ���أ���" + yourClass);

			// if (yourClass == null) {
			// System.out.println(yourInstance + "�ĸ��಻��HowNet��");
			// } else {
			// addIndividualAndProperty.addInstance(yourClass, yourInstance);
			// }
			// ������
		}
		System.out.println("�������ʲ���ɹ���");
	}

	@Override
	public void AddSentenceBatch(InputStream yourPath) throws BiffException,
			IOException {

		List<String[]> list = readExcel(yourPath);

		String[] str = null;
		for (int i = 0; i < list.size(); i++) {
			str = (String[]) list.get(i);
			for (int j = 0; j < str.length; j++) {
				System.out.println("Ҫ��ӵ�Excel�е�����" + str[j]);
			}

			String yourClass = null;
			yourClass = str[3];
			System.out.println("������ӵ���:" + yourClass + "\n");

			String yourInstance = null;
			yourInstance = str[4];
			System.out.println("������ӵ�ʵ��:" + yourInstance + "\n");

			if (yourClass.equals("���Ȳ���Prot��g������д��")) {

			} else {
				addIndividualAndProperty.addSentenceClass(yourClass,
						yourInstance);
				addIndividualAndProperty.addSentenceLabel(yourInstance);
			}

			String yourProperty;
			for (int k = 0; k < sentencePropertyLabel.length; k++) {
				if (sentencePropertyLabel[k].equals("�������")
						|| sentencePropertyLabel[k].equals("����")) {
					// ��Ϊ��������͡������⡱ʱ��ʲô������
				} else {
					yourProperty = null;
					yourProperty = str[k];
					addIndividualAndProperty.addSentenceProperty(
							sentencePropertyLabel[k], yourInstance,
							yourProperty);
				}
			}
		}
		System.out.println("�������Ӳ���ɹ���");
	}

	@Override
	public void Delete(String yourInstanceID) {

		ResultSet resultsProperty = queryWithManyWays
				.checkPropertyDependOnId(yourInstanceID);

		if (resultsProperty.hasNext()) {
			while (resultsProperty.hasNext()) {

				QuerySolution solutionInstance = resultsProperty.next();

				if (solutionInstance.get("?relationID").toString()
						.contains("31")) {// ����ǵ�����ִ��

					int i = 0;
					for (i = 0; i < propertyLabel.length; i++) {
						System.out.println("    ��������"
								+ propertyLabel[i]
								+ "��"
								+ substringManage(solutionInstance.get(
										propertySPARQLValue[i]).toString()));

						if (propertyLabel[i].equals("����")
								|| propertyLabel[i].equals("Hownet�еĸ���")) {

						} else {
							deleteIndividual.deleteInstanceProperty(
									solutionInstance.get("?instanceLabel")
											.toString(),
									solutionInstance.get(propertyRelation[i])
											.toString(),
									solutionInstance
											.get(propertySPARQLValue[i])
											.toString());

						}

					}

					// ɾ�������Label
					deleteIndividual.deleteClass(
							solutionInstance.get("?instanceLabel").toString(),
							solutionInstance.get("?propertyClass").toString());
					deleteIndividual.deleteLabel(solutionInstance.get(
							"?instanceLabel").toString());

					System.out.println("����ɾ���ɹ�\n");
				} else {
					// ����Ǿ�����ʲô������
				}
			}
		}

		else {
			System.out.println("֪ʶ�������û�д�ʵ��");
		}
	}

	@Override
	public void DeleteSentence(String yourInstanceID) {

		ResultSet resultsProperty = queryWithManyWays
				.checkSentencePropertyDependOnId(yourInstanceID);

		if (resultsProperty.hasNext()) {
			while (resultsProperty.hasNext()) {

				QuerySolution solutionInstance = resultsProperty.next();

				if (solutionInstance.get("?relationID").toString()
						.contains("85")) {// ����Ǿ�����ִ��
					int i = 0;
					for (i = 0; i < sentencePropertyLabel.length; i++) {
						System.out.println("    ��������"
								+ sentencePropertyLabel[i]
								+ "��"
								+ substringManage(solutionInstance.get(
										sentencePropertySPARQLValue[i])
										.toString()));

						if (sentencePropertyLabel[i].equals("����")
								|| sentencePropertyLabel[i].equals("�������")) {

						} else {
							deleteIndividual.deleteSentenceInstanceProperty(
									solutionInstance.get("?instanceLabel")
											.toString(),
									solutionInstance.get(
											sentencePropertyRelation[i])
											.toString(),
									solutionInstance.get(
											sentencePropertySPARQLValue[i])
											.toString());

						}

					}

					// ɾ�������Label
					deleteIndividual.deleteSentenceClass(
							solutionInstance.get("?instanceLabel").toString(),
							solutionInstance.get("?propertyClass").toString());
					deleteIndividual.deleteSentenceLabel(solutionInstance.get(
							"?instanceLabel").toString());

					System.out.println("����ɾ���ɹ�\n");
				} else {
					// ����ǵ�����ʲô������
				}

			}

		} else {
			System.out.println("֪ʶ�������û�д�ʵ��");
		}
	}

	@Override
	public void Modify(String yourID, String yourPropertyLabel,
			String yourSPARQLProperty, String yourRelationProperty) {

		// ���Ҷ�Ӧ����Ԫ��
		// ResultSet result = queryWithManyWays.checkThisTriple(yourID,
		// yourPropertyLabel, "����");
		ResultSet result = queryWithManyWays.checkPropertyDependOnId(yourID);
		QuerySolution solution = null;
		if (result.hasNext()) {
			while (result.hasNext()) {
				solution = result.next();
				if (yourPropertyLabel.equals("����ID")) {

					modifyID(solution, yourRelationProperty,
							yourSPARQLProperty, yourPropertyLabel);

				} else if (yourPropertyLabel.equals("Hownet�еĸ���")) {

					modifyClass(solution, yourSPARQLProperty);

				} else if (yourPropertyLabel.equals("����")) {

					modifyLabel(solution, yourID);
				} else {
					modifyPropertyValue(solution, yourRelationProperty,
							yourSPARQLProperty, yourPropertyLabel, yourID);
				}

			}
		} else {
			System.out.println("�޴���Ԫ��");
		}

		System.out.println("�޸ĳɹ�");
	}

	@Override
	public void ModifySentence(String yourID, String yourPropertyLabel,
			String yourSPARQLProperty, String yourRelationProperty) {

		// ���Ҷ�Ӧ����Ԫ��
		// ResultSet result = queryWithManyWays.checkThisTriple(yourID,
		// yourPropertyLabel, "����");
		ResultSet result = queryWithManyWays
				.checkSentencePropertyDependOnId(yourID);
		QuerySolution solution = null;
		if (result.hasNext()) {
			while (result.hasNext()) {
				solution = result.next();
				if (yourPropertyLabel.equals("����ID")) {

					modifySentenceID(solution, yourRelationProperty,
							yourSPARQLProperty, yourPropertyLabel);

				} else if (yourPropertyLabel.equals("�������")) {

					modifySentenceClass(solution, yourSPARQLProperty);

				} else if (yourPropertyLabel.equals("����")) {

					modifySentenceLabel(solution, yourID);

				} else {
					modifySentencePropertyValue(solution, yourRelationProperty,
							yourSPARQLProperty, yourPropertyLabel, yourID);
				}

			}
			System.out.println("�޸ĳɹ�");
		} else {
			System.out.println("�޴���Ԫ��");
		}
	}

	// ������������µ����е���Label
	public ResultSet QueryWord(String yourClass) {

		ResultSet resultsInstance = queryWithManyWays.checkInstance(yourClass);

		return resultsInstance;

	}

	// ������������µ����е���Label
	public List<ResultSet> QueryWordAndPropertiesDependOnClass(String yourClass) {

		// 1.���Ҹø����µ�����ID
		ResultSet resultsIDDependOnClass = queryWithManyWays
				.checkIDDependOnClass(yourClass);
		
		// 2.����ID��������
		List<ResultSet> resultsWordsAndProperties = new ArrayList<ResultSet>();
		if (resultsIDDependOnClass.hasNext()){
			while(resultsIDDependOnClass.hasNext()){
				QuerySolution solutionIDDependOnClass = resultsIDDependOnClass.next();

				resultsWordsAndProperties.add(QueryIndividualDependOnId(solutionIDDependOnClass.get("?propertyID").toString()));
			}
		} else {
			System.out.println("��HowNet������û�е��ʣ�");
		}

		return resultsWordsAndProperties;

	}

	// ����ID���Ҹõ��ʼ�����������
	@Override
	public ResultSet QueryIndividualDependOnId(String yourID) {

		ResultSet resultsInstance = queryWithManyWays
				.checkPropertyDependOnId(yourID);

		return resultsInstance;
	}

	// ��һ�����ʵ���������
	@Override
	public ResultSet QueryIndividual(String yourWord) {

		ResultSet resultsInstance = queryWithManyWays.checkProperty(yourWord);

		return resultsInstance;
	}

	// ��ѯ���ʶ�Ӧ������ID
	@Override
	public ResultSet QueryAWordAllId(String yourWord) {

		ResultSet resultsInstance = queryWithManyWays
				.checkAllIdOfAnWord(yourWord);

		return resultsInstance;
	}

	// ��ѯ���Ӷ�Ӧ������ID
	@Override
	public ResultSet QueryASentenceAllId(String yourSentence) {

		ResultSet resultsInstance = queryWithManyWays
				.checkAllIdOfAnSentence(yourSentence);

		return resultsInstance;
	}

	// ����ID���Ҹþ��Ӽ�����������
	@Override
	public ResultSet QuerySentenceIndividualDependOnId(String yourID) {

		ResultSet resultsInstance = queryWithManyWays
				.checkSentencePropertyDependOnId(yourID);

		return resultsInstance;
	}

	@Override
	public ResultSet QuerySentenceIndividual(String yourSentence) {

		ResultSet resultsInstance = queryWithManyWays
				.checkSentenceProperty(yourSentence);

		return resultsInstance;
	}

	// �����꼶����ҳ�5������
	@Override
	public List<ResultSet> QueryFiveWordsOfThisGrade(String yourGrade) {

		// �ö�̬���鱣����꼶����ID
		List<String> allIdOfThisGrade = QueryAllWordsIdOfThisGrade(yourGrade);

		// ���ȡ5������ID��֮�����ÿ�����ʵ�����
		List<ResultSet> resultsFiveWordsOfThisGrade = new ArrayList<ResultSet>();
		if (allIdOfThisGrade.isEmpty()) {
			System.out.println("�����û�е��ʣ�");
		} else {// ���꼶�е���
			System.out.println("���꼶���е��ʵĸ�����" + allIdOfThisGrade.size());

			if (allIdOfThisGrade.size() < 5) {// ������ʸ���<5���������е���
				for (int i = 0; i < allIdOfThisGrade.size(); i++) {
					resultsFiveWordsOfThisGrade.add(queryWithManyWays
							.checkPropertyDependOnId(allIdOfThisGrade.get(i)
									.toString()));
				}
			} else {// ������ʸ���>=5������ѡ���ظ���5������
					// �����鱣�����ID�����ж��Ƿ����ظ�
				List<String> randomIdList = new ArrayList<String>();
				// ���ȡ5������ID��֮�����ÿ�����ʵ�����
				do {
					Random ra = new Random();
					int randomNum = ra.nextInt(allIdOfThisGrade.size() - 1);
					System.out.println("�������Ϊ��" + randomNum);
					String randomId = allIdOfThisGrade.get(randomNum)
							.toString();
					System.out.println("���IDΪ��" + randomId);

					int j;
					// �������ID�����е�ÿһ��Ԫ��
					for (j = 0; j < randomIdList.size(); j++) {
						// �ж��Ƿ����ظ���
						if (randomIdList.get(j).equals(randomId)) {
							break;
						}
					}
					if (j == randomIdList.size()) {// ���ظ�
						// �����ID�����
						randomIdList.add(randomId);
						resultsFiveWordsOfThisGrade.add(queryWithManyWays
								.checkPropertyDependOnId(randomId));
					} else {
						continue;
					}
				} while (resultsFiveWordsOfThisGrade.size() < 5);
			}
		}
		return resultsFiveWordsOfThisGrade;
	}

	@Override
	public Map<String, String> QuerySentenceAndId(String yourClass) {
		// ������Map�Ա����ֵ��<ID, instanceLabel>
		Map<String, String> idAndInstancelabelOfAllInstance = new HashMap<String, String>();

		// �����������о���
		ResultSet resultsInstance = queryWithManyWays.checkInstance(yourClass);

		// �Լ�ֵ�Ա���ÿһ��
		if (resultsInstance.hasNext()) {
			while (resultsInstance.hasNext()) {
				QuerySolution solutionInstance = resultsInstance.next();

				// ��ȡ����ǰ����
				String thisSentence = solutionInstance
						.get("?instanceLabel")
						.toString()
						.substring(
								0,
								solutionInstance.get("?instanceLabel")
										.toString().indexOf("@"));
				// ���ݵ�ǰ���Ӳ������ԣ�ȡ������ID
				ResultSet resultsAllPropertiesOfAInstance = queryWithManyWays
						.checkAllIdOfAnSentence(thisSentence);

				// �����ֵ��<ID, instanceLabel>
				while (resultsAllPropertiesOfAInstance.hasNext()) {
					QuerySolution solutionAllPropertiesOfAInstance = resultsAllPropertiesOfAInstance
							.next();

					// ��ȡ����ǰ���ӵ�ID
					String idOfThisSentence = solutionAllPropertiesOfAInstance
							.get("?propertyID").toString();
					// ȥ����@zh��
					idOfThisSentence = idOfThisSentence.substring(0,
							idOfThisSentence.indexOf("@"));

					// ���浽Map��
					idAndInstancelabelOfAllInstance.put(idOfThisSentence,
							thisSentence);

				}
			}
		} else {
			System.out.println("֪ʶ�������û�д�ʵ��");
		}
		return idAndInstancelabelOfAllInstance;
	}

	@Override
	public List<ResultSet> QueryTwoSentencesOfThisGrade(String yourGrade) {

		// �ö�̬���鱣����꼶����ID
		List<String> allSentencesIdOfThisGrade = QueryAllSentencesIdOfThisGrade(yourGrade);

		// ���ȡ2������ID��֮�����ÿ�����ʵ�����
		List<ResultSet> resultsTwoSentencesOfThisGrade = new ArrayList<ResultSet>();
		if (allSentencesIdOfThisGrade.isEmpty()) {
			System.out.println("�����û�о��ӣ�");
		} else {// ���꼶�о���
			System.out
					.println("���꼶���о��ӵĸ�����" + allSentencesIdOfThisGrade.size());

			if (allSentencesIdOfThisGrade.size() < 2) {// ������Ӹ���<2����ֻ��һ�����ӣ��򱣴���1�����Ӳ�����
				resultsTwoSentencesOfThisGrade
						.add(queryWithManyWays
								.checkSentencePropertyDependOnId(allSentencesIdOfThisGrade
										.get(0).toString()));
			} else {// ������Ӹ���>=2��ѡ���ظ���2������
				// �����鱣�����ID�����ж��Ƿ����ظ�
				List<String> randomIdList = new ArrayList<String>();

				// ���ȡ2������ID��֮�����ÿ�����ʵ�����
				do {
					Random ra = new Random();
					int randomNum = ra
							.nextInt(allSentencesIdOfThisGrade.size() - 1);
					System.out.println("�������Ϊ��" + randomNum);
					String randomId = allSentencesIdOfThisGrade.get(randomNum)
							.toString();
					System.out.println("���IDΪ��" + randomId);

					int j;
					// �������ID�����е�ÿһ��Ԫ��
					for (j = 0; j < randomIdList.size(); j++) {
						// �ж��Ƿ����ظ���
						if (randomIdList.get(j).equals(randomId)) {
							break;
						}
					}
					if (j == randomIdList.size()) {// ���ظ�
						// �����ID�����
						randomIdList.add(randomId);

						resultsTwoSentencesOfThisGrade.add(queryWithManyWays
								.checkSentencePropertyDependOnId(randomId));
					} else {
						continue;
					}
				} while (resultsTwoSentencesOfThisGrade.size() < 2);
			}
		}
		return resultsTwoSentencesOfThisGrade;
	}

	@Override
	public List<ResultSet> QueryThreeSentencesOfThisGrade(String yourGrade) {

		// �ö�̬���鱣����꼶����ID
		List<String> allSentencesIdOfThisGrade = QueryAllSentencesIdOfThisGrade(yourGrade);

		// ���ȡ3������ID��֮�����ÿ�����ʵ�����
		List<ResultSet> resultsTwoSentencesOfThisGrade = new ArrayList<ResultSet>();
		if (allSentencesIdOfThisGrade.isEmpty()) {
			System.out.println("�����û�о��ӣ�");
		} else {// ���꼶�о���
			System.out
					.println("���꼶���о��ӵĸ�����" + allSentencesIdOfThisGrade.size());

			if (allSentencesIdOfThisGrade.size() < 3) {// ������Ӹ���<3����ֻ��һ�����ӣ��򱣴���1�����Ӳ�����
				resultsTwoSentencesOfThisGrade
						.add(queryWithManyWays
								.checkSentencePropertyDependOnId(allSentencesIdOfThisGrade
										.get(0).toString()));
			} else {// ������Ӹ���>=3��ѡ���ظ���3������
				// �����鱣�����ID�����ж��Ƿ����ظ�
				List<String> randomIdList = new ArrayList<String>();

				// ���ȡ3������ID��֮�����ÿ�����ʵ�����
				do {
					Random ra = new Random();
					int randomNum = ra
							.nextInt(allSentencesIdOfThisGrade.size() - 1);
					System.out.println("�������Ϊ��" + randomNum);
					String randomId = allSentencesIdOfThisGrade.get(randomNum)
							.toString();
					System.out.println("���IDΪ��" + randomId);

					int j;
					// �������ID�����е�ÿһ��Ԫ��
					for (j = 0; j < randomIdList.size(); j++) {
						// �ж��Ƿ����ظ���
						if (randomIdList.get(j).equals(randomId)) {
							break;
						}
					}
					if (j == randomIdList.size()) {// ���ظ�
						// �����ID�����
						randomIdList.add(randomId);

						resultsTwoSentencesOfThisGrade.add(queryWithManyWays
								.checkSentencePropertyDependOnId(randomId));
					} else {
						continue;
					}
				} while (resultsTwoSentencesOfThisGrade.size() < 3);
			}
		}
		return resultsTwoSentencesOfThisGrade;
	}

	// �����꼶��ѯ��������е�3������
	@Override
	public List<String> QueryTwoDifferentThemeWordsOfThisGrade(
			String yourGrade, String yourWord) {
		List<String> saveThreeWords = new ArrayList<String>(); // �������յ�3������
		saveThreeWords.add(yourWord);

		// 1.���ݵ��ʲ��Ҷ�Ӧ������ID
		ResultSet resultAllIdOfThisWord = QueryAWordAllId(yourWord);

		// 2.����ID�ж��꼶
		Map<String, List<String>> allThemeOfThisWordMap = new HashMap<String, List<String>>(); // ���ڸõ��ʶ�Ӧ����ID������������
		if (resultAllIdOfThisWord.hasNext()) {
			while (resultAllIdOfThisWord.hasNext()) {

				QuerySolution solutionAllIdOfThisWord = resultAllIdOfThisWord
						.next();

				// 3.�ҳ��õ��ʵĲ�������
				String bookOfThisWord = interceptBook(solutionAllIdOfThisWord
						.get("?propertyID").toString());
				String gradeOfThisWord = bookToGrade(bookOfThisWord);

				// 4.�ж�������ʵ��꼶�Ƿ�����������꼶,����ȣ��򱣴�õ��ʵ�����-�������������-��������
				if (gradeOfThisWord.equals(yourGrade)) {

					// ����ID���Ҹõ��ʵ���������
					ResultSet resultAllPropertiesOfThisId = queryWithManyWays
							.checkPropertyDependOnId((solutionAllIdOfThisWord
									.get("?propertyID").toString()));
					List<String> themeOfThisWord = new ArrayList<String>(); // ���ڱ�����꼶�иõ��ʵ���������
					if (resultAllPropertiesOfThisId.hasNext()) {
						while (resultAllPropertiesOfThisId.hasNext()) {

							QuerySolution solutionAllPropertiesOfThisId = resultAllPropertiesOfThisId
									.next();

							// ��������
							themeOfThisWord.add(solutionAllPropertiesOfThisId
									.get("?propertyFunction").toString());
							themeOfThisWord.add(solutionAllPropertiesOfThisId
									.get("?propertyTopic").toString());

							// ����Map
							allThemeOfThisWordMap.put(
									solutionAllPropertiesOfThisId.get(
											"?propertyID").toString(),
									themeOfThisWord);
						}
					}

				} else { // ������ȣ���continue
					continue;
				}
			}
		} else {
			System.out.println("�õ��ʲ������ڱ������");
		}

		// 5.�����꼶�������е���ID
		List<String> allIdOfThisGradeList = QueryAllWordsIdOfThisGrade(yourGrade);

		while (saveThreeWords.size() <= 3) { // ��Ҫ������������������ĵ���
			int circleTimes = 0;

			// 6.�����ȡһ��ID
			Random ra = new Random();
			int randomNum = ra.nextInt(allIdOfThisGradeList.size() - 1);
			System.out.println("�������Ϊ��" + randomNum);
			String randomId = allIdOfThisGradeList.get(randomNum).toString();
			System.out.println("�������IDΪ��" + randomId);

			// 7.�Ա�ID�������Ƿ�һ�£���һ�������³�ȡ������һ���򱣴�
			for (String key : allThemeOfThisWordMap.keySet()) { // ����Map
				// �ж�ID�Ƿ�һ��
				if (randomId.equals(key)) { // ��һ�£�����������ѭ��
					continue;
				} else {
					circleTimes++;
				}
			}

			if (circleTimes < allThemeOfThisWordMap.size()) { // ID���ظ�����������������һ��ѭ��
				continue;
			} else { // ID���ظ������ٶԱ������Ƿ����ظ�
				List<String> randomThemeList = new ArrayList<String>(); // ������ʱ�洢��������

				// ��ѯ�����ID����������
				ResultSet resultsAllPropertisOfAWord = queryWithManyWays
						.checkPropertyDependOnId(randomId);
				if (resultsAllPropertisOfAWord.hasNext()) {
					while (resultsAllPropertisOfAWord.hasNext()) {

						QuerySolution solutionAllPropertisOfAWord = resultsAllPropertisOfAWord
								.next();
						String functionOfThisWord = substringManage(solutionAllPropertisOfAWord
								.get("?propertyFunction").toString()); // ��ID��Ӧ������-������������
						String topicOfThisWord = substringManage(solutionAllPropertisOfAWord
								.get("?propertyTopic").toString()); // ��ID��Ӧ������-��������

						int repeatTimes = 0;
						// ����Map���������ԶԱ�
						for (String key : allThemeOfThisWordMap.keySet()) {

							List<String> oneInfoOfMap = allThemeOfThisWordMap
									.get(key);

							// �����ַ�����Map�е����Ժ�������Զ���ȡ����
							String functionOfMapString = substringManage(oneInfoOfMap
									.get(0));
							String topicOfMapString = substringManage(oneInfoOfMap
									.get(1));

							if (functionOfMapString.equals(functionOfThisWord)
									&& !functionOfThisWord.equals("��")) { // ��propertyFunction�Ա���һ�£�����������ѭ��
								randomThemeList.add("��");
								repeatTimes++;
								continue;
							} else { // ��������propertyTopic�Ա���һ�£�����������ѭ��
								if (topicOfMapString.equals(topicOfThisWord)
										&& !topicOfThisWord.equals("��")) { // ��propertyTopic�Ա���һ�£�����������ѭ��
									randomThemeList.add("��");
									repeatTimes++;
									continue;
								} else { // ������ʱ����
									// ʲô������
								}
							}
						}

						// ����Map֮���ж��ظ�����
						if (repeatTimes == 0) { // ����ظ�����Ϊ�㣬�򱣴�
							// ���ձ���
							saveThreeWords
									.add(subStringManage(solutionAllPropertisOfAWord
											.get("?instanceLabel").toString()));
						} else {
							System.out.println("���������ʵ�������ͬ");
						}
					}
				} else {
					System.out.println("�����ڸ�ID");
				}
			}
		}

		return saveThreeWords;
	}

	@Override
	public List<String> QueryTwoWordsDependOnDifficulty(String yourGrade,
			String yourDifficultyOfWord1, String yourDifficultyOfWord2) {
		List<String> twoWordsDependOnDifficultyList = new ArrayList<String>(); // ���ڱ�������������

		// 1.���Ҹ��꼶���е��ʵ�ID
		List<String> allIdOfThisGradeList = QueryAllWordsIdOfThisGrade(yourGrade);

		while (twoWordsDependOnDifficultyList.size() < 2) { // ��Ҫ�������������Ӧ�����Ѷȵĵ���{
			// 2.�����ȡһ��ID
			Random ra = new Random();
			int randomNum = ra.nextInt(allIdOfThisGradeList.size() - 1);
			System.out.println("�������Ϊ��" + randomNum);
			String randomId = allIdOfThisGradeList.get(randomNum).toString();
			System.out.println("�������IDΪ��" + randomId);

			// 3.�Ա��Ѷ��Ƿ�һ�£���һ�������³�ȡ������һ���򱣴�
			ResultSet resultsAllPropertisOfAWord = queryWithManyWays
					.checkPropertyDependOnId(randomId);
			if (resultsAllPropertisOfAWord.hasNext()) {
				while (resultsAllPropertisOfAWord.hasNext()) {

					QuerySolution solutionAllPropertisOfAWord = resultsAllPropertisOfAWord
							.next();
					String difficultyOfThisWord = substringManage(solutionAllPropertisOfAWord
							.get("?propertyDifficulty").toString()); // ��ID��Ӧ���Ѷ�

					if (twoWordsDependOnDifficultyList.size() == 0) { // �Ѷ�1�ĵ���û�ҵ�
						if (difficultyOfThisWord.equals(yourDifficultyOfWord1)) { // ����������Ѷ��������Ѷ�1��ȣ��򱣴�õ���
							twoWordsDependOnDifficultyList
									.add(substringManage(solutionAllPropertisOfAWord
											.get("?instanceLabel").toString()));
						} else {
							continue;
						}
					} else { // �Ѷ�1�ĵ����ҵ��ˣ��Ѷ�2�ĵ���û�ҵ�
						if (difficultyOfThisWord.equals(yourDifficultyOfWord2)) { // ����������Ѷ��������Ѷ�1��ȣ��򱣴�õ���
							twoWordsDependOnDifficultyList
									.add(substringManage(solutionAllPropertisOfAWord
											.get("?instanceLabel").toString()));
						} else {
							continue;
						}
					}
				}
			}
		}

		return twoWordsDependOnDifficultyList;
	}

	@Override
	public List<ResultSet> TwoRandomWordsOfThisGrade(String yourGrade) {
		List<ResultSet> twoRandomWordsOfThisGradeList = new ArrayList<ResultSet>(); // ���ڱ�������������

		// 1.���Ҹ��꼶���е��ʵ�ID
		List<String> allIdOfThisGradeList = QueryAllWordsIdOfThisGrade(yourGrade);
		String ID1 = new String();

		while (twoRandomWordsOfThisGradeList.size() < 2) { // ��Ҫ�������������Ӧ�����Ѷȵĵ���{
			// 2.�����ȡһ��ID
			Random ra = new Random();
			int randomNum = ra.nextInt(allIdOfThisGradeList.size() - 1);
			System.out.println("�������Ϊ��" + randomNum);
			String randomId = allIdOfThisGradeList.get(randomNum).toString();
			System.out.println("�������IDΪ��" + randomId);

			ResultSet resultsAllPropertisOfAWord = queryWithManyWays
					.checkPropertyDependOnId(randomId);
			if (twoRandomWordsOfThisGradeList.size() == 0) { // ���һ�����ʻ�û�ҵ�������ӽ�List<ResultSet>��
				ID1 = randomId;
				twoRandomWordsOfThisGradeList.add(resultsAllPropertisOfAWord);
			} else { // ����ҵ���һ������Ҫ����ҵ��ĵڶ����Ƿ����һ���ظ�
				if (randomId.equals(ID1)) { // �����ȣ����ظ���������ѭ�����²���
					continue;
				} else { // �������ȣ��򱣴�
					twoRandomWordsOfThisGradeList
							.add(resultsAllPropertisOfAWord);
				}
			}
		}

		return twoRandomWordsOfThisGradeList;
	}

	@Override
	public Boolean IfExistInFuseki(String yourWord) {
		Boolean ifExistInFuseki = false;
		ResultSet resultOfyourWord = QueryAWordAllId(yourWord);
		if (resultOfyourWord.hasNext()) {
			ifExistInFuseki = true;
		} else {
			ifExistInFuseki = false;
		}

		return ifExistInFuseki;
	}

	@Override
	public List<String> QueryTheTextOFThisWord(String yourWord,
			String yourGrade, String themeOfThisWord) {
		// �ö�̬���鱣����꼶����ID
		List<String> allIdOfThisGrade = QueryAllWordsIdOfThisGrade(yourGrade);
		// �ö�̬���鱣����꼶ͬ������������
		List<String> twoWordsWithSameGradeAndSameThemeList = new ArrayList<String>();

		// ����allIdOfThisGrade�Ҹ����������ID��֮�������ȡ2��
		List<String> wordsOfThisGradeAndThisTheme = new ArrayList<String>();
		if (allIdOfThisGrade.isEmpty()) {
			System.out.println("���꼶û�е��ʣ�");
		} else {// ���꼶���е���
			System.out.println("���꼶���е��ʵĸ�����" + allIdOfThisGrade.size());

			for (int i = 0; i < allIdOfThisGrade.size(); i++) {

				ResultSet thisWordResultSet = queryWithManyWays
						.checkPropertyDependOnId(allIdOfThisGrade.get(i)
								.toString());
				// �ж��Ƿ���ͬһ������
				if (thisWordResultSet.hasNext()) {
					while (thisWordResultSet.hasNext()) {
						QuerySolution thisWordSolution = thisWordResultSet
								.next();
						String topicOfThisWord = substringManage(thisWordSolution
								.get("?propertyTopic").toString());
						String functionOfThisWord = substringManage(thisWordSolution
								.get("?propertyFunction").toString());
						if (themeOfThisWord.equals(topicOfThisWord)) {
							wordsOfThisGradeAndThisTheme
									.add(subStringManage(thisWordSolution.get(
											"?instanceLabel").toString()));
						} else if (themeOfThisWord.equals(functionOfThisWord)) {
							wordsOfThisGradeAndThisTheme
									.add(subStringManage(thisWordSolution.get(
											"?instanceLabel").toString()));
						} else {
							// ���������ⲻ��ͬһ���ݣ���ʲô������
						}
					}
				}
			}

			// �����ȡ2������
			do {
				Random ra = new Random();
				int randomNum = ra
						.nextInt(wordsOfThisGradeAndThisTheme.size() - 1);
				System.out.println("�������Ϊ��" + randomNum);
				String randomWord = wordsOfThisGradeAndThisTheme.get(randomNum)
						.toString();
				System.out.println("�������Ϊ��" + randomWord);

				List<String> randomWordsList = new ArrayList<String>();
				int j;
				// �������ID�����е�ÿһ��Ԫ��
				for (j = 0; j < randomWordsList.size(); j++) {
					// �ж��Ƿ����ظ���
					if (randomWordsList.get(j).equals(yourWord)) {
						break;
					}
					if (randomWordsList.get(j).equals(randomWord)) {
						break;
					}
				}
				if (j == randomWordsList.size()) {// ���ظ�
					// �����ID�����
					randomWordsList.add(randomWord);
					twoWordsWithSameGradeAndSameThemeList.add(randomWord);
				} else {
					continue;
				}

			} while (twoWordsWithSameGradeAndSameThemeList.size() < 2);
		}

		return twoWordsWithSameGradeAndSameThemeList;
	}

	@Override
	public List<ResultSet> QueryAllWordsOfAUnit(String yourVersion,
			String yourBook, String yourUnit) {
		// 1.���ݰ汾���Ҹð汾���е���
		ResultSet allWordsOfAUnitResultSet = queryWithManyWays
				.checkAllWordsOfAVersion(yourVersion);

		// 2.����propertyVersion�ҵ���2λ���������͵�3λ����Ԫ����������������͵�Ԫ���жԱȣ����Աȳɹ��򱣴�ID
		// ��List<String>������Ӧ��ID
		List<String> allIDOfAUnitList = new ArrayList<String>();

		if (allWordsOfAUnitResultSet.hasNext()) {
			while (allWordsOfAUnitResultSet.hasNext()) {
				QuerySolution allWordsOfAUnitSolution = allWordsOfAUnitResultSet
						.next();
				// ��ȡ��ID
				String ID = substringManage4(allWordsOfAUnitSolution.get(
						"?propertyVersion").toString());
				// ��ȡ����2λ���������͵�3λ����Ԫ��
				String stringExcept1 = substringManage5(ID);
				String book = substringManage6(stringExcept1);
				String stringExcept2 = substringManage5(stringExcept1);
				String unit = substringManage6(stringExcept2);

				if (book.equals(yourBook)) {
					if (unit.equals(yourUnit)) {
						// ����Ҫ�Ĳ����͵�Ԫ���򱣴��ID
						allIDOfAUnitList.add(ID);
					} else {
						// ʲô������
					}
				} else {
					// ʲô������
				}
			}
		} else {
			System.out.println("�ð汾û�е���");
		}

		// 3.����ID�Ҹõ��ʵ���������
		// ��List<ResultSet>�������Ľ����
		List<ResultSet> resultOfAllWordsOfAUnit = new ArrayList<ResultSet>();
		for (int i = 0; i < allIDOfAUnitList.size(); i++) {
			resultOfAllWordsOfAUnit.add(queryWithManyWays
					.checkPropertyDependOnId(allIDOfAUnitList.get(i)));
		}

		return resultOfAllWordsOfAUnit;
	}
	@Override
	public void WriteBackToOwl() throws IOException {

		writeOwl.writeBackToOwl();

	}
	@Override
	public void WriteBackToRespectiveOwl() throws IOException {

		/*writeOwl.writeBackToRespectiveOwl();*/

	}
	@Override
	public void InsertRelationSameAs(InputStream yourPath)
			throws BiffException, IOException {
		List<String[]> list = readExcel(yourPath);

		String[] str = null;
		for (int i = 0; i < list.size(); i++) {
			System.out.println("i = " + i);
			str = (String[]) list.get(i);
			for (int j = 0; j < str.length; j++) {
				System.out.println("Ҫ��ӵ�Excel�е�����" + str[j]);
			}

			String question1 = null;
			question1 = str[0];

			String question2 = null;
			question2 = str[1];
			// �á�_�����桰 ��
			question1 = question1.replace(" ", "_");
			question2 = question2.replace(" ", "_");
			System.out.println("question1:" + question1);
			System.out.println("question2:" + question2);

			// �ж�question2�Ƿ������ݿ���
			boolean ifInDB = queryWithManyWays.checkIfInDB(question2);
			String ID = null;
			if (ifInDB == false) {
				// ��ȡquestion2����(�ɵ������ĻԽӿ��Ҹ���)�����������Լ��ı�����
				String[] sentenceAllClass = { "Where", "What", "How", "When",
						"Which", "Would", "Shall", "Have", "Why", "Whose",
						"Is", "May", "Could", "Can", "Did", "Will", "Do",
						"Dose", "Was", "Are" };
				String[] sentenceAllClass2 = { "where", "what", "how", "when",
						"which", "would", "shall", "have", "why", "whose",
						"Is", "may", "could", "can", "did", "will", "do",
						"dose", "was", "are" };
				String question2Class = null;
				int j = 0;
				for (j = 0; j < sentenceAllClass.length; j++) {
					if (question2.contains(sentenceAllClass[j])) {
						question2Class = sentenceAllClass[j];
						System.out.println("question2Class: " + question2Class);
						break;
					} else if (question2.contains(sentenceAllClass2[j])) {
						question2Class = sentenceAllClass[j];
						System.out.println("question2Class: " + question2Class);
						break;
					}
				}
				if (j >= sentenceAllClass.length) {
					System.out.println("û���ҵ��þ��ӵĸ���");
				}

				// ������д���磺What's
				// if (question2Class.contains("'")) {
				// question2Class = question2Class.substring(0,
				// question2Class.indexOf("'"));
				// }

				// ���question2����
				addIndividualAndProperty.addSentenceClass(question2Class,
						question2);

				// ��ӱ�ǩLabel
				addIndividualAndProperty.addSentenceLabel(question2);

				// ��Ӿ���ID
				System.out.println("���ӵİ汾");
				// Scanner sc = new Scanner(System.in);
				// String sentenceVersion = sc.nextLine();
				String sentenceVersion = str[2];
				System.out.println("���ӵĲ�����1-12��");
				// sc = new Scanner(System.in);
				// String sentenceBook = sc.nextLine();
				String sentenceBook = str[3];
				System.out.println("���ӵĵ�Ԫ��");
				// sc = new Scanner(System.in);
				// String sentenceUnit = sc.nextLine();
				String sentenceUnit = str[4];
				ID = sentenceVersion + "/" + sentenceBook + "/" + sentenceUnit
						+ "/";

				int countID = 1;
				// ���ҳ�������Ԫ��
				ResultSet resultAllTriples = queryWithManyWays.checkAllTriple();
				ResultSet resultInstanceAllProperty = null;
				QuerySolution solutionAllTriples = null;
				if (resultAllTriples.hasNext()) {
					while (resultAllTriples.hasNext()) {
						// QuerySolution next()
						// Moves onto the next result.
						// �ƶ����¸�result��
						solutionAllTriples = resultAllTriples.next();
						if (solutionAllTriples.get("?s").toString()
								.contains("'")) {
							resultInstanceAllProperty = queryWithManyWays
									.checkAllID(
											solutionAllTriples
													.get("?s")
													.toString()
													.substring(
															solutionAllTriples
																	.get("?s")
																	.toString()
																	.indexOf(
																			"'") + 1,
															solutionAllTriples
																	.get("?s")
																	.toString()
																	.lastIndexOf(
																			"'")),
											"85");// ���Ҹ�ʵ��������ID
							if (resultInstanceAllProperty.hasNext()) {// ���ͬ�汾��ͬ������ͬ��Ԫ�ĵ��ʸ���������ID
								while (resultInstanceAllProperty.hasNext()) {
									QuerySolution solution = resultInstanceAllProperty
											.next();
									if (solution.get("?allID").toString()
											.contains(ID)) {// ��ͬ�汾��ͬ������ͬ��Ԫ
										countID++;// ��countID��1
									} else {
										continue;
									}
								}
							} else {
								System.out
										.println("��ͬ�汾��ͬ������ͬ��Ԫ�ĵ��ʣ�countID���� = 1");
							}
							System.out.println("countID: " + countID);
						} else {
							continue;
						}

					}
				} else {
					System.out.println("����Ԫ��");
				}

				System.out.println("����countID: " + countID);
				ID = ID + String.valueOf(countID);
				// ���ID
				addIndividualAndProperty.addSentencePropertyForModify("����ID",
						question2, ID, ID);

			} else {
				// ʲô������
			}

			// ��ӵȼ۹�ϵ
			addIndividualAndProperty.addRelationSameAs(question1, question2);
			// addIndividualAndProperty.addRelationSameAs(question2, question1);
		}
		System.out.println("��������ȼ۹�ϵ�ɹ���");

	}

	@Override
	public ArrayList<String> ReasonSameAs(String yourSentence) {

		yourSentence = yourSentence.replace(" ", "_");

		InfModel inf = myReasoner.ReasonSameAs();

		// ͨ����ǩLabel��URI
		ResultSet result = queryWithManyWays.checkOnlyInstanceURI(yourSentence);

		System.out.println(yourSentence + " * * =>\n");
		// Iterator list = null;
		StmtIterator list = null;
		if (result.hasNext()) {
			while (result.hasNext()) {
				// QuerySolution next()
				// Moves onto the next result.
				// �ƶ����¸�result��
				QuerySolution solution = result.next();
				Resource yourSentenceSubject = solution.get("?instance")
						.asResource();
				// listStatements(Resource subject, Property predicate, RDFNode
				// object, Model posit)
				// Find all the statements matching a pattern.
				list = inf.listStatements(yourSentenceSubject, null,
						(RDFNode) null);
			}
		}

		ArrayList<String> allString = new ArrayList<String>();
		int index = 0;
		// while (list.hasNext()) {
		// String[] listStringArray = list.next().toString().split(",");
		//
		// for (String ss : listStringArray) {
		// // System.out.println(ss);
		// allString.add(ss);
		// index++;
		// }
		// }

		while (list.hasNext()) {
			String[] listStringArray = list.next().toString().split("]");

			for (String ss : listStringArray) {// �ֽ��ÿһ����Ԫ��
				// System.out.println(ss);

				String str1 = ss.substring(0, ss.indexOf(","));
				System.out.println("str1��������" + str1);
				allString.add(str1);
				index++;

				String str2 = ss.substring(ss.indexOf(",") + 1, ss.length());
				// System.out.println("str2��������" + str2);

				String str3 = str2.substring(0, str2.indexOf(","));
				System.out.println("str3��������" + str3);
				allString.add(str3);
				index++;

				if (str2.substring((str2.indexOf(",") + 1)).contains("\"")) {
					String str4 = str2.substring(str2.indexOf("\"") + 1,
							str2.lastIndexOf("\""));
					if (str4.contains(")")) {
						str4 = str4.substring(str4.indexOf(")") + 1);
					}
					System.out.println("str4��������" + str4 + "\n");
					allString.add(str4);
					index++;
				} else if (str2.substring((str2.indexOf(",") + 1))
						.contains("'")) {
					String str4 = str2.substring(str2.indexOf("\'") + 1,
							str2.lastIndexOf("\'"));
					System.out.println("str4��������" + str4 + "\n");
					allString.add(str4);
					index++;
				} else {
					String str4 = str2.substring(str2.indexOf(",") + 1);
					if (str4.contains(",")) {
						str4 = str2.substring(str2.indexOf(",") + 1,
								str2.indexOf(","));
					}
					System.out.println("str4��������" + str4 + "\n");
					allString.add(str4);
					index++;
				}
			}
		}

		ArrayList<String> returnString = new ArrayList<String>();
		for (int i = 0; i < index; i++) {
			String everyString = allString.subList(i, i + 1).toString();
			if (i % 3 == 1) {

				// ɾ����һ���ո�
				if (everyString.contains("]")) {
					everyString = everyString.substring(
							everyString.indexOf(" ") + 1,
							everyString.indexOf("]"));
				}
				if (everyString.equals(RDFS.label.toString())) {
					returnString.add("����Label");
				} else if (everyString.equals(RDF.type.toString())) {
					returnString.add("���⸸��");

					everyString = allString.subList(i + 1, i + 2).toString();
					everyString = everyString.substring(
							everyString.indexOf(" ") + 1,
							everyString.indexOf("]"));
					everyString = QueryLabelAndReturn(everyString);
					returnString.add(everyString);
					i++;

				} else if (everyString.equals(OWL.sameAs.toString())) {
					returnString.add("�ȼ�");

					everyString = allString.subList(i + 1, i + 2).toString();
					everyString = everyString.substring(
							everyString.indexOf("[") + 1,
							everyString.indexOf("]"));
					returnString.add(everyString);
					i++;
				} else {
					everyString = QueryLabelAndReturn(everyString);
					returnString.add(everyString);
				}

			} else if (i % 3 == 2) {
				everyString = everyString.substring(
						everyString.indexOf("[") + 1, everyString.indexOf("]"));
				returnString.add(everyString);
			}

		}
		return returnString;
	}

	// ���ݵ��ʲ鿴����ͬ�����ʼ�������
	@Override
	public List<ResultSet> QueryBrotherIndividual(String yourTheme) {
		// // ���Ҹõ��ʵĵ���������ֵ
		// String[] theme = { "?propertyTopic", "?propertyFunction"};
		// ResultSet resultsTopicValue = queryWithManyWays
		// .checkTopicValue(yourWord);
		// String yourThemeValue = null;
		// String yourThemeValueFlag1 = null;
		// String yourThemeValueFlag2 = null;
		// String themeSPARQL = null;
		// if (resultsTopicValue.hasNext()) {
		// while (resultsTopicValue.hasNext()) {
		// // QuerySolution next()
		// // Moves onto the next result.
		// // �ƶ����¸�result��
		// QuerySolution solutionPropertyValue = resultsTopicValue.next();
		// for (int i = 0; i < theme.length; i++) {
		// yourThemeValue = solutionPropertyValue.get(theme[i])
		// .toString();
		// if (yourThemeValue.contains("��")) {
		// continue;
		// } else {
		// yourThemeValueFlag1 = substringManage3(yourThemeValue);
		// yourThemeValueFlag2 = substringManage2(yourThemeValue);
		// themeSPARQL = theme[i];
		// }
		// }
		// System.out.println(yourWord + "�������ǣ� " + yourThemeValue);
		// }
		// } else {
		// System.out.println("�õ���������");
		// }

		String yourThemeValueFlag1 = null;
		String yourThemeValueFlag2 = null;
		ResultSet resultsAllBrotherID = null;
		if (yourTheme.contains("-")) {
			yourThemeValueFlag1 = substringManage2(yourTheme);
			yourThemeValueFlag2 = substringManage3(yourTheme);

			// �����������Ա�ǣ��ҳ����а����ñ�ǵ�����ֵ��������ֵ�а�������ID
			resultsAllBrotherID = queryWithManyWays.checkBrotherID(
					yourThemeValueFlag1, yourThemeValueFlag2);
		} else {
			// �����������Ա�ǣ��ҳ����а����ñ�ǵ�����ֵ��������ֵ�а�������ID���ǿα궨�����⣩
			resultsAllBrotherID = queryWithManyWays.checkBrotherID2(yourTheme);
		}

		List<ResultSet> brotherAllResultSet = new ArrayList<ResultSet>();
		if (resultsAllBrotherID.hasNext()) {
			while (resultsAllBrotherID.hasNext()) {
				QuerySolution solutionBrotherID = resultsAllBrotherID.next();
				if (solutionBrotherID.get("?propertyTheme").toString()
						.contains("|")) {
					// ������ʲô������
				} else {
					String brotherTheme = solutionBrotherID.get(
							"?propertyTheme").toString();

					// ��ȡID
					String brotherID = substringManage4(brotherTheme);

					// ����ÿ������ID��Ӧ����������
					ResultSet resultsBrother = queryWithManyWays
							.checkPropertyDependOnId(brotherID);
					brotherAllResultSet.add(resultsBrother);
				}

			}
		} else {
			System.out.println("�������޵���");
		}

		return brotherAllResultSet;
	}

	// ��̬����-------------------------------------------------------------------------------------------------------------------------------------
	// �����ַ�����ȡ@֮ǰ���ַ�
	private static String subStringManage(String string) {
		string = string.substring(0, string.indexOf("@"));
		return string;
	}

	// �����ַ�������ȡ��)���͡�@��֮����ַ���
	private static String substringManage(String string) {
		String newString = string.substring(string.indexOf(")") + 1,
				string.lastIndexOf("@"));
		return newString;
	}

	// �����ַ�������ȡ��������ֵ�С�.���͡�-��֮����ַ���
	private static String substringManage2(String string) {
		String newString = string.substring(string.indexOf(".") + 1,
				string.lastIndexOf("-"));
		return newString;
	}

	// �����ַ�������ȡ��������ֵ�С�)��֮�󣬻��ߡ�)���͡�-��֮����ַ���
	private static String substringManage3(String string) {
		String newString = string.substring(string.indexOf(")") + 1,
				string.length());
		if (newString.contains("-")) {
			newString = newString.substring(0, newString.indexOf("-"));
		}
		return newString;
	}

	// �����ַ�������ȡ��������ֵ�е�һ����(���͵�һ����)��֮����ַ���
	private static String substringManage4(String string) {
		String newString = string.substring(string.indexOf("(") + 1,
				string.indexOf(")"));
		return newString;
	}

	// �����ַ�������ȡ��һ����/���������ַ���
	private static String substringManage5(String string) {
		String newString = string.substring(string.indexOf("/") + 1,
				string.length());
		return newString;
	}

	// �����ַ�������ȡ�ӿ�ʼ����һ����/�����ַ���
	private static String substringManage6(String string) {
		String newString = string.substring(0, string.indexOf("/"));
		return newString;
	}

	// �����ַ�������ȡ�ӵ�һ����/���������ַ���
	private static String substringManage7(String string) {
		String newString = string.substring(string.indexOf("/") + 1,
				string.length());
		return newString;
	}

	private static void Init() {
		try {
			read();
		} catch (FileNotFoundException ex) {

		} catch (IOException ex) {

		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void read() throws FileNotFoundException, IOException {
		ALLWORDS = new HashMap<String, Vector>();
		ALLMEANS = new HashMap<String, Vector>();
		BufferedReader in = new BufferedReader(new FileReader(new File(
				"Data/HowNet.txt")));
		String temp = in.readLine();
		while (temp != null) {
			Vector<String> DEFS;
			Vector<String> W_C;

			// ��ȡһ��No��txt����
			temp = in.readLine();
			String W_c = temp.substring(4);// ��ȡ����
			temp = in.readLine();
			temp = in.readLine();
			temp = in.readLine();
			temp = in.readLine();
			String W_e = temp.substring(temp.indexOf("=") + 1);// ��ȡӢ��
			temp = in.readLine();
			temp = in.readLine();
			temp = in.readLine();
			temp = in.readLine();
			String DEF = temp.substring(temp.indexOf("=") + 1);// ��ȡDEF

			//
			if (ALLWORDS.containsKey(W_e) && ALLMEANS.containsKey(W_e)) {
				W_C = ALLWORDS.get(W_e);
				DEFS = ALLMEANS.get(W_e);
			} else {
				W_C = new Vector<String>();
				DEFS = new Vector<String>();
			}

			/* �ж�֮ǰ�Ƿ���ֹ�ͬ����W_C��DEF */
			Iterator<String> It_1 = W_C.iterator();
			boolean judge_1 = false;
			while (It_1.hasNext()) {
				String m = It_1.next();
				if (m.equals(W_c)) {
					judge_1 = true;
					break;
				}
			}
			/* �ж�֮ǰ�Ƿ���ֹ�ͬ����DEF */
			Iterator<String> It_2 = DEFS.iterator();
			boolean judge_2 = false;
			while (It_2.hasNext()) {
				String m = It_2.next();
				if (m.equals(DEF)) {
					judge_2 = true;
					break;
				}
			}
			if (!judge_1) {
				W_C.add(W_c);
			}
			if (!judge_2) {
				DEFS.add(DEF);
			}
			ALLWORDS.put(W_e, W_C);
			ALLMEANS.put(W_e, DEFS);
			temp = in.readLine();
			temp = in.readLine();
		}
		in.close();
		System.out.println("HowNet Load Succeed!");
		// findDEF("man");
	}

	// ����Hownet�еĸ���
	private static String findDEF(String W_e) {
		if (W_e.contains("@")) {
			W_e = subStringManage(W_e);
		}
		String yourClass = null;
		@SuppressWarnings("unchecked")
		Vector<String> DEFS = ALLMEANS.get(W_e);
		ArrayList<String> result = new ArrayList<String>();
		if (DEFS == null || DEFS.size() <= 0) {
			System.out.println(W_e + "�ĸ��಻��HowNet��");
		} else {
			for (int i = 0; i < DEFS.size(); i++) {
				// System.out.println(m.get(i)[1]);
				result.add(DEFS.get(i));
			}
			System.out.println("��ѡ������Ҫ�ĸ��ࣨ������������");
			int num = 0;
			for (String temp : result) {
				num++;
				System.out.println(num + temp);
			}

			Scanner sc = new Scanner(System.in);
			int yourClassNum = 0;
			yourClassNum = sc.nextInt();
			num = 0;
			for (String temp : result) {
				num++;
				if (num == yourClassNum) {
					System.out.println(num + temp);
					yourClass = temp;
				}
			}
			int index = 0;
			index = yourClass.indexOf(":");
			System.out.println("index: " + index);
			if (index == -1) {
				yourClass = yourClass.substring(1, yourClass.length() - 1);
			} else {
				yourClass = yourClass.substring(1, index);
			}
			System.out.println("��ѡ��ĸ��ࣺ" + yourClass);
		}
		return yourClass;
	}

	// �޸ĵ���ID
	private static void modifyID(QuerySolution solution,
			String yourRelationProperty, String yourSPARQLProperty,
			String yourPropertyLabel) {
		// ɾ������Ԫ��
		deleteIndividual.deleteInstanceProperty(solution.get("?instanceLabel")
				.toString(), solution.get(yourRelationProperty).toString(),
				solution.get(yourSPARQLProperty).toString());

		// ����µ���Ԫ��
		Scanner sc = new Scanner(System.in);
		System.out.println("�����������޸ĵ�����ֵ:");
		String yourModifyValue = sc.nextLine();
		String yourID = yourModifyValue;
		addIndividualAndProperty.addPropertyForModify(yourPropertyLabel,
				solution.get("?instanceLabel").toString(), yourModifyValue,
				yourID);

		for (int i = 0; i < propertyRelation.length; i++) {
			if (propertyLabel[i].equals("����")
					|| propertyLabel[i].equals("Hownet�еĸ���")
					|| propertyLabel[i].equals("����ID")) {
				// ʲô������
			} else {

				// ����֮ǰ������ֵ
				String prePropertyValue = solution.get(propertySPARQLValue[i])
						.toString();
				System.out.println("prePropertyValue:" + prePropertyValue);
				String newPropertyValue = prePropertyValue
						.substring(prePropertyValue.indexOf(")") + 1);
				System.out.println("newPropertyValue:" + newPropertyValue);

				// ɾ������Ԫ��
				deleteIndividual.deleteInstanceProperty(
						solution.get("?instanceLabel").toString(), solution
								.get(propertyRelation[i]).toString(), solution
								.get(propertySPARQLValue[i]).toString());

				// ����µ���Ԫ��
				addIndividualAndProperty.addPropertyForModify(propertyLabel[i],
						solution.get("?instanceLabel").toString(),
						newPropertyValue, yourID);

			}
		}
	}

	// �޸ĵ��ʸ���
	private static void modifyClass(QuerySolution solution,
			String yourSPARQLProperty) {
		Init();
		// ɾ������Ԫ��
		deleteIndividual.deleteClass(solution.get("?instanceLabel").toString(),
				solution.get(yourSPARQLProperty).toString());

		// ����µ���Ԫ��
		System.out.println("��ѡ�������޸ĵĸ���:");
		// ����Ҫ��ӵ�ʵ����Hownet����
		String yourClass = findDEF(solution.get("?instanceLabel").toString());

		addIndividualAndProperty.addClass(yourClass,
				solution.get("?instanceLabel").toString());
	}

	// �޸ĵ���Label
	private static void modifyLabel(QuerySolution solution, String yourID) {
		// �޸�Label
		// ɾ������Ԫ��
		deleteIndividual.deleteLabel(solution.get("?instanceLabel").toString());
		// ����µ���Ԫ��
		Scanner sc = new Scanner(System.in);
		System.out.println("�����������޸ĵ�����ֵ:");
		String yourModifyValue = sc.nextLine();
		addIndividualAndProperty.addLabel(yourModifyValue);

		// �޸���
		// ɾ������Ԫ��
		deleteIndividual.deleteClass(solution.get("?instanceLabel").toString(),
				solution.get("?propertyClass").toString());
		// ����µ���Ԫ��
		addIndividualAndProperty.addClass(solution.get("?propertyClass")
				.toString(), yourModifyValue);

		for (int i = 0; i < propertyRelation.length; i++) {
			if (propertyLabel[i].equals("����")
					|| propertyLabel[i].equals("Hownet�еĸ���")) {
				// ʲô������
			} else {

				// ɾ����Ӧ���Ե���Ԫ��
				deleteIndividual.deleteInstanceProperty(
						solution.get("?instanceLabel").toString(), solution
								.get(propertyRelation[i]).toString(), solution
								.get(propertySPARQLValue[i]).toString());

				// �����Ӧ���Ե���Ԫ��
				addIndividualAndProperty.addPropertyForModify(propertyLabel[i],
						yourModifyValue, solution.get(propertySPARQLValue[i])
								.toString(), yourID);

			}
		}
	}

	// �޸ĵ�������
	private static void modifyPropertyValue(QuerySolution solution,
			String yourRelationProperty, String yourSPARQLProperty,
			String yourPropertyLabel, String yourID) {
		// ɾ������Ԫ��
		deleteIndividual.deleteInstanceProperty(solution.get("?instanceLabel")
				.toString(), solution.get(yourRelationProperty).toString(),
				solution.get(yourSPARQLProperty).toString());

		// ����µ���Ԫ��
		Scanner sc = new Scanner(System.in);
		System.out.println("�����������޸ĵĵ�������:");
		String yourModifyValue = sc.nextLine();
		addIndividualAndProperty.addPropertyForModify(yourPropertyLabel,
				solution.get("?instanceLabel").toString(), yourModifyValue,
				yourID);
	}

	// �޸ľ���ID
	private static void modifySentenceID(QuerySolution solution,
			String yourRelationProperty, String yourSPARQLProperty,
			String yourPropertyLabel) {
		// ɾ������Ԫ��
		deleteIndividual.deleteSentenceInstanceProperty(
				solution.get("?instanceLabel").toString(),
				solution.get(yourRelationProperty).toString(),
				solution.get(yourSPARQLProperty).toString());

		// ����µ���Ԫ��
		Scanner sc = new Scanner(System.in);
		System.out.println("�����������޸ĵ�����ֵ:");
		String yourModifyValue = sc.nextLine();
		String yourID = yourModifyValue;
		addIndividualAndProperty.addSentencePropertyForModify(
				yourPropertyLabel, solution.get("?instanceLabel").toString(),
				yourModifyValue, yourID);

		for (int i = 0; i < sentencePropertyRelation.length; i++) {
			if (sentencePropertyLabel[i].equals("����")
					|| sentencePropertyLabel[i].equals("�������")
					|| sentencePropertyLabel[i].equals("����ID")) {
				// ʲô������
			} else {

				// ����֮ǰ������ֵ
				String prePropertyValue = solution.get(
						sentencePropertySPARQLValue[i]).toString();
				System.out.println("prePropertyValue:" + prePropertyValue);
				String newPropertyValue = prePropertyValue
						.substring(prePropertyValue.indexOf(")") + 1);
				System.out.println("newPropertyValue:" + newPropertyValue);

				// ɾ������Ԫ��
				deleteIndividual
						.deleteSentenceInstanceProperty(
								solution.get("?instanceLabel").toString(),
								solution.get(sentencePropertyRelation[i])
										.toString(),
								solution.get(sentencePropertySPARQLValue[i])
										.toString());

				// ����µ���Ԫ��
				addIndividualAndProperty.addSentencePropertyForModify(
						sentencePropertyLabel[i], solution
								.get("?instanceLabel").toString(),
						newPropertyValue, yourID);

			}
		}
	}

	// �޸ľ��Ӹ���
	private static void modifySentenceClass(QuerySolution solution,
			String yourSPARQLProperty) {
		// ɾ������Ԫ��
		deleteIndividual.deleteSentenceClass(solution.get("?instanceLabel")
				.toString(), solution.get(yourSPARQLProperty).toString());

		// ����µ���Ԫ��
		System.out.println("��ѡ�������޸ĵ��������:");
		// ����Ҫ��ӵ�ʵ����Hownet����
		String yourClass = solution
				.get("?instanceLabel")
				.toString()
				.substring(0,
						solution.get("?instanceLabel").toString().indexOf("_"));
		// ������д���磺What's
		if (yourClass.contains("'")) {
			yourClass = yourClass.substring(0, yourClass.indexOf("'"));
		}

		addIndividualAndProperty.addSentenceClass(yourClass,
				solution.get("?instanceLabel").toString());
	}

	// �޸ľ���Label
	private static void modifySentenceLabel(QuerySolution solution,
			String yourID) {
		// �޸�Label
		// ɾ������Ԫ��
		deleteIndividual.deleteSentenceLabel(solution.get("?instanceLabel")
				.toString());
		// ����µ���Ԫ��
		Scanner sc = new Scanner(System.in);
		System.out.println("�����������޸ĵ�����:");
		String yourModifyValue = sc.nextLine();
		addIndividualAndProperty.addSentenceLabel(yourModifyValue);

		// �޸���
		// ɾ������Ԫ��
		deleteIndividual.deleteSentenceClass(solution.get("?instanceLabel")
				.toString(), solution.get("?propertyClass").toString());
		// ����µ���Ԫ��
		addIndividualAndProperty.addSentenceClass(solution
				.get("?propertyClass").toString(), yourModifyValue);

		for (int i = 0; i < sentencePropertyRelation.length; i++) {
			if (sentencePropertyLabel[i].equals("����")
					|| sentencePropertyLabel[i].equals("�������")) {
				// ʲô������
			} else {

				// ɾ����Ӧ���Ե���Ԫ��
				deleteIndividual
						.deleteSentenceInstanceProperty(
								solution.get("?instanceLabel").toString(),
								solution.get(sentencePropertyRelation[i])
										.toString(),
								solution.get(sentencePropertySPARQLValue[i])
										.toString());

				// �����Ӧ���Ե���Ԫ��
				addIndividualAndProperty
						.addSentencePropertyForModify(sentencePropertyLabel[i],
								yourModifyValue,
								solution.get(sentencePropertySPARQLValue[i])
										.toString(), yourID);

			}
		}
	}

	// �޸ľ�������
	private static void modifySentencePropertyValue(QuerySolution solution,
			String yourRelationProperty, String yourSPARQLProperty,
			String yourPropertyLabel, String yourID) {
		// ɾ������Ԫ��
		deleteIndividual.deleteSentenceInstanceProperty(
				solution.get("?instanceLabel").toString(),
				solution.get(yourRelationProperty).toString(),
				solution.get(yourSPARQLProperty).toString());

		// ����µ���Ԫ��
		Scanner sc = new Scanner(System.in);
		System.out.println("�����������޸ĵľ�������:");
		String yourModifyValue = sc.nextLine();
		addIndividualAndProperty.addSentencePropertyForModify(
				yourPropertyLabel, solution.get("?instanceLabel").toString(),
				yourModifyValue, yourID);
	}

	// ��ȡExcel�������ַ�������
	private static List<String[]> readExcel(InputStream yourPath)
			throws BiffException, IOException {
		// ����һ��list �����洢��ȡ������
		List<String[]> list = new ArrayList<String[]>();
		Workbook rwb = null;
		Cell cell = null;
		// ��ȡExcel�ļ�����
		rwb = Workbook.getWorkbook(yourPath);

		// ��ȡ�ļ���ָ�������� Ĭ�ϵĵ�һ��
		Sheet sheet = rwb.getSheet(0);

		// ����(��ͷ��Ŀ¼����Ҫ����1��ʼ)
		for (int i = 0; i < sheet.getRows(); i++) {

			// ����һ������ �����洢ÿһ�е�ֵ
			String[] str = new String[sheet.getColumns()];

			// ����
			for (int j = 0; j < sheet.getColumns(); j++) {

				// ��ȡ��i�У���j�е�ֵ
				cell = sheet.getCell(j, i);
				str[j] = cell.getContents();

			}
			// �Ѹջ�ȡ���д���list
			list.add(str);
		}
		return list;
	}

	// ��ѯLabel������������������Label
	private static String QueryLabelAndReturn(String URI) {
		ResultSet resultPropertyLabel = queryWithManyWays
				.checkOnlyPropertyLabel(URI);
		String label = null;
		if (resultPropertyLabel.hasNext()) {
			while (resultPropertyLabel.hasNext()) {

				QuerySolution solutionPropertyLabel = resultPropertyLabel
						.next();
				label = solutionPropertyLabel.get("propertyLabel").toString();

				if (label.contains("@")) {
					label = label.substring(0, label.indexOf("@"));
				}
			}
		}
		return label;
	}

	// ��ѯĳ�꼶���е���ID
	private static List<String> QueryAllWordsIdOfThisGrade(String yourGrade) {
		// �����꼶�������
		int yourGradeInt = Integer.parseInt(yourGrade);
		yourGradeInt = yourGradeInt * 2;

		// �������е��ʵ�ID
		ResultSet resultsIdOfAllWords = queryWithManyWays.checkIdOfAllWords();

		// �ö�̬���鱣����꼶����ID
		List<String> allIdOfThisGrade = new ArrayList<String>();

		// ɸѡ���꼶��ID
		if (resultsIdOfAllWords.hasNext()) {
			while (resultsIdOfAllWords.hasNext()) {
				// QuerySolution next()
				// Moves onto the next result.
				// �ƶ����¸�result��
				QuerySolution solutionIdOfAllWords = resultsIdOfAllWords.next();
				String propertyId = solutionIdOfAllWords.get("?propertyID")
						.toString();

				// ��ȡ������֮ǰ����Ϣ
				String propertyBook = interceptBook(propertyId);
				if (String.valueOf(yourGradeInt).equals(propertyBook)
						|| String.valueOf(yourGradeInt - 1)
								.equals(propertyBook)) {
					allIdOfThisGrade.add(solutionIdOfAllWords
							.get("?propertyID").toString());
				}
			}
		}
		return allIdOfThisGrade;
	}

	// ��ѯĳ�꼶���е���ID
	private static List<String> QueryAllSentencesIdOfThisGrade(String yourGrade) {
		// �������о��ӵ�ID
		ResultSet resultsIDOfAllWords = queryWithManyWays
				.checkIdOfAllSentences();

		// �ö�̬���鱣����꼶����ID
		List<String> allSentencesIdOfThisGrade = new ArrayList<String>();

		// ɸѡ���꼶��ID
		if (resultsIDOfAllWords.hasNext()) {
			while (resultsIDOfAllWords.hasNext()) {
				// QuerySolution next()
				// Moves onto the next result.
				// �ƶ����¸�result��
				QuerySolution solutionIdOfAllWords = resultsIDOfAllWords.next();
				String propertyId = solutionIdOfAllWords.get("?propertyID")
						.toString();
				// ��ȡ������֮ǰ����Ϣ
				String stringOutOfVersion = propertyId.substring(propertyId
						.indexOf("/") + 1);
				String propertyBook = stringOutOfVersion.substring(0,
						stringOutOfVersion.indexOf("/"));
				// System.out.println("�õ��ʵĲ�����" + propertyBook);
				if (yourGrade.equals(propertyBook)) {
					allSentencesIdOfThisGrade.add(solutionIdOfAllWords.get(
							"?propertyID").toString());
				}
			}
		} else {
			System.out.println("��ID����Ӧ�κ���Ԫ��");
		}

		return allSentencesIdOfThisGrade;
	}

	// ͨ��ID��ȡ��������Ϣ
	private static String interceptBook(String yourId) {
		// ��ȡ������֮ǰ����Ϣ
		String stringOutOfVersion = yourId.substring(yourId.indexOf("/") + 1);
		String propertyBook = stringOutOfVersion.substring(0,
				stringOutOfVersion.indexOf("/"));
		// System.out.println("�õ��ʵĲ�����" + propertyBook);

		return propertyBook;
	}

	// ���ݲ�������������꼶
	private static String bookToGrade(String yourBook) {

		int bookOfThisWordInt = Integer.valueOf(yourBook);
		int gradeOfThisWord = (int) Math.ceil((float) bookOfThisWordInt / 2);
		String yourBookString = String.valueOf(gradeOfThisWord);

		return yourBookString;
	}

	// �����꼶�������
	private static List<String> gradeToBook(String yourGrade) {
		List<String> book = new ArrayList<String>();
		int gradeOfThisWordInt = Integer.valueOf(yourGrade);
		for (int i = 0; i < 2; i++) {
			int bookOfThisWord = gradeOfThisWordInt * 2 - i;
			book.add(String.valueOf(bookOfThisWord));
		}
		return book;
	}

}
