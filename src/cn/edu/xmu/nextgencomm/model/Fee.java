package cn.edu.xmu.nextgencomm.model;

import java.io.Serializable;
import java.sql.Date;

public class Fee implements Serializable {

	/** ���� **/
	private long id;
	/** ����һ���������ɵı�� **/
	private String serialNum;
	/** ˮ�� **/
	private double waterFee;
	/** ��� **/
	private double electricityFee;
	/** ��������� **/
	private double wasteFee;
	/** ��λ����� **/
	private double carportFee;
	/** ��̯�������� **/
	private double publicLightFee;
	/** ��̯���ݷ� **/
	private double publicElevatorFee;
	/** ��ҵ����� **/
	private double propertyFee;
	/** ���ɽ� **/
	private double lateFee;
	/** �������� **/
	private Date date;
	/** �������� **/
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
