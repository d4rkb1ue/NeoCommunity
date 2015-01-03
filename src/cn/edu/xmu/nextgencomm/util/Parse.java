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
import java.net.URISyntaxException;
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

public class Parse {
	public static String fileName = "example.xml";
	public List<Map<String, Object>> calculators = new ArrayList<Map<String, Object>>();
	public Map<String, Object> calcultor;
	public Map<Double, Double> ladderMap;
	public Map<String, Double> itemsMap;

	public void parse() {

		SAXReader saxReader = new SAXReader();
		try {
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
			// create new *.cfg.xml
			createCfgXml();
		} catch (DocumentException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void createCfgXml() throws IOException, URISyntaxException {
		for (Map<String, Object> plain : calculators) {
			// 输出文本文件
			String realPath = this.getClass().getResource("/entityconfig")
					.getPath();
			// + (String) plain.get("name") + ".hbm.xml";
			// File f = new File("E:" + File.separator + "files" +
			// File.separator
			// + (String) plain.get("name") + ".hbm.xml");
			System.out.print(realPath);
			File f = new File(new File(realPath), (String) plain.get("name")
					+ ".hbm.xml");
			FileOutputStream fop = new FileOutputStream(f);
			// 构建FileOutputStream对象,文件不存在会自动新建

			OutputStreamWriter writer = new OutputStreamWriter(fop, "UTF-8");
			// 构建OutputStreamWriter对象,参数可以指定编码,默认为操作系统默认编码,windows上是gbk

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
			writer.append("<property name=\"displayName\" type=\"string\" not-null=\"true\" column=\"displayName\" />");
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
			// 关闭写入流,同时会把缓冲区内容写入文件,所以上面的注释掉
			writer.close();
			fop.close();

		}

	}
}
