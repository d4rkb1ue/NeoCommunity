package cn.edu.xmu.nextgencomm.model;

import java.io.Serializable;
import java.sql.Date;

/**
 * ˮ������
 * 
 * @author Luyahui
 *
 */
public class Dosage implements Serializable {

	private static final long serialVersionUID = 1L;

	/** ���� **/
	private long id;
	/** ����һ���������ɵı�� **/
	private String serialNum;
	/** ��ˮ�� **/
	private double waterDosage;
	/** �õ��� **/
	private double electricityDosage;
	/** ��Ӧ������ **/
	private Date date;
	/** ��Ӧ�ķ��� **/
	private House house;

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

	public double getWaterDosage() {
		return waterDosage;
	}

	public void setWaterDosage(double waterDosage) {
		this.waterDosage = waterDosage;
	}

	public double getElectricityDosage() {
		return electricityDosage;
	}

	public void setElectricityDosage(double electricityDosage) {
		this.electricityDosage = electricityDosage;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public House getHouse() {
		return house;
	}

	public void setHouse(House house) {
		this.house = house;
	}
}
