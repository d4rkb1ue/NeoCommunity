package cn.edu.xmu.nextgencomm.util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class LadderCalculatorUtil extends PrivateCalculatorUtil {

	Map<Double, Double> levels;
	ArrayList<Double> keys;
	public LadderCalculatorUtil(String name, String para, String display,
			Object method) {
		super(name, para, display, method);
		// TODO Auto-generated constructor stub
		levels=(Map<Double, Double>)method;
		//����
		keys=new ArrayList<Double>();
		for (Map.Entry<Double, Double> entry : levels.entrySet()) {
			keys.add(0, entry.getKey());
		}
		//System.err.println(keys.toString());
		
	}

	@Override
	public double calculate(Map<String, Object> items) {
		if(items.get(para)== null)
			return 0;
		double result = calculateLadder((double) items.get(para));
		
		//System.err.println((double) items.get(para));
		return result;
	}

	public double calculateLadder(double x) {
		double result = 0;
		
		for (double key : keys) {
			double multi = levels.get(key);
			
			if (x > key) {
				result = result + (x - key) * multi;
				x = key;
			}
		}
		return result;
	}

}
