package cn.edu.xmu.nextgencomm.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
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
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class ParseUtil {
	public static String fileName = "example.xml";
	// -------private---------
	private List<Map<String, Object>> calculators = new ArrayList<Map<String, Object>>();
	private Map<String, Object> calcultor;
	private Map<Double, Double> ladderMap;
	private Map<String, Double> itemsMap;
	// -------share--------
	private Map<Integer, Double> infoMap;
	private Map<String, Object> shareCalculator;
	private List<Map<String, Object>> shareCalculators = new ArrayList<Map<String, Object>>();

	private Map<Double, Double> parseLadderMap(Element node) {
		Map<Double, Double> ladderMap2 = new HashMap<Double, Double>();
		for (Iterator m = node.elementIterator(); m.hasNext();) {
			Element ladder = (Element) m.next();

			ladderMap2
					.put(Double
							.parseDouble(ladder
									.attributeValue(PrivateCalculatorUtil.CALCULATE_STYLE_FROM)),
							Double.parseDouble(ladder.getText()));

		}
		return ladderMap2;
	}

	private Map<String, Double> parseItemMap(Element node) {
		Map<String, Double> itemsMap2 = new HashMap<String, Double>();
		for (Iterator mq = node.elementIterator(); mq.hasNext();) {
			Element item = (Element) mq.next();

			itemsMap2
					.put(item
							.attributeValue(PrivateCalculatorUtil.CALCULATE_STYLE_TYPE),
							Double.parseDouble(item.getText()));
		}
		return itemsMap2;
	}

	public void parse() {

		SAXReader saxReader = new SAXReader();
		try {
			File inputXml = new File(this.getClass()
					.getResource("/" + fileName).getPath());

			Document document = saxReader.read(inputXml);
			Element fees = document.getRootElement();
			// xx-fee
			for (@SuppressWarnings("rawtypes")
			Iterator i = fees.elementIterator(); i.hasNext();) {

				Element fee = (Element) i.next();
				// private-fee
				if (fee.getName() == "private-fee") {
					calcultor = new HashMap<String, Object>();
					// attribute
					for (Iterator j = fee.elementIterator(); j.hasNext();) {
						Element node = (Element) j.next();
						// attr=ladder
						if (node.getName().equals(
								PrivateCalculatorUtil.CALCULATE_STYLE_LADDERS)) {
							// ladder
							ladderMap = parseLadderMap(node);
							calcultor
									.put(PrivateCalculatorUtil.CALCULATE_STYLE_LADDERS,
											ladderMap);
							// System.out.println(ladderMap.toString());
						} else if (node.getName().equals(
								PrivateCalculatorUtil.CALCULATE_STYLE_ITEMS)) {
							itemsMap = parseItemMap(node);

							calcultor
									.put(PrivateCalculatorUtil.CALCULATE_STYLE_ITEMS,
											itemsMap);
							// System.out.println(itemsMap.toString());

						} else {
							calcultor.put(node.getName(), node.getText());
							// System.out.println(calcultor.toString());
						}
					}
					calculators.add(calcultor);
					// System.out.println(calculators.toString());
				}// end if private
					// share-fee
				else if (fee.getName() == "share-fee") {

					shareCalculator = new HashMap<String, Object>();
					// attr
					for (Iterator j = fee.elementIterator(); j.hasNext();) {

						Element node = (Element) j.next();
						// floor-weight
						if (node.getName().equals(
								ShareCalculatorUtil.WEIGHT_FLOOR)) {
							infoMap = new HashMap<Integer, Double>();
							for (Iterator m = node.elementIterator(); m
									.hasNext();) {
								Element floor = (Element) m.next();
								infoMap.put(
										Integer.parseInt(floor
												.attributeValue(ShareCalculatorUtil.FLOOR)),
										Double.parseDouble(floor.getText()));

							}
							shareCalculator.put(
									ShareCalculatorUtil.WEIGHT_FLOOR, infoMap);
						} else if (node.getName().equals(
								PrivateCalculatorUtil.CALCULATE_STYLE_LADDERS)) {
							// ladder
							ladderMap = parseLadderMap(node);
							shareCalculator
									.put(PrivateCalculatorUtil.CALCULATE_STYLE_LADDERS,
											ladderMap);
							// System.out.println(ladderMap.toString());
						} else if (node.getName().equals(
								PrivateCalculatorUtil.CALCULATE_STYLE_ITEMS)) {
							itemsMap = parseItemMap(node);

							shareCalculator
									.put(PrivateCalculatorUtil.CALCULATE_STYLE_ITEMS,
											itemsMap);
							// System.out.println(itemsMap.toString());

						} else {
							shareCalculator.put(node.getName(), node.getText());
						}

					}
					shareCalculators.add(shareCalculator);

				}// end if share
				else if (fee.getName().equals("empty-room")) {
					for (Iterator j = fee.elementIterator(); j.hasNext();) {
						Element node = (Element) j.next();
						if (node.getName().equals("water"))
							emptyRoomWater = Double.parseDouble(node.getText());
						else if (node.getName().equals("electric"))
							emptyRoomElectric = Double.parseDouble(node
									.getText());

					}
				}
			}
			// create new *.cfg.xml

			createCfgXml();
		} catch (DocumentException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void createCfgXml() throws IOException {
		for (Map<String, Object> plain : calculators) {
			// 输出文本文件
			File f = new File(this.getClass().getResource("/").getPath()
					+ "entityconfig" + File.separator
					+ (String) plain.get("name") + ".hbm.xml");
			FileOutputStream fop = new FileOutputStream(f);
			// 构建FileOutputStream对象,文件不存在会自动新建

			OutputStreamWriter writer = new OutputStreamWriter(fop, "UTF-8");
			// 构建OutputStreamWriter对象,参数可以指定编码,默认为操作系统默认编�?,windows上是gbk

			writer.append("<?xml version=\"1.0\"?>");
			// 换行
			writer.append("\r\n");

			writer.append("<!DOCTYPE hibernate-mapping PUBLIC");
			writer.append("\r\n");
			writer.append("\"-//Hibernate/Hibernate Mapping DTD//EN\"");
			writer.append("\r\n");
			writer.append("\"http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd\">");
			writer.append("\r\n");
			writer.append("<hibernate-mapping>");
			writer.append("\r\n");
			writer.append("<class entity-name=\"" + (String) plain.get("name")
					+ "\">");
			writer.append("\r\n");
			writer.append("<composite-id>");
			writer.append("\r\n");
			writer.append("<key-property name=\"serialNum\" type=\"string\"></key-property>");
			writer.append("\r\n");
			writer.append("<key-property name=\"date\" type=\"java.sql.Date\"></key-property>");
			writer.append("\r\n");
			writer.append("</composite-id>");
			writer.append("\r\n");
			writer.append("<property name=\"fee\" type=\"double\" not-null=\"true\" column=\"fee\" />");
			writer.append("\r\n");
			writer.append("<property name=\"payStatus\" type=\"boolean\" not-null=\"true\" column=\"payStatus\" />");
			writer.append("\r\n");
			writer.append("</class>");
			writer.append("\r\n");
			writer.append("</hibernate-mapping>");
			writer.append("\r\n");
			writer.append("");
			writer.append("\r\n");
			// 关闭写入�?,同时会把缓冲区内容写入文�?,�?以上面的注释�?
			writer.close();
			fop.close();

		}

	}

	public List<Map<String, Object>> getCalculators() {
		return calculators;
	}

	public List<Map<String, Object>> getShareCalculators() {
		return shareCalculators;
	}

	private double emptyRoomWater, emptyRoomElectric;

	public double getEmptyRoomWater() {
		return emptyRoomWater;
	}

	public double getEmptyRoomElectric() {
		return emptyRoomElectric;
	}

}
