package cn.edu.xmu.nextgencomm.util;

import java.util.Map;
import java.util.Map.Entry;

public class ItemCalculator extends Calculator{
	Map<String, Double> map;
	public ItemCalculator(String name, String para, String display,
			Object method) {
		super(name, para, display, method);
		// TODO Auto-generated constructor stub
		map=(Map<String, Double>)method;
		
	}

	@Override
	public double calculate(Map<String, Object> items) {
		// TODO Auto-generated method stub
		if(items.get(para) == null)
			return 0;
		double result = 0;
		Map<String,Integer> things=(Map<String, Integer>) items.get(para);
		//System.err.println(map.toString()+"things:"+things.toString());
		for (Entry<String, Integer> thing : things.entrySet()) {
			String str=thing.getKey();
			int num=thing.getValue();
			for (Entry<String, Double> entry : map.entrySet()) {
				String name=entry.getKey();
				double price=entry.getValue();
				if(str.equals(name))
					result += price*num;
			}
		}
		return result;
	}

}
