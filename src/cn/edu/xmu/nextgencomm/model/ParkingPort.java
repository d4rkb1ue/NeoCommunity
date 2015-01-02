package cn.edu.xmu.nextgencomm.model;

public class ParkingPort {

	/** 主键 **/
	private long id;
	/** 车位类型 **/
	private String type;
	/** 车位位置 **/
	private String location;
	/** 车位状态 **/
	private int status;
	/** 所属房屋 **/
	House house;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public House getHouse() {
		return house;
	}

	public void setHouse(House house) {
		this.house = house;
	}

}
