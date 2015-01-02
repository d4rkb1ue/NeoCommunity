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
import cn.edu.xmu.nextgencomm.model.Fee;

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

	/** 所有水电用量信息 **/
	private List<Dosage> dosages;
	/** 所有费用信息 **/
	private List<Fee> fees = new ArrayList<Fee>();

	public void calculate(Date date) {
		// 获取本月所有记录总数
		int allCount = dosageDaoImpl.getCount(date);
		for (int i = 0; allCount > 0; i++, allCount -= 20) {
			fees.clear();
			dosages = dosageDaoImpl.get(date, i * 20, 20);
			Iterator<Dosage> iterator = dosages.iterator();
			while (iterator.hasNext()) {
				// 得到本月用量读数
				Dosage currentDosage = iterator.next();
				// 得到上月用量读数
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				calendar.add(Calendar.MONTH, -1);
				Date preDate = new Date(calendar.getTime().getTime());
				Dosage preDosage = dosageDaoImpl.get(
						currentDosage.getSerialNum(), preDate);
				// 计算本月用量数据
				double waterDosage = currentDosage.getWaterDosage()
						- preDosage.getWaterDosage();
				double electricityDosage = currentDosage.getElectricityDosage()
						- preDosage.getElectricityDosage();
				double area = currentDosage.getHouse().getArea();
				// 调用计算器计算费用
				Map<String, Object> dosageMap = new HashMap<String, Object>();
				Map<String, Integer> parking = new HashMap<String, Integer>();
				dosageMap.put("water-usage", waterDosage);
				dosageMap.put("electric-usage", electricityDosage);
				dosageMap.put("area", area);
				dosageMap.put("parking-port", parking);
				System.out.println(dosageMap.toString());
				Map<String, Double> feeMap = new CalculateService()
						.getResult((HashMap<String, Object>) dosageMap);
				System.out.println(feeMap.toString());
				// 保存至数据库
				Fee fee = new Fee();
				fee.setSerialNum(currentDosage.getSerialNum());
				fee.setDate(date);
				fee.setWasteFee(feeMap.get("水费"));
				fee.setElectricityFee(feeMap.get("电费"));
				fee.setWasteFee(feeMap.get("垃圾处理费"));
				fee.setCarportFee(feeMap.get("车位费"));
				fee.setPropertyFee(feeMap.get("物业费"));
				fee.setHouse(currentDosage.getHouse());
				fees.add(fee);
			}
			feeDaoImpl.saveOrUpdate(fees);
		}
	}
}
