package cn.edu.xmu.nextgencomm.model;

import java.io.Serializable;

/**
 * һ��¥
 * 
 * @author Luyahui
 *
 */
public class Building implements Serializable {

	private static final long serialVersionUID = 1L;
	/** ���� **/
	private long id;
	/** ���� **/
	private String name;
	/** ¥�� **/
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
