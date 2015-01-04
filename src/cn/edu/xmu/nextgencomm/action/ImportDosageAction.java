package cn.edu.xmu.nextgencomm.action;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import cn.edu.xmu.nextgencomm.model.Dosage;
import cn.edu.xmu.nextgencomm.model.House;
import cn.edu.xmu.nextgencomm.service.DosageService;
import cn.edu.xmu.nextgencomm.service.HouseService;

import com.opensymphony.xwork2.ActionSupport;

public class ImportDosageAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	@Autowired
	private HouseService houseService;
	@Autowired
	private DosageService dosageService;

	private List<Map<String, Object>> houseInfos;

	/** 获取整栋楼的水电用量信息的匹配字符串 **/
	private String getBuildString(int num) {
		String result = "";
		if (num < 10) {
			result = "__0" + String.valueOf(num) + "%";
		} else {
			result = "__" + String.valueOf(num) + "%";
		}
		return result;
	}

	/** 获取当前日期 **/
	private Date getCurrentDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		Date date = new Date(calendar.getTime().getTime());
		return date;
	}

	/** 获取上月日期 **/
	private Date getPreDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -2);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		Date date = new Date(calendar.getTime().getTime());
		return date;
	}

	@Override
	public String execute() throws Exception {
		houseInfos = new ArrayList<>();
		List<House> houses = houseService.get(getBuildString(1));
		Iterator<House> houseIterator = houses.iterator();
		while (houseIterator.hasNext()) {
			// 获取上月水电表读数
			House house = houseIterator.next();
			Dosage preDosage = dosageService.get(house.getSerialNum(),
					getPreDate());
			Map<String, Object> infoMap = new HashMap<String, Object>();
			infoMap.put("serialNum", house.getSerialNum());
			if (preDosage == null) {
				infoMap.put("preWaterDosage", 0);
				infoMap.put("preElectricityDosage", 0);
			} else {
				infoMap.put("preWaterDosage", preDosage.getWaterDosage());
				infoMap.put("preElectricityDosage",
						preDosage.getElectricityDosage());
			}
			houseInfos.add(infoMap);
		}
		System.out.println(houses.toString());
		return super.execute();
	}

	public List<Map<String, Object>> getHouseInfos() {
		return houseInfos;
	}

	public void setHouseInfos(List<Map<String, Object>> houseInfos) {
		this.houseInfos = houseInfos;
	}

}
