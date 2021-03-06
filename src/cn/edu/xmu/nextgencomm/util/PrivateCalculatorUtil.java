package cn.edu.xmu.nextgencomm.util;

import java.util.Map;

public abstract class PrivateCalculatorUtil implements CalculatorUtil {
	public static String CALCULATE_STYLE_LADDERS = "ladders";
	public static String CALCULATE_STYLE_LINE = "line";
	public static String CALCULATE_STYLE_ITEMS = "items";
	public static String CALCULATE_STYLE_FROM = "from";
	public static String CALCULATE_STYLE_TYPE = "type";
	public static String CALCULATE_STYLE_FIX = "fix";

	String name;
	String displayName;
	String para;

	public PrivateCalculatorUtil(String name, String para, String display,
			Object method) {
		this.name = name;
		this.para = para;
		this.displayName = display;
	}

	public abstract double calculate(Map<String, Object> items);

	public String getDisplayName() {
		return displayName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
