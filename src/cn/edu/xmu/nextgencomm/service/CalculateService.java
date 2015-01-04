package cn.edu.xmu.nextgencomm.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.nextgencomm.util.CalculatorFactory;
import cn.edu.xmu.nextgencomm.util.ParseUtil;
import cn.edu.xmu.nextgencomm.util.PrivateCalculatorUtil;

public class CalculateService {
	Map<String, Object> items;
	List<PrivateCalculatorUtil> calculators;
	Map<String, Double> result;

	// public static void main(String[] args) {
	// CalculateService calculateService = new CalculateService();
	// calculateService.exeCalculatePrivate();
	// }

	public Map<String, Double> exeCalculatePrivate(HashMap<String, Object> items) {
		this.items = items;
		createCalculators();

		// getMaps();
		exe();
		System.out.println(result.toString());
		return result;
	}

	private void exe() {
		// TODO Auto-generated method stub
		result = new HashMap<String, Double>();
		for (PrivateCalculatorUtil calculator : calculators) {

			double result1 = calculator.calculate(items);

			double result2 = (double) (Math.round(result1 * 100) / 100.0);
			// System.err.println(result1+"--->"+result2);
			result.put(calculator.getName(), result2);
		}
	}

	private void createCalculators() {
		// TODO Auto-generated method stub
		CalculatorFactory calculatorFactory = new CalculatorFactory();
		calculators = new ArrayList<PrivateCalculatorUtil>();
		ParseUtil parse = new ParseUtil();
		parse.parse();
		PrivateCalculatorUtil calculator;
		for (Map<String, Object> plain : parse.getCalculators()) {

			if (plain
					.containsKey(PrivateCalculatorUtil.CALCULATE_STYLE_LADDERS)) {
				calculator = calculatorFactory
						.createWithLadders(
								(Map<Double, Double>) plain
										.get(PrivateCalculatorUtil.CALCULATE_STYLE_LADDERS),
								(String) plain.get("name"), (String) plain
										.get("property"), (String) plain
										.get("display-name"));

			} else if (plain
					.containsKey(PrivateCalculatorUtil.CALCULATE_STYLE_ITEMS)) {
				calculator = calculatorFactory
						.createWithItems(
								(Map<String, Double>) (plain
										.get(PrivateCalculatorUtil.CALCULATE_STYLE_ITEMS)),
								(String) plain.get("name"), (String) plain
										.get("property"), (String) plain
										.get("display-name"));
			} else if (plain
					.containsKey(PrivateCalculatorUtil.CALCULATE_STYLE_LINE)) {
				calculator = calculatorFactory
						.calculatorWithLine(
								(String) plain
										.get(PrivateCalculatorUtil.CALCULATE_STYLE_LINE),
								(String) plain.get("name"), (String) plain
										.get("property"), (String) plain
										.get("display-name"));
			} else if (plain
					.containsKey(PrivateCalculatorUtil.CALCULATE_STYLE_FIX)) {

				calculator = calculatorFactory.calculatorWithFix((String) plain
						.get(PrivateCalculatorUtil.CALCULATE_STYLE_FIX),
						(String) plain.get("name"), (String) plain
								.get("property"), (String) plain
								.get("display-name"));
			} else {
				calculator = null;
			}
			// System.err.println("Create one");
			if (calculator != null) {
				calculators.add(calculator);

			}
		}
	}

	public void getMaps() {
		Map<String, Integer> parking = new HashMap<String, Integer>();
		// items = new HashMap<String, Object>();
		// items.put("water-usage", 8.0);
		// items.put("electric-usage", 201.55);
		// items.put("area", 12.0);
		//
		// items.put("parking-port", parking);

		items = new HashMap<String, Object>();
		items.clear();
		parking.clear();

		items.put("water-usage", 120.0);
		items.put("electric-usage", 125.5);
		items.put("area", 123.4);
		items.put("parking-port", parking);
	}

}
