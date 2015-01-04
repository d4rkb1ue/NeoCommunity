package cn.edu.xmu.nextgencomm.model;

import java.io.Serializable;

/**
 * һ��¥
 * 
 * @author Luyahui
 *
 */
public class Floor implements Serializable {

	private static final long serialVersionUID = 1L;

	/** ���� **/
	private long id;
	/** ��� **/
	private int floorNum;
	/** ������Building **/
	private Building building;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getFloorNum() {
		return floorNum;
	}

	public void setFloorNum(int floorNum) {
		this.floorNum = floorNum;
	}

	public Building getBuilding() {
		return building;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}

}
