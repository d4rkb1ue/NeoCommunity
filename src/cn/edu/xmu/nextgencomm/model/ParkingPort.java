package cn.edu.xmu.nextgencomm.model;

public class ParkingPort {

	/** ���� **/
	private long id;
	/** ��λ���� **/
	private String type;
	/** ��λλ�� **/
	private String location;
	/** ��λ״̬ **/
	private int status;
	/** �������� **/
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
