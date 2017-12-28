package com.msword.parser;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class DocxParser {

	public static void go(ArrayList<HashMap<String, String>> arrParams, String sign, String firstPart,
			ArrayList<String> uniquePart) {

		XWPFDocument doc = null;
		String fileName = null;
		int totalReplace = 0;

		System.out.println("I'm starting to do wonders:\n");

		int cnt = 1;
		
		//We take each parameters
		for (HashMap<String, String> params : arrParams) {
			fileName = "";
			fileName = firstPart;

			try {
				//Take the template
				doc = new XWPFDocument(OPCPackage.open("./docx/template/Slave_characteristic.docx"));
			} catch (InvalidFormatException | IOException e) {
				// e.printStackTrace();
			}
			
			//We take each param
			for (Map.Entry<String, String> entry : params.entrySet()) {
				//We take each line
				for (XWPFParagraph p : doc.getParagraphs()) {
					List<XWPFRun> runs = p.getRuns();
					
					if (runs != null) {
						for (XWPFRun r : runs) {
							String text = r.getText(0);
							
							//If the text matches the key name, replace it with a value
							if (text != null && text.contains(sign + entry.getKey() + sign)) {
								totalReplace++;
								text = text.replace(sign + entry.getKey() + sign, entry.getValue());
								r.setText(text, 0);
								
								//If the same key, add value to the name
								for (String value : uniquePart) {
									if (entry.getKey().equals(value)) {
										fileName = fileName + "_" + entry.getValue();
									}
								}
							}
						}
					}
				}
			}

			try {
				if (fileName != null) {
					doc.write(new FileOutputStream("./docx/ready-made_documents/" + fileName + ".docx"));
					System.out.println(cnt + " document created with name: " + fileName);
					cnt++;
				}
			} catch (IOException e) {
				 e.printStackTrace();
			}
		}

		System.out.println("\nThere is nothing more to create ...");
		System.out.println("\nTotal replacements by placeholders: " + totalReplace);
	}
}