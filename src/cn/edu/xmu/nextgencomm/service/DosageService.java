package cn.edu.xmu.nextgencomm.service;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.xmu.nextgencomm.dao.impl.DosageDaoImpl;
import cn.edu.xmu.nextgencomm.dao.impl.HouseDaoImpl;
import cn.edu.xmu.nextgencomm.model.Dosage;

/**
 * ���ˮ����������
 * 
 * @author Luyahui
 *
 */
@Repository("dosageService")
@Transactional
public class DosageService {
	@Autowired
	DosageDaoImpl dosageDaoImpl;
	@Autowired
	HouseDaoImpl houseDaoImpl;

	/**
	 * �����ݸ�ʽ������λ��ǰ���������0
	 * 
	 * @param num
	 * @return
	 */
	private String format(String str) {
		String result = "";
		int num = Integer.parseInt(str);
		if (num < 10) {
			result = "0" + String.valueOf(num);
		} else {
			result = String.valueOf(num);
		}
		return result;
	}

	public void addDosage(String buildingID, String floorID, String roomID,
			String waterDosage, String electricityDosage, String date) {
		if (!roomID.equals("0")) {
			// ���յ���������ҵ�����������
			Dosage dosage = new Dosage();
			String serialNum = "01" + format(buildingID) + format(floorID)
					+ format(roomID);
			dosage.setSerialNum(serialNum);
			dosage.setWaterDosage(Double.parseDouble(waterDosage));
			dosage.setElectricityDosage(Double.parseDouble(electricityDosage));
			dosage.setDate(Date.valueOf(date));
			dosage.setHouse(houseDaoImpl.get(serialNum));
			dosageDaoImpl.saveOrUpdate(dosage);
		} else if (!floorID.equals("0")) {
			// ���յ��������ǵ��㹫̯������
			Dosage dosage = new Dosage();
			dosage.setSerialNum("00" + format(buildingID) + format(floorID)
					+ "00");
			dosage.setWaterDosage(Double.parseDouble(waterDosage));
			dosage.setElectricityDosage(Double.parseDouble(electricityDosage));
			dosageDaoImpl.saveOrUpdate(dosage);
		} else {
			// ���յ�������������¥��̯������
			Dosage dosage = new Dosage();
			dosage.setSerialNum("00" + format(buildingID) + "0000");
			dosage.setWaterDosage(Double.parseDouble(waterDosage));
			dosage.setElectricityDosage(Double.parseDouble(electricityDosage));
			dosage.setDate(Date.valueOf(date));
			dosageDaoImpl.saveOrUpdate(dosage);
		}
	}
}
