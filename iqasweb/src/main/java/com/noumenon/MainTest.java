package com.noumenon;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Vector;

import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.noumenon.OntologyManage.OntologyManage;
import com.noumenon.OntologyManage.Impl.OntologyManageImpl;

import jxl.read.biff.BiffException;
public class MainTest {

	public static HashMap<String, Vector> ALLWORDS;
	public static HashMap<String, Vector> ALLMEANS;

	private static String[] propertyLabel = new String[] { "����ID", "����",
			"����-��������", "����-����", "Hownet�еĸ���", "����", "��������", "���ĺ���", "���ʽ̲İ汾",
			"���ʲ���", "�Ѷ�", "����ԭ��", "�龳����", "����", "ͬ���", "�����", "��չ", "�ٿ�", "�÷�",
			"��������", "����" };
	private static String[] propertySPARQLValue = { "?propertyID",
			"?instanceLabel", "?propertyFunction", "?propertyTopic",
			"?propertyClass", "?propertyPartsOfSpeech",
			"?propertyWordProperty", "?propertyChinese", "?propertyVersion",
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
			"?propertyVersion", "?propertyBook", "?instanceLabel",
			"?propertyClass", "?propertyAnswer", "?propertyScene",
			"?propertySentencePattern", "?propertyRelatedWords" };
	private static String[] sentencePropertyRelation = { "?relationID",
			"?relationVersion", "?relationBook", "?instanceLabel",
			"?relationClass", "?relationAnswer", "?relationScene",
			"?relationSentencePattern", "?relationRelatedWords" };

