package cn.edu.xmu.nextgencomm.util;

import java.util.Map;

public class LinerCalculatorUtil extends PrivateCalculatorUtil{
	double k;
	public LinerCalculatorUtil(String name, String para, String display,
			Object method) {
		super(name, para, display, method);
		// TODO Auto-generated constructor stub
		k=Double.parseDouble((String) method);
	}
	@Override
	public double calculate(Map<String, Object> items) {
		// TODO Auto-generated method stub
		if(items.get(para)==null)
			return 0;
		return (double)items.get(para) * k;
	}

}
