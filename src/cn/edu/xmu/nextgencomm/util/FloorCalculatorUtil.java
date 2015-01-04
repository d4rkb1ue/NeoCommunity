package cn.edu.xmu.nextgencomm.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class FloorCalculatorUtil extends ShareCalculatorUtil {


	public FloorCalculatorUtil(int meterId, String name, String displayName,
			boolean isAreaWeight, String calulateStyle, Object method) {
		this.meterId = meterId;
		this.name = name;
		this.displayName = displayName;
		this.shareStrategy = SHARE_STRATEGY_FLOOR;
		this.isAreaWeight = isAreaWeight;
		this.calulateStyle = calulateStyle;
		this.method = method;
	}

	@Override
	public Map<String, Double> calculate(List<Map<String, Object>> houses,
			double usage) {
		// TODO Auto-generated method stub
		System.err.println(houses.toString());
		result = new HashMap<String, Double>();
		logicArea = new HashMap<String, Double>();
		// calculate each logic Area
		/*
		 * house.put("room-id", "01010101"); house.put("water-usage", "1");
		 * house.put("electric-usage", "5"); house.put("area", "100.9");
		 */
		calculateTotalFee(usage);
		if (isAreaWeight) {
			double sum = 0;
			for (Map<String, Object> map : houses) {
				double areaOfRoom = (double)(map.get("area"));
				logicArea.put((String) map.get("room-id"), areaOfRoom);
				sum += areaOfRoom;
			}
			for (Entry<String, Double> aLogicArea : logicArea.entrySet()) {
				double shareOfRoom = aLogicArea.getValue() / sum * totalFee;
				double round = (double) (Math.round(shareOfRoom * 100) / 100.0);
				result.put(aLogicArea.getKey(), round);
			}
		}// area Weight
		else {
			int sum = houses.size();
			for (Map<String, Object> map : houses) {
				double shareOfRoom = 1.0 / sum * totalFee;
				double round = (double) (Math.round(shareOfRoom * 100) / 100.0);
				result.put((String) map.get("room-id"), round);
			}
		}
		// sum=?
		// calculate %
		// multi with total
		// put into result
		return result;

	}

}
