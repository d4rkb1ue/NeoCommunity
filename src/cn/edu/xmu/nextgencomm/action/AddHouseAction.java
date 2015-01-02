package cn.edu.xmu.nextgencomm.action;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;

import cn.edu.xmu.nextgencomm.dao.impl.HouseDaoImpl;
import cn.edu.xmu.nextgencomm.model.House;

import com.opensymphony.xwork2.ActionSupport;

public class AddHouseAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	@Autowired
	private HouseDaoImpl houseDaoImpl;
	private String area;

	public String execute() {

		// House house = new House();
		// house.setArea(Float.parseFloat(area));
		// houseDaoImpl.save(house);
		return "success";
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
}
