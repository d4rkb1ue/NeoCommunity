package cn.edu.xmu.nextgencomm.action;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

public class TestAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private List<String> names;

	@Override
	public String execute() throws Exception {
		System.out.println("test");
		names = new ArrayList<String>();
		names.add("lu");
		names.add("quan");
		names.add("shi");
		names.add("shen");
		return super.execute();
	}

	public List<String> getNames() {
		return names;
	}

	public void setNames(List<String> names) {
		this.names = names;
	}

}
