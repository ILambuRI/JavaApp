package com.xml.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlParser {

	public static ArrayList<HashMap<String, String>> getSlavesParams(String path) {
		ArrayList<HashMap<String, String>> slavesData = new ArrayList<>();

		try {
			// Создается построитель документа
			DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			// Создается дерево DOM документа из файла
			Document document = documentBuilder.parse(path);

			// Получаем корневой элемент
			Node root = document.getDocumentElement();

			// Просматриваем все подэлементы корневого - т.е. раб
			NodeList slaves = root.getChildNodes();
			for (int i = 0; i < slaves.getLength(); i++) {
				Node slave = slaves.item(i);

				// Если нода не текст, то это раб - заходим внутрь
				if (slave.getNodeType() != Node.TEXT_NODE && slave.getNodeName().equals("Slave")) {
					HashMap<String, String> slaveArrayParams = new HashMap<>();

					NodeList slaveParams = slave.getChildNodes();
					for (int j = 0; j < slaveParams.getLength(); j++) {
						Node slaveParam = slaveParams.item(j);

						// Если нода не текст, то это один из параметров раба
						if (slaveParam.getNodeType() != Node.TEXT_NODE) {

							// Первый символ имени node в нижний регистр
							String nodeName = slaveParam.getNodeName();
							String paramName = Character.toLowerCase(nodeName.charAt(0))
									+ (nodeName.length() > 1 ? nodeName.substring(1) : "");

							slaveArrayParams.put(paramName, slaveParam.getChildNodes().item(0).getTextContent());
						}
					}

					// Добавляем в array map c параметрами раба
					slavesData.add(slaveArrayParams);
				}
			}

		} catch (ParserConfigurationException ex) {
			ex.printStackTrace(System.out);
		} catch (SAXException ex) {
			ex.printStackTrace(System.out);
		} catch (IOException ex) {
			ex.printStackTrace(System.out);
		}

		return slavesData;
	}

	public static String getConstant(String path, String constName) {
		String constant = "";

		try {
			// Создается построитель документа
			DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

			// Создается дерево DOM документа из файла
			Document document = documentBuilder.parse(path);
			NodeList constants = document.getElementsByTagName(constName);

			for (int i = 0; i < constants.getLength(); i++) {
				Node con = constants.item(i);

				// Если нода не текст, то это константа - заходим внутрь
				if (con.getNodeType() != Node.TEXT_NODE && con.getNodeName().equals(constName)) {

					// Пишем содержимое
					constant = con.getTextContent();
				}
			}
		} catch (ParserConfigurationException ex) {
			ex.printStackTrace(System.out);
		} catch (SAXException ex) {
			ex.printStackTrace(System.out);
		} catch (IOException ex) {
			ex.printStackTrace(System.out);
		}

		return constant;
	}
}
