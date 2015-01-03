package cn.edu.xmu.nextgencomm.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.xmu.nextgencomm.dao.impl.DosageDaoImpl;
import cn.edu.xmu.nextgencomm.dao.impl.FeeDaoImpl;
import cn.edu.xmu.nextgencomm.model.Dosage;

/**
 * 从数据库中读取数据，向计费器中传入数据，计算出费用详情
 * 
 * @author Luyahui
 *
 */
@Repository
@Transactional
public class FeeService {
	@Autowired
	private DosageDaoImpl dosageDaoImpl;
	@Autowired
	private FeeDaoImpl feeDaoImpl;

	/** 一栋楼的水电用量信息 **/
	private List<Map<String, Object>> dosages = new ArrayList<>();

	/** 一栋楼的费用信息 **/
	// private List<Fee> fees = new ArrayList<Fee>();

	/** 获取整栋楼的水电用量信息的匹配字符串 **/
	private String getBuildString(int num) {
		String result = "";
		if (num < 10) {
			result = "ww0" + String.valueOf(num) + "wwww";
		} else {
			result = "ww" + String.valueOf(num) + "wwww";
		}
		return result;
	}

	public void calculate(Date date) {
		int buildingNum = 5;
		for (int i = 0; i < buildingNum; i++) {
			dosages.clear();
			// 本月用量读数
			List<Dosage> currentDosages = dosageDaoImpl.get(date,
					getBuildString(i));
			// 上月用量读数
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.MONTH, -1);
			Date preDate = new Date(calendar.getTime().getTime());
			List<Dosage> preDosages = dosageDaoImpl.get(preDate,
					getBuildString(i));
			// 计算本月用量
			Iterator<Dosage> currentDI = currentDosages.iterator();
			while (currentDI.hasNext()) {
				// 得到某个房间的本月读数和上月读数
				Dosage currentDosage = currentDI.next();
				Dosage preDosage = new Dosage();
				Iterator<Dosage> preDI = preDosages.iterator();
				while (preDI.hasNext()) {
					preDosage = preDI.next();
					if (currentDosage.getSerialNum() == preDosage
							.getSerialNum()) {
						break;
					}
				}
				// 将该用户的数据放入Map中
				Map<String, Object> dMap = new HashMap<String, Object>();
				dMap.put("water-usage", currentDosage.getWaterDosage()
						- preDosage.getWaterDosage());
				dMap.put("electric-usage", currentDosage.getElectricityDosage()
						- preDosage.getElectricityDosage());
				dMap.put("area", currentDosage.getHouse().getArea());
				Map<String, Integer> parking = new HashMap<String, Integer>();
				dMap.put("parking-port", parking);
				// 将Map对象放入List中
				dosages.add(dMap);
			}
			// 将dosages传入CalculatorService中
		}
	}

	public void test() {
		String[] array = { "water_fee", "水费", "01010101", "15" };
		System.out.println(array.toString());
		new CalculateService().createCalculators();
		// List<String[]> fees = new ArrayList<>();
		// fees.add(array);
		//
		// Iterator<String[]> iterator = fees.iterator();
		// while (iterator.hasNext()) {
		// String[] feeArray = iterator.next();
		// Map<String, Object> feeMap = new HashMap<>();
		// feeMap.put("serialNum", feeArray[2]);
		// feeMap.put("date", Date.valueOf("2015-1-1"));
		// feeMap.put("fee", feeArray[3]);
		// feeMap.put("payStatus", false);
		// feeDaoImpl.saveOrUpdate(feeArray[0], feeMap);

		// Map<String, Object> feeMap = new HashMap<>();
		// feeMap.put("serialNum", "01010101");
		// feeMap.put("displayName", "水费");
		// feeMap.put("date", Date.valueOf("2015-1-1"));
		// feeMap.put("fee", 150.0);
		// feeMap.put("payStatus", false);
		// feeDaoImpl.saveOrUpdate("water_fee", feeMap);
		// }
	}
}
