package cn.edu.xmu.nextgencomm.action;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;

import cn.edu.xmu.nextgencomm.service.DosageService;
import cn.edu.xmu.nextgencomm.service.FeeService;

import com.opensymphony.xwork2.ActionSupport;

public class AddDosageAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	/** ¥�� **/
	private String buildingID;
	/** ¥��� **/
	private String floorID;
	/** ����� **/
	private String roomID;
	/** ��ˮ�� **/
	private String waterDosage;
	/** �õ��� **/
	private String electricityDosage;
	/** ���� **/
	private String date;

	@Autowired
	private DosageService dosageService;
	@Autowired
	private FeeService feeService;

	@Override
	public String execute() throws Exception {
		// ����������ݿ�
		// System.out.println(buildingID + floorID + roomID + waterDosage
		// + electricityDosage + date);
		// dosageService.addDosage(buildingID, floorID, roomID, waterDosage,
		// electricityDosage, date + "-01");
		// feeService.calculate(Date.valueOf("2015-1-1"));
		System.out.println("adddosage");
		feeService.test();
		return "success";
	}

	public String getBuildingID() {
		return buildingID;
	}

	public void setBuildingID(String buildingID) {
		this.buildingID = buildingID;
	}

	public String getFloorID() {
		return floorID;
	}

	public void setFloorID(String floorID) {
		this.floorID = floorID;
	}

	public String getRoomID() {
		return roomID;
	}

	public void setRoomID(String roomID) {
		this.roomID = roomID;
	}

	public String getWaterDosage() {
		return waterDosage;
	}

	public void setWaterDosage(String waterDosage) {
		this.waterDosage = waterDosage;
	}

	public String getElectricityDosage() {
		return electricityDosage;
	}

	public void setElectricityDosage(String electricityDosage) {
		this.electricityDosage = electricityDosage;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public DosageService getDosageService() {
		return dosageService;
	}

	public void setDosageService(DosageService dosageService) {
		this.dosageService = dosageService;
	}
}
