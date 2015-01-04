package cn.edu.xmu.nextgencomm.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import cn.edu.xmu.nextgencomm.bean.ShareResultBean;
import cn.edu.xmu.nextgencomm.util.CalculatorFactory;
import cn.edu.xmu.nextgencomm.util.ParseUtil;
import cn.edu.xmu.nextgencomm.util.ShareCalculatorUtil;

public class ShareCalculateService {
	Map<String, Object> house;
	List<Map<String, Object>> building;
	Map<String, Double> meters;
	Map<Integer, ShareCalculatorUtil> shareCalculatorMap;
	ShareCalculatorUtil shareCalculator;
	ParseUtil parse;

	List<ShareResultBean> finalResult = new ArrayList<ShareResultBean>();

	// public static void main(String[] args) {
	// // launch share
	// System.out.println("----Share------");
	// ShareCalculateService shareCalculateService = new
	// ShareCalculateService();
	// shareCalculateService.exeCalculateShare();
	//
	// // launch private
	// // CalculateService calculateService=new CalculateService();
	// // calculateService.exeCalculatePrivate();
	// }

	public List<ShareResultBean> exeCalculateShare(
			List<Map<String, Object>> building, Map<String, Double> meters) {
		this.building = building;
		this.meters = meters;
		// create 只需运行一次
		createCalculators();

		// getList();
		getMoreInfo();
		exe();
		// 结果是finalResult
		for (ShareResultBean st : finalResult) {
			System.err.println(st.toString());
		}
		return finalResult;
	}

	private void createCalculators() {
		parse = new ParseUtil();
		parse.parse();
		CalculatorFactory calculatorFactory = new CalculatorFactory();
		shareCalculatorMap = new HashMap<Integer, ShareCalculatorUtil>();
		// System.err.println(parse.getShareCalculators().toString());

		for (Map<String, Object> plain : parse.getShareCalculators()) {
			// System.err.println(plain.toString());
			if (((String) plain.get(ShareCalculatorUtil.SHARE_STRATEGY))
					.equals(ShareCalculatorUtil.SHARE_STRATEGY_BUILDING)) {
				shareCalculator = calculatorFactory.createBuilingCalculator(
						Integer.parseInt((String) plain.get("meter-id")),
						(String) plain.get("name"), (String) plain
								.get("display-name"),
						((String) plain.get("area-weight")).toLowerCase()
								.equals("true"), plain
								.containsKey(ShareCalculatorUtil.WEIGHT_FLOOR),
						(Map<Integer, Double>) plain
								.get(ShareCalculatorUtil.WEIGHT_FLOOR),
						(String) plain.get("calulate-style"), plain
								.get((String) plain.get("calulate-style")));
			} else if (((String) plain.get(ShareCalculatorUtil.SHARE_STRATEGY))
					.equals(ShareCalculatorUtil.SHARE_STRATEGY_FLOOR)) {

				shareCalculator = calculatorFactory.createFloorCalculator(
						Integer.parseInt((String) plain.get("meter-id")),
						(String) plain.get("name"), (String) plain
								.get("display-name"),
						((String) plain.get("area-weight")).toLowerCase()
								.equals("true"), (String) plain
								.get("calulate-style"), plain
								.get((String) plain.get("calulate-style")));
			}
			if (shareCalculator != null) {

				shareCalculatorMap.put(shareCalculator.getMeterId(),
						shareCalculator);
			}
		}

	}

