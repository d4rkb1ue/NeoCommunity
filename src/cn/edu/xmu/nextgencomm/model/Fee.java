package cn.edu.xmu.nextgencomm.model;

import java.io.Serializable;
import java.sql.Date;

public class Fee implements Serializable {

	/** 主键 **/
	private long id;
	/** 按照一定规则生成的编号 **/
	private String serialNum;
	/** 水费 **/
	private double waterFee;
	/** 电费 **/
	private double electricityFee;
	/** 垃圾处理费 **/
	private double wasteFee;
	/** 车位管理费 **/
	private double carportFee;
	/** 公摊照明费用 **/
	private double publicLightFee;
	/** 公摊电梯费 **/
	private double publicElevatorFee;
	/** 物业管理费 **/
	private double propertyFee;
	/** 滞纳金 **/
	private double lateFee;
	/** 所属日期 **/
	private Date date;
	/** 所属房间 **/
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

	public double getWaterFee() {
		return waterFee;
	}

	public void setWaterFee(double waterFee) {
		this.waterFee = waterFee;
	}

	public double getElectricityFee() {
		return electricityFee;
	}

	public void setElectricityFee(double electricityFee) {
		this.electricityFee = electricityFee;
	}

	public double getWasteFee() {
		return wasteFee;
	}

	public void setWasteFee(double wasteFee) {
		this.wasteFee = wasteFee;
	}

	public double getCarportFee() {
		return carportFee;
	}

	public void setCarportFee(double carportFee) {
		this.carportFee = carportFee;
	}

	public double getPublicLightFee() {
		return publicLightFee;
	}

	public void setPublicLightFee(double publicLightFee) {
		this.publicLightFee = publicLightFee;
	}

	public double getPublicElevatorFee() {
		return publicElevatorFee;
	}

	public void setPublicElevatorFee(double publicElevatorFee) {
		this.publicElevatorFee = publicElevatorFee;
	}

	public double getPropertyFee() {
		return propertyFee;
	}

	public void setPropertyFee(double propertyFee) {
		this.propertyFee = propertyFee;
	}

	public double getLateFee() {
		return lateFee;
	}

	public void setLateFee(double lateFee) {
		this.lateFee = lateFee;
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
