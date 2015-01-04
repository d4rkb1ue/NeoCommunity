package cn.edu.xmu.nextgencomm.util;

import java.util.Map;

public class CalculatorFactory {
	PrivateCalculatorUtil calculator;
	ShareCalculatorUtil shareCalculator;

	public PrivateCalculatorUtil createWithLadders(Map<Double, Double> ladder,
			String name, String para, String display) {
		// TODO Auto-generated method stub
		calculator = new LadderCalculatorUtil(name, para, display, ladder);

		return calculator;
	}

	public PrivateCalculatorUtil createWithItems(Map<String, Double> map,
			String name, String para, String display) {
		// TODO Auto-generated method stub
		calculator = new ItemCalculatorUtil(name, para, display, map);
		return calculator;
	}

	public PrivateCalculatorUtil calculatorWithLine(String line, String name,
			String para, String display) {
		// TODO Auto-generated method stub
		calculator = new LinerCalculatorUtil(name, para, display, line);

		return calculator;
	}

	public PrivateCalculatorUtil calculatorWithFix(String fix, String name,
			String para, String display) {
		// TODO Auto-generated method stub
		calculator = new FixCalculatorUtil(name, para, display, fix);
		return calculator;
	}

	public ShareCalculatorUtil createFloorCalculator(int meterId, String name,
			String displayName, boolean isAreaWeight, String calulateStyle,
			Object method) {
		shareCalculator = new FloorCalculatorUtil(meterId, name, displayName,
				isAreaWeight, calulateStyle, method);

		return shareCalculator;
	}

	public ShareCalculatorUtil createBuilingCalculator(int meterId,
			String name, String displayName, boolean isAreaWeight,
			boolean isFloorWeight, Map<Integer, Double> floorWeight,
			String calulateStyle, Object method) {
		shareCalculator = new BuildingCalculatorUtil(meterId, name,
				displayName, isAreaWeight, isFloorWeight, floorWeight,
				calulateStyle, method);
		return shareCalculator;
	}
}
