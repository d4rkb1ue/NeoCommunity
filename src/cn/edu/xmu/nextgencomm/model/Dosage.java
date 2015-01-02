package cn.edu.xmu.nextgencomm.model;

import java.io.Serializable;
import java.sql.Date;

/**
 * 水电用量
 * 
 * @author Luyahui
 *
 */
public class Dosage implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 主键 **/
	private long id;
	/** 按照一定规则生成的编号 **/
	private String serialNum;
	/** 用水量 **/
	private double waterDosage;
	/** 用电量 **/
	private double electricityDosage;
	/** 对应的日期 **/
	private Date date;
	/** 对应的房间 **/
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
