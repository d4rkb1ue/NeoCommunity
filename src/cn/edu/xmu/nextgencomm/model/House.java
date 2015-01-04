package cn.edu.xmu.nextgencomm.model;

import java.io.Serializable;

public class House implements Serializable {

	private static final long serialVersionUID = 1L;

	/** ���� **/
	private long id;
	/** ͳһ��� **/
	private String serialNum;
	/** ����� **/
	private int houseNum;
	/** ������¥�� **/
	Floor floor;
	/** ������� **/
	private double area;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}

	public int getHouseNum() {
		return houseNum;
	}

	public void setHouseNum(int houseNum) {
		this.houseNum = houseNum;
	}

	public Floor getFloor() {
		return floor;
	}

	public void setFloor(Floor floor) {
		this.floor = floor;
	}

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}
}
