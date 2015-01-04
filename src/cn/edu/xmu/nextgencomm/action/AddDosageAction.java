package cn.edu.xmu.nextgencomm.action;

import java.sql.Date;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;

import cn.edu.xmu.nextgencomm.service.DosageService;
import cn.edu.xmu.nextgencomm.service.FeeService;

import com.opensymphony.xwork2.ActionSupport;

public class AddDosageAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	/** 楼号 **/
	private String buildingID;
	/** 楼层号 **/
	private String floorID;
	/** 房间号 **/
	private String roomID;
	/** 用水量 **/
	private String waterDosage;
	/** 用电量 **/
	private String electricityDosage;
	/** 日期 **/
	private String date;

	@Autowired
	private DosageService dosageService;
	@Autowired
	private FeeService feeService;

	/** 获取当前日期 **/
	private Date getCurrentDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		Date date = new Date(calendar.getTime().getTime());
		return date;
	}

	/** 获取上月日期 **/
	private Date getPreDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		Date date = new Date(calendar.getTime().getTime());
		return date;
	}

	@Override
	public String execute() throws Exception {
		// 保存对象到数据库
		// System.out.println(buildingID + floorID + roomID + waterDosage
		// + electricityDosage + date);
		// dosageService.addDosage(buildingID, floorID, roomID, waterDosage,
		// electricityDosage, date + "-01");
		System.out.println(getCurrentDate().toString());
		feeService.calculate(getCurrentDate());
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