	public void getList() {
		/**
		 * 接口说明： 需要2个表。
		 * 
		 * 1.本楼的公共设施的各种表的读数 结构：hashmap<String:表编号,double:usage> 表编号结构： 00 11 22
		 * 33 "00"是表的种类。下面的例子中01代表了电梯电用量 02代表了公共照明电用量 “11”是楼号 “22”是层号 “33”是表编号
		 * 
		 * 2.本楼的居民信息 结构hashmap<String,String> 必须包含信息：
		 * room-id，water-usage，electric-usage，area
		 * 注意值的类型都是String，calculator会自行解析
		 * 
		 */
		building = new ArrayList<Map<String, Object>>();
		// 实例数据，2个表，一个是全部的“公共设施的电表或者水表”表（meters）；另一个是一栋楼所有居民的信息。
		// elevator Building 1 No.1,2
		// 01 02 的头代表了是哪里的电表，与xml的定义相符（meter-id）只含本栋楼的表
		meters = new HashMap<String, Double>();
		meters.put("010115.001", 54.5);
		meters.put("010115.002", 55.9);
		// public light Building 1 No.1,2
		meters.put("02010101", 15.0);
		meters.put("02010202", 70.0);
		// 人不在不收费
		house = new HashMap<String, Object>();
		house.put("room-id", "00010101");
		house.put("water-usage", 115.0);
		house.put("electric-usage", 115.0);
		house.put("area", 115.0);
		building.add(house);
		house = new HashMap<String, Object>();
		house.put("room-id", "00010102");
		house.put("water-usage", 115.0);
		house.put("electric-usage", 115.0);
		house.put("area", 115.0);
		building.add(house);
		house = new HashMap<String, Object>();
		house.put("room-id", "00010201");
		house.put("water-usage", 115.0);
		house.put("electric-usage", 115.0);
		house.put("area", 115.0);
		building.add(house);
		house = new HashMap<String, Object>();
		house.put("room-id", "00010202");
		house.put("water-usage", 115.0);
		house.put("electric-usage", 115.0);
		house.put("area", 115.0);
		building.add(house);
		house = new HashMap<String, Object>();
		house.put("room-id", "00010301");
		house.put("water-usage", 115.0);
		house.put("electric-usage", 115.0);
		house.put("area", 115.0);
		building.add(house);
		house = new HashMap<String, Object>();
		house.put("room-id", "00010302");
		house.put("water-usage", 115.0);
		house.put("electric-usage", 115.0);
		house.put("area", 115.0);
		building.add(house);

	}

	public void getMoreInfo() {
		// floor
		if (building == null)
			return;
		Map<String, Object> map;
		Iterator<Map<String, Object>> iterator = building.iterator();
		while (iterator.hasNext()) {
			map = iterator.next();

			map.put("floor", ((String) map.get("room-id")).substring(4, 6));
			if ((double) (map.get("water-usage")) < parse.getEmptyRoomWater()
					&& (double) (map.get("electric-usage")) < parse
							.getEmptyRoomElectric()) {
				// 空屋，移除收费列表
				iterator.remove();
			}
		}
	}

	public int getMeterId(String s) {
		return Integer.parseInt(s.substring(0, 2));
	}

	public int getMeterFloor(String s) {
		// System.err.println(s+"  ="+Integer.parseInt(s.substring(4, 6)));
		return Integer.parseInt(s.substring(4, 6));
	}

	void addToResult(String name, String dis, Map<String, Double> oneResult) {
		// TODO
		ShareResultBean shareResult = new ShareResultBean(oneResult, dis, name);
		finalResult.add(shareResult);
		System.err.println(name + "  " + oneResult.toString());
		// for (Entry<String, Double> aRoomAResult : oneResult.entrySet()) {
		// String roomId=aRoomAResult.getKey();
		// double aResult=aRoomAResult.getValue();
		// for (Entry<String, Double> aRoomResult : result.entrySet()) {
		// if(aRoomResult.getKey().equals(roomId)){
		// double oldV=aRoomResult.getValue();
		// double newV=oldV+aResult;
		// aRoomResult.setValue(newV);
		// break;
		// }
		// }
		// }
	}

	private void exe() {
		int meterFloor = 0;
		ShareCalculatorUtil fitCalculator;
		Map<String, Double> oneResult;
		List<Map<String, Object>> whoShareFee;

		for (Entry<String, Double> meter : meters.entrySet()) {
			int meterId = getMeterId(meter.getKey());
			double meterUsage = meter.getValue();
			oneResult = new HashMap<String, Double>();
			fitCalculator = shareCalculatorMap.get(meterId);
			whoShareFee = new ArrayList<Map<String, Object>>();
			if (fitCalculator.getShareStrategy().equals(
					ShareCalculatorUtil.SHARE_STRATEGY_FLOOR)) {
				meterFloor = getMeterFloor(meter.getKey());
				for (Map<String, Object> map : building) {
					if (Integer.parseInt((String) (map.get("floor"))) == meterFloor) {
						whoShareFee.add(map);

					}// end floor if
				}// end building for
				oneResult = fitCalculator.calculate(whoShareFee, meterUsage);
			}// end floor strategy if
			else if (fitCalculator.getShareStrategy().equals(
					ShareCalculatorUtil.SHARE_STRATEGY_BUILDING)) {
				oneResult = fitCalculator.calculate(building, meterUsage);
			}// end building st
			addToResult(fitCalculator.getName(),
					fitCalculator.getDisplayName(), oneResult);
		}// end meters for
	}

}
