package cn.edu.xmu.nextgencomm.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.eclipse.jdt.internal.compiler.ast.ThisReference;

public class Parse {
	public static String fileName = "example.xml";
	public List<Map<String, Object>> calculators = new ArrayList<Map<String, Object>>();
	public Map<String, Object> calcultor;
	public Map<Double, Double> ladderMap;
	public Map<String, Double> itemsMap;

	public void parse() {
		SAXReader saxReader = new SAXReader();
		try {
			// System.out.println(System.getProperty("user.dir"));
			System.out.println(this.getClass().getResource("/example.xml"));
			File inputXml = new File(this.getClass()
					.getResource("/" + fileName).toURI());
			Document document = saxReader.read(inputXml);
			Element fees = document.getRootElement();
			// private-fee
			for (@SuppressWarnings("rawtypes")
			Iterator i = fees.elementIterator(); i.hasNext();) {
				calcultor = new HashMap<String, Object>();
				Element fee = (Element) i.next();
				// attribute
				for (Iterator j = fee.elementIterator(); j.hasNext();) {
					Element node = (Element) j.next();
					// attr=ladder
					if (node.getName().equals(
							Calculator.CALCULATE_STYLE_LADDERS)) {
						// ladder
						ladderMap = new HashMap<Double, Double>();
						for (Iterator m = node.elementIterator(); m.hasNext();) {
							Element ladder = (Element) m.next();

							ladderMap
									.put(Double
											.parseDouble(ladder
													.attributeValue(Calculator.CALCULATE_STYLE_FROM)),
											Double.parseDouble(ladder.getText()));

						}
						calcultor.put(Calculator.CALCULATE_STYLE_LADDERS,
								ladderMap);
						// System.out.println(ladderMap.toString());
					} else if (node.getName().equals(
							Calculator.CALCULATE_STYLE_ITEMS)) {
						itemsMap = new HashMap<String, Double>();
						for (Iterator mq = node.elementIterator(); mq.hasNext();) {
							Element item = (Element) mq.next();

							itemsMap.put(
									item.attributeValue(Calculator.CALCULATE_STYLE_TYPE),
									Double.parseDouble(item.getText()));

						}
						calcultor.put(Calculator.CALCULATE_STYLE_ITEMS,
								itemsMap);
						// System.out.println(itemsMap.toString());

					} else {
						calcultor.put(node.getName(), node.getText());
						// System.out.println(calcultor.toString());
					}
				}
				calculators.add(calcultor);
				// System.out.println(calculators.toString());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
