package cn.edu.xmu.nextgencomm.model;

import java.io.Serializable;

/**
 * Ò»¶°Â¥
 * 
 * @author Luyahui
 *
 */
public class Building implements Serializable {

	private static final long serialVersionUID = 1L;
	/** Ö÷¼ü **/
	private long id;
	/** Ãû³Æ **/
	private String name;
	/** Â¥ºÅ **/
	private int buildingNum;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBuildingNum() {
		return buildingNum;
	}

	public void setBuildingNum(int buildingNum) {
		this.buildingNum = buildingNum;
	}

}
