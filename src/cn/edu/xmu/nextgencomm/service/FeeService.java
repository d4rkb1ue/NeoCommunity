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

	/** ����ˮ��������Ϣ **/
	private List<Dosage> dosages;
	/** ���з�����Ϣ **/
	private List<Fee> fees = new ArrayList<Fee>();

	public void calculate(Date date) {
		// ��ȡ�������м�¼����
		int allCount = dosageDaoImpl.getCount(date);
		for (int i = 0; allCount > 0; i++, allCount -= 20) {
			fees.clear();
			dosages = dosageDaoImpl.get(date, i * 20, 20);
			Iterator<Dosage> iterator = dosages.iterator();
			while (iterator.hasNext()) {
				// �õ�������������
				Dosage currentDosage = iterator.next();
				// �õ�������������
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				calendar.add(Calendar.MONTH, -1);
				Date preDate = new Date(calendar.getTime().getTime());
				Dosage preDosage = dosageDaoImpl.get(
						currentDosage.getSerialNum(), preDate);
				// ���㱾����������
				double waterDosage = currentDosage.getWaterDosage()
						- preDosage.getWaterDosage();
				double electricityDosage = currentDosage.getElectricityDosage()
						- preDosage.getElectricityDosage();
				double area = currentDosage.getHouse().getArea();
				// ���ü������������
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
				// ���������ݿ�
				Fee fee = new Fee();
				fee.setSerialNum(currentDosage.getSerialNum());
				fee.setDate(date);
				fee.setWasteFee(feeMap.get("ˮ��"));
				fee.setElectricityFee(feeMap.get("���"));
				fee.setWasteFee(feeMap.get("���������"));
				fee.setCarportFee(feeMap.get("��λ��"));
				fee.setPropertyFee(feeMap.get("��ҵ��"));
				fee.setHouse(currentDosage.getHouse());
				fees.add(fee);
			}
			feeDaoImpl.saveOrUpdate(fees);
		}
	}
}
