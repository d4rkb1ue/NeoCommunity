package cn.edu.xmu.nextgencomm.bean;

import java.util.Map;

public class ShareResultBean {
	Map<String, Double> result;
	String displayName;
	String name;
	public Map<String, Double> getResult() {
		return result;
	}
	public void setResult(Map<String, Double> result) {
		this.result = result;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ShareResultBean(Map<String, Double> result,String displayName,String name){
		this.result=result;
		this.displayName=displayName;
		this.name=name;
		
	}
	@Override
	public String toString(){
		String to;
		to="name="+name+",displayName="+displayName+" "+result.toString();
		return to;
	}
}
