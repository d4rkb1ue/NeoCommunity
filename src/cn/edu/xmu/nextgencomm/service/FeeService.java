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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.xmu.nextgencomm.bean.ShareResultBean;
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

	/** �û�˽�˵�ˮ��������Ϣ **/
	private List<Map<String, Object>> privateDosages = new ArrayList<>();

	/** һ��¥������ˮ��������Ϣ **/
	private Map<String, Double> buildingDosages = new HashMap<String, Double>();

	/** ��ȡ����¥��ˮ��������Ϣ��ƥ���ַ��� **/
	private String getBuildString(int num) {
		String result = "";
		if (num < 10) {
			result = "__0" + String.valueOf(num) + "%";
		} else {
			result = "__" + String.valueOf(num) + "%";
		}
		return result;
	}

	public void calculate(Date date) {
		int buildingNum = 1;
		for (int i = 0; i < buildingNum; i++) {
			privateDosages.clear();
			buildingDosages.clear();
			// ������������
			System.out.println(date.toString() + getBuildString(i + 1));
			List<Dosage> currentDosages = dosageDaoImpl.get(date,
					getBuildString(i + 1));
			if (!currentDosages.iterator().hasNext()) {
				System.out.println("null!");
			}
			// ������������
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.MONTH, -1);
			Date preDate = new Date(calendar.getTime().getTime());
			System.out.println(preDate.toString() + getBuildString(i + 1));
			List<Dosage> preDosages = dosageDaoImpl.get(preDate,
					getBuildString(i + 1));
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
				if (currentDosage.getSerialNum().startsWith("00")) {
					// �����û������ݷ���Map��
					HashMap<String, Object> dMap = new HashMap<String, Object>();
					dMap.put("roon-id", currentDosage.getSerialNum());
					dMap.put("water-usage", currentDosage.getWaterDosage()
							- preDosage.getWaterDosage());
					dMap.put(
							"electric-usage",
							currentDosage.getElectricityDosage()
									- preDosage.getElectricityDosage());
					// �����û�˽�˷���
					dMap.put("area", currentDosage.getHouse().getArea());
					Map<String, Integer> parking = new HashMap<String, Integer>();
					dMap.put("parking-port", parking);
					Map<String, Double> results = new CalculateService()
							.exeCalculatePrivate(dMap);
					Iterator<String> keyIterator = results.keySet().iterator();
					while (keyIterator.hasNext()) {
						Map<String, Object> feeMap = new HashMap<String, Object>();
						String key = keyIterator.next();
						feeMap.put("serialNum", currentDosage.getSerialNum());
						feeMap.put("date", date);
						feeMap.put("fee", results.get(key));
						feeMap.put("payStatus", false);
						System.out.println(feeMap.toString());
						feeDaoImpl.saveOrUpdate(key, feeMap);
					}
				} else {
					// �����ݷ���Map��
					buildingDosages.put(
							currentDosage.getSerialNum(),
							currentDosage.getElectricityDosage()
									- preDosage.getElectricityDosage());
				}
			}
			System.out.println(privateDosages.toString());
			System.out.println(buildingDosages.toString());
			// �����ݴ���ShareCalculatorService��
			List<ShareResultBean> shareFees = new ShareCalculateService()
					.exeCalculateShare(privateDosages, buildingDosages);
			System.out.println(shareFees.toString());
			Iterator<ShareResultBean> feeIterator = shareFees.iterator();
			while (feeIterator.hasNext()) {
				ShareResultBean shareResultBean = feeIterator.next();
				Map<String, Double> resultMap = shareResultBean.getResult();
				Iterator<String> resultMapKeyIterator = resultMap.keySet()
						.iterator();
				while (resultMapKeyIterator.hasNext()) {
					String resultMapKey = resultMapKeyIterator.next();
					Map<String, Object> feeMap = new HashMap<String, Object>();
					feeMap.put("serialNum", resultMapKey);
					feeMap.put("date", date);
					feeMap.put("fee", resultMap.get(resultMapKey));
					feeMap.put("payStatus", false);
					feeDaoImpl.saveOrUpdate(shareResultBean.getName(), feeMap);
				}
			}
		}
	}

	public void test() {
		String[] array = { "water_fee", "ˮ��", "01010101", "15" };
		System.out.println(array.toString());
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
