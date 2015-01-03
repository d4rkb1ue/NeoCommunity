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
 * �����ݿ��ж�ȡ���ݣ���Ʒ����д������ݣ��������������
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

	/** һ��¥��ˮ��������Ϣ **/
	private List<Map<String, Object>> dosages = new ArrayList<>();

	/** һ��¥�ķ�����Ϣ **/
	// private List<Fee> fees = new ArrayList<Fee>();

	/** ��ȡ����¥��ˮ��������Ϣ��ƥ���ַ��� **/
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
			// ������������
			List<Dosage> currentDosages = dosageDaoImpl.get(date,
					getBuildString(i));
			// ������������
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.MONTH, -1);
			Date preDate = new Date(calendar.getTime().getTime());
			List<Dosage> preDosages = dosageDaoImpl.get(preDate,
					getBuildString(i));
			// ���㱾������
			Iterator<Dosage> currentDI = currentDosages.iterator();
			while (currentDI.hasNext()) {
				// �õ�ĳ������ı��¶��������¶���
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
				// �����û������ݷ���Map��
				Map<String, Object> dMap = new HashMap<String, Object>();
				dMap.put("water-usage", currentDosage.getWaterDosage()
						- preDosage.getWaterDosage());
				dMap.put("electric-usage", currentDosage.getElectricityDosage()
						- preDosage.getElectricityDosage());
				dMap.put("area", currentDosage.getHouse().getArea());
				Map<String, Integer> parking = new HashMap<String, Integer>();
				dMap.put("parking-port", parking);
				// ��Map�������List��
				dosages.add(dMap);
			}
			// ��dosages����CalculatorService��
		}
	}

	public void test() {
		String[] array = { "water_fee", "ˮ��", "01010101", "15" };
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
		// feeMap.put("displayName", "ˮ��");
		// feeMap.put("date", Date.valueOf("2015-1-1"));
		// feeMap.put("fee", 150.0);
		// feeMap.put("payStatus", false);
		// feeDaoImpl.saveOrUpdate("water_fee", feeMap);
		// }
	}
}