	public static void read() throws FileNotFoundException, IOException {

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

	/**
	 * @param args
	 * @throws IOException
	 * @throws BiffException
	 */
	public static void main(String[] args) throws BiffException, IOException {

		try {
			read();
		} catch (FileNotFoundException ex) {

		} catch (IOException ex) {

		}

		System.out.println("��ѡ������Ҫִ�еĲ���������1-10����");
		System.out.println("1.��ӵ���");
		System.out.println("2.��Ӿ���");
		System.out.println("3.������ӵ���");
		System.out.println("4.������Ӿ���");
		System.out.println("5.ɾ������");
		System.out.println("6.ɾ������");
		System.out.println("7.�޸ĵ���");
		System.out.println("8.�޸ľ���");
		System.out.println("9.����HowNet����鿴���е��ʼ�������");
		System.out.println("10.����ID�鿴���ʼ�������");
		System.out.println("11.�鿴���ʼ�������");
		System.out.println("12.����HowNet����鿴���о���");
		System.out.println("13.����ID�鿴���Ӽ�������");
		System.out.println("14.�鿴���Ӽ�������");

		System.out.println("15.��������鿴����ͬ�����ʼ�������");
		System.out.println("16.�������ѯ�����Լ�������ID");

		System.out.println("17.�����꼶����ҳ�5�����ʺ�2������");
		System.out.println("18.�����꼶����ҳ�5�����ʺ�3�����Ӽ���ش�");
		System.out.println("19.�����꼶��ѯ��������е�3������");
		System.out.println("20.�����꼶���������ʵ��Ѷ����������������");
		System.out.println("21.�����꼶���������������");
		System.out.println("22.�ж�ĳ�����Ƿ�����ڱ������");
		System.out.println("23.���ݵ��ʲ�ѯ�����ԭ�䣬������ͬ�꼶ͬ�����������������");
		System.out.println("24.���ݰ汾+�꼶+��Ԫ��ѯ���е��ʼ�������");

		System.out.println("26.��Fuseki���ݿ��е�����д��owl�ļ���");
		System.out.println("27.������ӵȼ۹�ϵ");
		System.out.println("28.����ȼ۹�ϵ");

		Scanner option = new Scanner(System.in);
		int operation = option.nextInt();

		OntologyManage ontologyManage = new OntologyManageImpl();
		String[] wordParameter = new String[21];
		String[] sentenceParameter = new String[9];

		Scanner sc = null;
		String yourProperty = null;
		String yourInstance = null;
		switch (operation) {
		case 1:
			int i = 0;
			sc = new Scanner(System.in);

			// addIndividualAndProperty.addInstance(yourClass, yourInstance);

			// ����ʵ����Label+����+���е�����
			for (i = 0; i < propertyLabel.length; i++) {
				if (propertyLabel[i].equals("����")) {
					// ����Ҫ��ӵ�ʵ��
					System.out.println("������������ӵĵ���:");
					yourInstance = sc.nextLine();
					wordParameter[i] = yourInstance;

				} else if (propertyLabel[i].equals("Hownet�еĸ���")) {
					// ����Ҫ��ӵ�ʵ����Hownet����
					String yourClass = findDEF(yourInstance);
					wordParameter[i] = yourClass;
				} else {
					System.out.println("�����뵥�ʵ�" + propertyLabel[i] + ":\n");
					yourProperty = null;
					yourProperty = sc.nextLine();
					wordParameter[i] = yourProperty;
				}
			}

			// ���β�ִ��
			ontologyManage.Add(wordParameter);

			System.out.println("���ʲ���ɹ���");
			break;

		case 2:
			sc = new Scanner(System.in);
			// ����ʵ����Label+����+���е�����
			for (i = 0; i < sentencePropertyLabel.length; i++) {
				if (sentencePropertyLabel[i].equals("����")) {
					// ����Ҫ��ӵ�ʵ��
					System.out.println("������������ӵľ���:");
					yourInstance = sc.nextLine();
					yourInstance = yourInstance.replace(' ', '_');
					sentenceParameter[i] = yourInstance;

				} else if (sentencePropertyLabel[i].equals("�������")) {
					// ����Ҫ��ӵ�ʵ����Hownet����
					String yourClass = yourInstance.substring(0,
							yourInstance.indexOf("_"));
					sentenceParameter[i] = yourClass;
				} else {
					System.out.println("��������ӵ�" + sentencePropertyLabel[i]
							+ ":\n");
					yourProperty = null;
					yourProperty = sc.nextLine();
					sentenceParameter[i] = yourProperty;
				}
			}

			// ���β�ִ��
			ontologyManage.AddSentence(sentenceParameter);

			System.out.println("���Ӳ���ɹ���");
			break;

		case 3:
			sc = new Scanner(System.in);
			System.out.println("������������ӵ�excel·��:");
			String yourPath = sc.nextLine();
			InputStream yourInputStream = new FileInputStream(yourPath);
			// ontologyManage.AddBatch(yourInputStream);
			ontologyManage.AddWordBatch(yourInputStream);
			break;
		case 4:
			sc = new Scanner(System.in);
			System.out.println("������������ӵ�excel·��:");
			yourPath = sc.nextLine();
			yourInputStream = new FileInputStream(yourPath);
			ontologyManage.AddSentenceBatch(yourInputStream);
			break;
		case 5:
			sc = new Scanner(System.in);
			System.out.println("������Ҫɾ���ĵ��ʵ�ID��\n");
			String yourInstanceID = sc.nextLine();
			ontologyManage.Delete(yourInstanceID);
			break;
		case 6:
			sc = new Scanner(System.in);
			System.out.println("������Ҫɾ���ľ��ӵ�ID��\n");
			yourInstanceID = sc.nextLine();
			ontologyManage.DeleteSentence(yourInstanceID);
			break;
		case 7:
			sc = new Scanner(System.in);
			System.out.println("������Ҫ�޸ĵĵ���ID��\n");
			String yourID = sc.nextLine();
			System.out.println("������Ҫ�޸ĵĵ��ʵ����ԣ�\n");
			String yourPropertyLabel = sc.nextLine();
			// System.out.println("������Ҫ�޸ĵ�ʵ��������ֵ��\n");
			// yourProperty = sc.nextLine();

			String yourSPARQLProperty = null;
			String yourRelationProperty = null;
			for (i = 0; i < propertyLabel.length; i++) {
				if (propertyLabel[i].equals(yourPropertyLabel)) {
					yourSPARQLProperty = propertySPARQLValue[i];
					yourRelationProperty = propertyRelation[i];
					break;
				}
			}
			ontologyManage.Modify(yourID, yourPropertyLabel,
					yourSPARQLProperty, yourRelationProperty);
			break;
		case 8:
			sc = new Scanner(System.in);
			System.out.println("������Ҫ�޸ĵľ���ID��\n");
			yourID = sc.nextLine();
			System.out.println("������Ҫ�޸ĵľ��ӵ����ԣ�\n");
			yourPropertyLabel = sc.nextLine();

			yourSPARQLProperty = null;
			yourRelationProperty = null;
			for (i = 0; i < sentencePropertyLabel.length; i++) {
				if (sentencePropertyLabel[i].equals(yourPropertyLabel)) {
					yourSPARQLProperty = sentencePropertySPARQLValue[i];
					yourRelationProperty = sentencePropertyRelation[i];
					break;
				}
			}
			ontologyManage.ModifySentence(yourID, yourPropertyLabel,
					yourSPARQLProperty, yourRelationProperty);

			break;
		case 9:
			sc = new Scanner(System.in);
			System.out.println("������Ҫ��ѯ���ࣺ\n");
			String yourClass = sc.nextLine();

			List<ResultSet> resultsInstanceList = ontologyManage
					.QueryWordAndPropertiesDependOnClass(yourClass);

			// ��ӡ���
			for (int index = 0; index < resultsInstanceList.size(); index++) {
				ResultSet thisResultSet = resultsInstanceList.get(index);
				if (thisResultSet.hasNext()) {
					while (thisResultSet.hasNext()) {
						QuerySolution solutionInstance = thisResultSet.next();
						System.out.println("�ࣺ" + yourClass + "\n");
						for (i = 0; i < propertyLabel.length; i++) {
							System.out
									.println("    ��������"
											+ propertyLabel[i]
											+ "��"
											+ subStringManage(solutionInstance
													.get(propertySPARQLValue[i])
													.toString()));
						}
						System.out.println("\n");
					}
				} else {
					System.out.println("֪ʶ�������û�д�ʵ��");
				}
			}

			break;

		case 10:
			sc = new Scanner(System.in);
			System.out.println("������Ҫ��ѯ�ĵ���ID��\n");
			yourID = sc.nextLine();
			ResultSet resultsInstance = ontologyManage.QueryIndividualDependOnId(yourID);

			if (resultsInstance.hasNext()) {
				while (resultsInstance.hasNext()) {
					// QuerySolution next()
					// Moves onto the next result.
					// �ƶ����¸�result��
					QuerySolution solutionInstance = resultsInstance.next();

					System.out.println("ʵ��ID��" + yourID + "\n");
					for (i = 0; i < propertyLabel.length; i++) {
						System.out.println("    ��������"
								+ propertyLabel[i]
								+ "��"
								+ subStringManage(solutionInstance.get(
										propertySPARQLValue[i]).toString()));
					}
					System.out.println("\n");
				}
			} else {
				System.out.println("֪ʶ�������û�д�ʵ��");
			}

			break;

		case 11:
			sc = new Scanner(System.in);
			System.out.println("������Ҫ��ѯ�ĵ��ʣ�\n");
			String yourWord = sc.nextLine();

			// ��õ��ʶ�Ӧ����ID�Ľ����
			resultsInstance = ontologyManage.QueryAWordAllId(yourWord);
			// resultsInstance = ontologyManage.QueryIndividual(yourWord);

			if (resultsInstance.hasNext()) {
				while (resultsInstance.hasNext()) {
					// QuerySolution next()
					// Moves onto the next result.
					// �ƶ����¸�result��
					QuerySolution solutionInstance = resultsInstance.next();

					// �ҳ��õ��ʵĶ�Ӧ������ID
					ResultSet resultsAllPropertyOfThisId = ontologyManage
							.QueryIndividualDependOnId(solutionInstance.get(
									"?propertyID").toString());
					QuerySolution solutionAllPropertyOfThisId = resultsAllPropertyOfThisId
							.next();

					// ��ӡ��ÿ��ID��Ӧ�ľ��Ӽ�������
					System.out.println("ʵ����" + yourWord + "\n");
					for (i = 0; i < propertyLabel.length; i++) {
						System.out
								.println("    ��������"
										+ propertyLabel[i]
										+ "��"
										+ subStringManage(solutionAllPropertyOfThisId
												.get(propertySPARQLValue[i])
												.toString()));
					}
					System.out.println("\n");
				}
			} else {
				System.out.println("֪ʶ�������û�д�ʵ��");
			}
			break;
		case 12:
			sc = new Scanner(System.in);
			System.out.println("������Ҫ��ѯ���ࣺ\n");
			yourClass = sc.nextLine();

			resultsInstance = ontologyManage.QueryWord(yourClass);

			// ��ӡ���
			if (resultsInstance.hasNext()) {
				while (resultsInstance.hasNext()) {
					QuerySolution solutionInstance = resultsInstance.next();
					System.out.println("�ࣺ"
							+ yourClass
							+ "\n"
							+ "    ��������ʵ����"
							+ solutionInstance
									.get("?instanceLabel")
									.toString()
									.substring(
											0,
											solutionInstance
													.get("?instanceLabel")
													.toString().indexOf("@"))
							+ "\n");
				}
			} else {
				System.out.println("֪ʶ�������û�д�ʵ��");
			}
			break;

		case 13:
			sc = new Scanner(System.in);
			System.out.println("������Ҫ��ѯ�ľ���ID��\n");
			yourID = sc.nextLine();
			resultsInstance = ontologyManage
					.QuerySentenceIndividualDependOnId(yourID);

			if (resultsInstance.hasNext()) {
				while (resultsInstance.hasNext()) {
					// QuerySolution next()
					// Moves onto the next result.
					// �ƶ����¸�result��
					QuerySolution solutionInstance = resultsInstance.next();

					System.out.println("����ID��" + yourID + "\n");
					for (i = 0; i < sentencePropertyLabel.length; i++) {

						System.out.println("    ��������"
								+ sentencePropertyLabel[i]
								+ "��"
								+ subStringManage(solutionInstance.get(
										sentencePropertySPARQLValue[i])
										.toString()));
					}
					System.out.println("\n");
				}
			} else {
				System.out.println("֪ʶ�������û�д�ʵ��");
			}

			break;

		case 14:
			sc = new Scanner(System.in);
			System.out.println("������Ҫ��ѯ�ľ��ӣ�\n");
			String yourSentence = sc.nextLine();

			// ��õ��ʶ�Ӧ����ID�Ľ����
			resultsInstance = ontologyManage.QueryASentenceAllId(yourSentence);
			// resultsInstance = ontologyManage.QueryIndividual(yourWord);

			if (resultsInstance.hasNext()) {
				while (resultsInstance.hasNext()) {
					// QuerySolution next()
					// Moves onto the next result.
					// �ƶ����¸�result��
					QuerySolution solutionInstance = resultsInstance.next();

					// �ҳ��þ��ӵĶ�Ӧ������ID
					ResultSet resultsAllPropertyOfThisId = ontologyManage
							.QuerySentenceIndividualDependOnId(solutionInstance
									.get("?propertyID").toString());
					QuerySolution solutionAllPropertyOfThisId = resultsAllPropertyOfThisId
							.next();

					// ��ӡ��ÿ��ID��Ӧ�ľ��Ӽ�������
					System.out.println("���ӣ�" + yourSentence + "\n");
					for (i = 0; i < sentencePropertyLabel.length; i++) {

						System.out.println("    ��������"
								+ sentencePropertyLabel[i]
								+ "��"
								+ subStringManage(solutionAllPropertyOfThisId
										.get(sentencePropertySPARQLValue[i])
										.toString()));
					}
					System.out.println("\n");
				}
			} else {
				System.out.println("֪ʶ�������û�д�ʵ��");
			}
			break;

		case 15:
			sc = new Scanner(System.in);
			System.out.println("������Ҫ��ѯ�����⣺\n");
			String yourTheme = sc.nextLine();
			List<ResultSet> resultsAllBrother = ontologyManage
					.QueryBrotherIndividual(yourTheme);
			for (i = 0; i < resultsAllBrother.size(); i++) {
				if (resultsAllBrother.get(i).hasNext()) {
					while (resultsAllBrother.get(i).hasNext()) {
						QuerySolution solutionEachBrother = resultsAllBrother
								.get(i).next();

						for (int j = 0; j < propertyLabel.length; j++) {
							System.out
									.println("    ��������"
											+ propertyLabel[j]
											+ "��"
											+ subStringManage(solutionEachBrother
													.get(propertySPARQLValue[j])
													.toString()));
						}
						System.out.println("\n");
					}
				} else {
					System.out.println("֪ʶ�������û�д�ʵ��");
				}
			}

			break;

		case 16:
			sc = new Scanner(System.in);
			System.out.println("������Ҫ��ѯ���ࣺ\n");
			yourClass = sc.nextLine();

			Map<String, String> resultsSentenceAndId = new HashMap<String, String>();
			resultsSentenceAndId = ontologyManage.QuerySentenceAndId(yourClass);

			// ��ӡ���
			if (resultsSentenceAndId.isEmpty()) {
				System.out.println("�޼�ֵ��");
			} else {
				for (Entry<String, String> s : resultsSentenceAndId.entrySet()) {
					System.out.println("��ֵ��:" + s);
				}
			}
			break;

		// ��������-------------------------------------------------------------------------
		case 17:
			// �����꼶����ҳ�5�����ʺ�2������
			sc = new Scanner(System.in);
			System.out.println("������Ҫ��ѯ�꼶��\n");
			String yourGrade = sc.nextLine();

			List<ResultSet> resultsFiveWordsOfThisGrade = ontologyManage
					.QueryFiveWordsOfThisGrade(yourGrade);

			// ��ѯ���꼶���5������
			if (resultsFiveWordsOfThisGrade.size() == 0) {
				System.out.println("���꼶�ĵ��ʽ����Ϊ�գ�����ӡ��");
			} else {
				// ��ӡ
				System.out.println(yourGrade + "�꼶��5������:" + "\n");
				for (int wordIndex = 0; wordIndex < resultsFiveWordsOfThisGrade
						.size(); wordIndex++) {
					while (resultsFiveWordsOfThisGrade.get(wordIndex).hasNext()) {
						QuerySolution solutionFiveWordsOfThisGrade = resultsFiveWordsOfThisGrade
								.get(wordIndex).next();

						for (int j = 0; j < propertyLabel.length; j++) {
							System.out
									.println("    ��������"
											+ propertyLabel[j]
											+ "��"
											+ subStringManage(solutionFiveWordsOfThisGrade
													.get(propertySPARQLValue[j])
													.toString()));
						}
						System.out.println("\n");
					}
				}

			}

			// ��ѯ���꼶���2������
			List<ResultSet> resultsTwoSentencesOfThisGrade = ontologyManage
					.QueryTwoSentencesOfThisGrade(yourGrade);
			if (resultsTwoSentencesOfThisGrade.size() == 0) {
				System.out.println("���꼶�ľ��ӽ����Ϊ�գ�����ӡ��");
			} else {
				// ��ӡ
				System.out.println(yourGrade + "�꼶��2������:" + "\n");
				for (int sentenceIndex = 0; sentenceIndex < resultsTwoSentencesOfThisGrade
						.size(); sentenceIndex++) {
					while (resultsTwoSentencesOfThisGrade.get(sentenceIndex)
							.hasNext()) {
						QuerySolution solutionTwoSentencesOfThisGrade = resultsTwoSentencesOfThisGrade
								.get(sentenceIndex).next();

						for (int j = 0; j < sentencePropertyLabel.length; j++) {

							System.out
									.println("    ��������"
											+ sentencePropertyLabel[j]
											+ "��"
											+ subStringManage(solutionTwoSentencesOfThisGrade
													.get(sentencePropertySPARQLValue[j])
													.toString()));
						}
						System.out.println("\n");
					}
				}
			}

			break;
		case 18:
			// �����꼶����ҳ�5�����ʺ�3�����Ӽ���ش�
			sc = new Scanner(System.in);
			System.out.println("������Ҫ��ѯ�꼶��\n");
			yourGrade = sc.nextLine();

			resultsFiveWordsOfThisGrade = ontologyManage
					.QueryFiveWordsOfThisGrade(yourGrade);

			// ��ѯ���꼶���5������
			if (resultsFiveWordsOfThisGrade.size() == 0) {
				System.out.println("���꼶�ĵ��ʽ����Ϊ�գ�����ӡ��");
			} else {
				// ��ӡ
				System.out.println(yourGrade + "�꼶��5������:" + "\n");
				for (int wordIndex = 0; wordIndex < resultsFiveWordsOfThisGrade
						.size(); wordIndex++) {
					while (resultsFiveWordsOfThisGrade.get(wordIndex).hasNext()) {
						QuerySolution solutionFiveWordsOfThisGrade = resultsFiveWordsOfThisGrade
								.get(wordIndex).next();

						for (int j = 0; j < propertyLabel.length; j++) {
							System.out
									.println("    ��������"
											+ propertyLabel[j]
											+ "��"
											+ subStringManage(solutionFiveWordsOfThisGrade
													.get(propertySPARQLValue[j])
													.toString()));
						}
						System.out.println("\n");
					}
				}

			}

			// ��ѯ���꼶���3�����Ӽ���ش�
			List<ResultSet> resultsThreeSentencesAndAnswerOfThisGrade = ontologyManage
					.QueryThreeSentencesOfThisGrade(yourGrade);
			if (resultsThreeSentencesAndAnswerOfThisGrade.size() == 0) {
				System.out.println("���꼶�ľ��ӽ����Ϊ�գ�����ӡ��");
			} else {
				// �����ֵ��
				System.out.println(yourGrade + "�꼶��3������:" + "\n");
				Map<String, String> threeSentencesAndAnswer = new HashMap<String, String>();
				for (int sentenceIndex = 0; sentenceIndex < resultsThreeSentencesAndAnswerOfThisGrade
						.size(); sentenceIndex++) {
					while (resultsThreeSentencesAndAnswerOfThisGrade.get(
							sentenceIndex).hasNext()) {
						QuerySolution solutionTwoSentencesOfThisGrade = resultsThreeSentencesAndAnswerOfThisGrade
								.get(sentenceIndex).next();

						// for (int j = 0; j < sentencePropertyLabel.length;
						// j++) {
						//
						// System.out
						// .println("    ��������"
						// + sentencePropertyLabel[j]
						// + "��"
						// + subStringManage(solutionTwoSentencesOfThisGrade
						// .get(sentencePropertySPARQLValue[j])
						// .toString()));
						// }
						String sentenceString = subStringManage(solutionTwoSentencesOfThisGrade
								.get(sentencePropertySPARQLValue[3]).toString());
						String anwerString = subStringManage(solutionTwoSentencesOfThisGrade
								.get(sentencePropertySPARQLValue[5]).toString());
						threeSentencesAndAnswer
								.put(sentenceString, anwerString);
						System.out.println("\n");
					}
				}

				// ��ӡ
				if (threeSentencesAndAnswer.isEmpty()) {
					System.out.println("�޼�ֵ��");
				} else {
					for (Entry<String, String> s : threeSentencesAndAnswer
							.entrySet()) {
						System.out.println("��ֵ��:" + s);
					}
				}
			}
			break;

		case 19:
			// �����꼶��ѯ��õ������ⲻͬ�����2������
			sc = new Scanner(System.in);
			System.out.println("������Ҫ��ѯ�꼶��\n");
			yourGrade = sc.nextLine();
			System.out.println("������õ��ʣ�\n");
			yourWord = sc.nextLine();

			List<String> twoWordsOfDifferentThemeList = ontologyManage
					.QueryTwoDifferentThemeWordsOfThisGrade(yourGrade, yourWord);
			for (i = 0; i < twoWordsOfDifferentThemeList.size(); i++) {
				System.out.println(i + 1 + "----"
						+ twoWordsOfDifferentThemeList.get(i));
			}

			break;

		case 20:
			// �����꼶���������ʵ��Ѷ����������������
			sc = new Scanner(System.in);
			System.out.println("������Ҫ��ѯ�꼶��\n");
			yourGrade = sc.nextLine();
			System.out.println("�����뵥��1���Ѷȣ�2��5����\n");
			String yourDifficultyOfWord1 = sc.nextLine();
			System.out.println("�����뵥��2���Ѷȣ�2��5����\n");
			String yourDifficultyOfWord2 = sc.nextLine();

			List<String> twoWordsDependOnDifficultyList = ontologyManage
					.QueryTwoWordsDependOnDifficulty(yourGrade,
							yourDifficultyOfWord1, yourDifficultyOfWord2);
			for (i = 0; i < twoWordsDependOnDifficultyList.size(); i++) {
				System.out.println(i + 1 + "----"
						+ twoWordsDependOnDifficultyList.get(i));
			}

			break;

		case 21:
			// �����꼶���������������
			sc = new Scanner(System.in);
			System.out.println("������Ҫ��ѯ�꼶��\n");
			yourGrade = sc.nextLine();

			List<ResultSet> twoRandomWordsOfThisGrade = ontologyManage
					.TwoRandomWordsOfThisGrade(yourGrade);
			for (int index = 0; index < twoRandomWordsOfThisGrade.size(); index++) {
				if (twoRandomWordsOfThisGrade.get(index).hasNext()) {
					while (twoRandomWordsOfThisGrade.get(index).hasNext()) {

						QuerySolution solutionTwoRandomWordsOfThisGrade = twoRandomWordsOfThisGrade
								.get(index).next();

						for (i = 0; i < propertyLabel.length; i++) {
							System.out
									.println("    ��������"
											+ propertyLabel[i]
											+ "��"
											+ subStringManage(solutionTwoRandomWordsOfThisGrade
													.get(propertySPARQLValue[i])
													.toString()));
						}
						System.out.println("\n");
					}
				} else {
					System.out.println("֪ʶ�������û�д�ʵ��");
				}
			}
			break;

		case 22:
			// �ж�ĳ�����Ƿ�����ڱ������
			sc = new Scanner(System.in);
			System.out.println("������Ҫ��ѯ�ĵ��ʣ�\n");
			yourWord = sc.nextLine();

			Boolean ifExistInFuseki = ontologyManage.IfExistInFuseki(yourWord);

			System.out.println("�Ƿ�����ڱ�����У���������");
			if (ifExistInFuseki) {
				System.out.println("����");
			} else {
				System.out.println("������");
			}

			break;

		case 23:
			// ���ݵ��ʲ�ѯ�����ԭ�䣬������ͬ�꼶ͬ�����������������
			sc = new Scanner(System.in);
			System.out.println("������Ҫ��ѯ�ĵ��ʣ�\n");
			yourWord = sc.nextLine();

			sc = new Scanner(System.in);
			System.out.println("�������û������꼶��\n");
			yourGrade = sc.nextLine();

			String textOfThisWord = new String();
			String themeOfThisWord = new String();

			// ��õ��ʶ�Ӧ����ID�Ľ����
			resultsInstance = ontologyManage.QueryAWordAllId(yourWord);
			// resultsInstance = ontologyManage.QueryIndividual(yourWord);

			if (resultsInstance.hasNext()) {
				while (resultsInstance.hasNext()) {
					// QuerySolution next()
					// Moves onto the next result.
					// �ƶ����¸�result��
					QuerySolution solutionInstance = resultsInstance.next();

					// �ҳ��õ��ʵĶ�Ӧ������ID
					ResultSet resultsAllPropertyOfThisId = ontologyManage
							.QueryIndividualDependOnId(solutionInstance.get(
									"?propertyID").toString());
					QuerySolution solutionAllPropertyOfThisId = resultsAllPropertyOfThisId
							.next();

					// ��ӡ����ID��Ӧ�Ĳ���
					String gradeOfThisWord = subStringManage(solutionAllPropertyOfThisId
							.get("?propertyBook").toString());
					System.out.println("ʵ���� " + yourWord + "  �ĵ��ʵĲ���Ϊ�� "
							+ gradeOfThisWord);
					int intgradeOfThisWord = Integer.parseInt(gradeOfThisWord);
					intgradeOfThisWord = (int) Math
							.ceil((double) intgradeOfThisWord / 2);
					gradeOfThisWord = String.valueOf(intgradeOfThisWord);

					// �ж����ID��Ӧ���꼶�Ƿ�����û������꼶
					if (gradeOfThisWord.equals(yourGrade)) {
						// �������򱣴����ԭ�䣬������ѭ��
						textOfThisWord = subStringManage(solutionAllPropertyOfThisId
								.get("?propertyText").toString());
						themeOfThisWord = subStringManage(solutionAllPropertyOfThisId
								.get("?propertyTopic").toString());
						if (themeOfThisWord.equals("��")) {
							themeOfThisWord = subStringManage(solutionAllPropertyOfThisId
									.get("?propertyFunction").toString());
						} else {
							// ʲô������
						}
						break;
					} else {
						continue;
					}
				}
			} else {
				System.out.println("֪ʶ�������û�д�ʵ��");
			}
			System.out.println("�õ��ʵĿ���ԭ��Ϊ�� " + textOfThisWord);

			List<String> otherTwoWords = ontologyManage.QueryTheTextOFThisWord(
					yourWord, yourGrade, themeOfThisWord);
			System.out.println("ͬ�꼶ͬ����ĵ��ʣ� ");
			for (i = 0; i < otherTwoWords.size(); i++) {
				System.out.println(otherTwoWords.get(i));
			}

			break;

		case 24:
			// ���ݰ汾�͵�Ԫ��ѯ���е��ʼ�������
			sc = new Scanner(System.in);
			System.out.println("������Ҫ��ѯ�Ľ̲İ汾��\n");
			String yourVersion = sc.nextLine();

			System.out.println("������Ҫ��ѯ�Ĳ�����\n");
			String yourBook = sc.nextLine();

			System.out.println("������Ҫ��ѯ�ĵ�Ԫ��\n");
			String yourUnit = sc.nextLine();

			List<ResultSet> allWordsOfAUnitList = ontologyManage
					.QueryAllWordsOfAUnit(yourVersion, yourBook, yourUnit);
			for (int index = 0; index < allWordsOfAUnitList.size(); index++) {
				ResultSet thisResultSet = allWordsOfAUnitList.get(index);
				if (thisResultSet.hasNext()) {
					while (thisResultSet.hasNext()) {
						// QuerySolution next()
						// Moves onto the next result.
						// �ƶ����¸�result��
						QuerySolution thisSolution = thisResultSet.next();

						System.out.println(yourVersion + "��" + yourBook + "��"
								+ "��" + yourUnit + "��Ԫ�ĵ��ʣ�  " + "\n");
						for (i = 0; i < propertyLabel.length; i++) {
							System.out
									.println("    ��������"
											+ propertyLabel[i]
											+ "��"
											+ subStringManage(thisSolution.get(
													propertySPARQLValue[i])
													.toString()));
						}
						System.out.println("\n");
					}
				} else {
					System.out.println("֪ʶ������и���������");
				}
			}

			break;

		// ��������-------------------------------------------------------------------------
		case 26:
			// ontologyManage.WriteBackToOwl();
			ontologyManage.WriteBackToRespectiveOwl();
			break;

		case 27:
			sc = new Scanner(System.in);
			System.out.println("������������ȼ۹�ϵ�ӵ�excel·��:");
			yourPath = sc.nextLine();
			yourInputStream = new FileInputStream(yourPath);
			ontologyManage.InsertRelationSameAs(yourInputStream);
			break;

		case 28:
			// ����֮ǰҪ�Ȱε�Fuseki����д��OWL��
			// ontologyManage.WriteBackToOwl();

			sc = new Scanner(System.in);
			System.out.println("������������ҵľ���:");
			yourSentence = sc.nextLine();
			ArrayList<String> allInformation = ontologyManage
					.ReasonSameAs(yourSentence);

			i = 0;
			for (String temp : allInformation) {
				i++;
				System.out.println(temp + "  ");
				if (i % 2 == 0) {
					System.out.println("\n");
				}
			}
			break;

		default:
			System.out.println("������1��17");
		}
	}

	// ����Hownet�еĸ���
	public static String findDEF(String W_e) {
		String yourClass = null;
		Vector<String> DEFS = ALLMEANS.get(W_e);
		ArrayList<String> result = new ArrayList();
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

	// �����ַ���
	private static String subStringManage(String string) {
		String newString = string.substring(string.indexOf(")") + 1,
				string.lastIndexOf("@"));
		return newString;
	}
}
