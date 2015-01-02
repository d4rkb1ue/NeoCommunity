package cn.edu.xmu.nextgencomm.util;

import java.util.Map;

public class CalculatorFactory {
	Calculator calculator;

	public Calculator createWithLadders(Map<Double, Double> ladder,
			String name, String para, String display) {
		// TODO Auto-generated method stub
		calculator = new LadderCalculator(name, para, display, ladder);

		return calculator;
	}

	public Calculator createWithItems(Map<String, Double> map, String name,
			String para, String display) {
		// TODO Auto-generated method stub
		calculator = new ItemCalculator(name, para, display, map);
		return calculator;
	}

	public Calculator calculatorWithLine(String line, String name, String para,
			String display) {
		// TODO Auto-generated method stub
		calculator = new LinerCalculator(name, para, display, line);

		return calculator;
	}

	public Calculator calculatorWithFix(String fix, String name, String para,
			String display) {
		// TODO Auto-generated method stub
		calculator = new FixCalculator(name, para, display, fix);
		return calculator;
	}
}
