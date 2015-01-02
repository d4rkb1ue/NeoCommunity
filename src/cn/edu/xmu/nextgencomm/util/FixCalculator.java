package cn.edu.xmu.nextgencomm.util;

import java.util.Map;

public class FixCalculator extends Calculator {
	public double fixFee;

	public FixCalculator(String name, String para, String display, Object method) {
		super(name, para, display, method);
		
		fixFee = 0;
		fixFee = Double.parseDouble((String) method);
	}

	@Override
	public double calculate(Map<String, Object> items) {
		// TODO Auto-generated method stub
		return fixFee;
	}

}
