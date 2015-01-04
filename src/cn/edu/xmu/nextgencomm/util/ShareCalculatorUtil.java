package cn.edu.xmu.nextgencomm.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ShareCalculatorUtil implements CalculatorUtil {
	public static String WEIGHT_FLOOR = "floor-weight";
	public static String WEIGHT = "weight";
	public static String FLOOR = "floor";
	public static String SHARE_STRATEGY_BUILDING = "building";
	public static String SHARE_STRATEGY_FLOOR = "floor";
	public static String SHARE_STRATEGY = "share-strategy";

	Map<String, Double> result;
	Map<String, Double> logicArea;
	double totalFee;

	Object method;
	int meterId;
	String name;
	String displayName;
	String shareStrategy;
	boolean isAreaWeight;
	String calulateStyle;

	public String getCalulateStyle() {
		return calulateStyle;
	}

	public abstract Map<String, Double> calculate(
			List<Map<String, Object>> houses, double total);

	public String getName() {
		return name;
	}

	public String getDisplayName() {
		return displayName;
	}

	public String getShareStrategy() {
		return shareStrategy;
	}

	public int getMeterId() {
		return meterId;
	}

	void calculateTotalFee(Double usage) {
		PrivateCalculatorUtil pre;
		CalculatorFactory calculatorFactory = new CalculatorFactory();
		if (calulateStyle.equals(PrivateCalculatorUtil.CALCULATE_STYLE_FIX)) {
			pre = calculatorFactory.calculatorWithFix((String) method, "pre",
					"usage", "pre");
		} else if (calulateStyle
				.equals(PrivateCalculatorUtil.CALCULATE_STYLE_LADDERS)) {
			pre = calculatorFactory.createWithLadders(
					(Map<Double, Double>) method, "pre", "usage", "pre");
		} else {
			pre = calculatorFactory.calculatorWithLine((String) method, "pre",
					"usage", "pre");
		}
		Map<String, Object> items = new HashMap<String, Object>();
		items.put("usage", usage);
		totalFee = pre.calculate(items);

	}
}
