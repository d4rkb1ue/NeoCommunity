package cn.edu.xmu.nextgencomm.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.internal.compiler.ast.ThisReference;

import cn.edu.xmu.nextgencomm.util.Calculator;
import cn.edu.xmu.nextgencomm.util.CalculatorFactory;
import cn.edu.xmu.nextgencomm.util.Parse;

public class CalculateService {
	// 个人费用
	Map<String, Object> items;
	List<Calculator> calculators;
	Map<String, Double> result;

	public Map<String, Double> getResult(HashMap<String, Object> items) {
		this.items = items;
		exeCalculatePrivate();
		return result;
	}

	// public static void main(String[] args) {
	// CalculateService calculateService = new CalculateService();
	// calculateService.exeCalculatePrivate();
	// }

	public void exeCalculatePrivate() {
		// create 只需运行一次
		createCalculators();

		// getMaps();
		exe();
		System.out.println(result.toString());
	}

	private void exe() {
		// TODO Auto-generated method stub
		result = new HashMap<String, Double>();
		for (Calculator calculator : calculators) {

			double result1 = calculator.calculate(items);
			// 四舍五入
			double result2 = (double) (Math.round(result1 * 100) / 100.0);
			// System.err.println(result1+"--->"+result2);
			result.put(calculator.getDisplayName(), result2);
		}
	}

	public void createCalculators() {
		// TODO Auto-generated method stub
		CalculatorFactory calculatorFactory = new CalculatorFactory();
		calculators = new ArrayList<Calculator>();
		Parse parse = new Parse();
		parse.parse();
		Calculator calculator;
		for (Map<String, Object> plain : parse.calculators) {

			if (plain.containsKey(Calculator.CALCULATE_STYLE_LADDERS)) {
				calculator = calculatorFactory.createWithLadders(
						(Map<Double, Double>) plain
								.get(Calculator.CALCULATE_STYLE_LADDERS),
						(String) plain.get("name"), (String) plain
								.get("property"), (String) plain
								.get("display-name"));

			} else if (plain.containsKey(Calculator.CALCULATE_STYLE_ITEMS)) {
				calculator = calculatorFactory.createWithItems(
						(Map<String, Double>) (plain
								.get(Calculator.CALCULATE_STYLE_ITEMS)),
						(String) plain.get("name"), (String) plain
								.get("property"), (String) plain
								.get("display-name"));
			} else if (plain.containsKey(Calculator.CALCULATE_STYLE_LINE)) {
				calculator = calculatorFactory.calculatorWithLine(
						(String) plain.get(Calculator.CALCULATE_STYLE_LINE),
						(String) plain.get("name"),
						(String) plain.get("property"),
						(String) plain.get("display-name"));
			} else if (plain.containsKey(Calculator.CALCULATE_STYLE_FIX)) {

				calculator = calculatorFactory.calculatorWithFix(
						(String) plain.get(Calculator.CALCULATE_STYLE_FIX),
						(String) plain.get("name"),
						(String) plain.get("property"),
						(String) plain.get("display-name"));
			} else {
				calculator = null;
			}
			// System.err.println("Create one");
			if (calculator != null) {
				calculators.add(calculator);

			}
		}
	}

	private void getMaps() {
		Map<String, Integer> parking = new HashMap<String, Integer>();
		// items = new HashMap<String, Object>();
		// items.put("water-usage", 8.0);
		// items.put("electric-usage", 201.55);
		// items.put("area", 12.0);
		//
		// parking.put("地上", 1);
		// parking.put("地下", 2);
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
