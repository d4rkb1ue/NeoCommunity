package cn.edu.xmu.nextgencomm.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class BuildingCalculatorUtil extends ShareCalculatorUtil {

	boolean isFloorWeight;
	Map<Integer, Double> floorWeight;

	public BuildingCalculatorUtil(int meterId, String name, String displayName,
			boolean isAreaWeight, boolean isFloorWeight,
			Map<Integer, Double> floorWeight,String calulateStyle, Object method) {
		this.meterId = meterId;
		this.name = name;
		this.displayName = displayName;
		this.shareStrategy = SHARE_STRATEGY_BUILDING;
		this.isAreaWeight = isAreaWeight;
		this.isFloorWeight = isAreaWeight;
		this.floorWeight = floorWeight;
		this.calulateStyle=calulateStyle;
		this.method=method;
		//System.err.println(calulateStyle);
		// System.err.println(floorWeight.toString());
		// System.err.println("new building calculator");
	}

	@Override
	public Map<String, Double> calculate(List<Map<String, Object>> houses,
			double usage) {
		// TODO Auto-generated method stub
		// System.err.println(houses.toString());
		result = new HashMap<String, Double>();
		logicArea = new HashMap<String, Double>();
		double sum = 0.0;
		/*
		 * house.put("room-id", "01010101"); house.put("water-usage", "1");
		 * house.put("electric-usage", "5"); house.put("area", "100.9");
		 */
		for (Map<String, Object> map : houses) {
			String roomId = (String)(map.get("room-id"));
			double realArea;
			if (isAreaWeight) {
				double aFloorWeight;
				realArea = (double)map.get("area");
				if (isFloorWeight) {
					int aFloor = Integer.parseInt((String) (map.get("floor")));
					if (!floorWeight.containsKey(aFloor)) {
						//System.err.println("do not contain "+aFloor);
						aFloorWeight = 0;
					} else
						aFloorWeight = floorWeight.get(aFloor);
					logicArea.put(roomId, aFloorWeight * realArea);
					sum += aFloorWeight * realArea;
				} else {
					logicArea.put(roomId, realArea);
					sum += realArea;
				}
			}// end is area weight
			else {
				realArea = 1.0;
				if (isFloorWeight) {

					double aFloorWeight = floorWeight.get((int)(map
							.get("floor")));
					logicArea.put(roomId, aFloorWeight * realArea);
					sum += aFloorWeight * realArea;
				} else {
					logicArea.put(roomId, realArea);
					sum += realArea;
				}

			}// end is not area weight
		}
		calculateTotalFee(usage);
		for (Entry<String, Double> aRoom : logicArea.entrySet()) {
			String roomId = aRoom.getKey();
			double aResult = aRoom.getValue() / sum * totalFee;
			double round = (double)(Math.round(aResult*100)/100.0);
			if(aResult>0.0)
				result.put(roomId, round);
		}
		// calculate %
		// multi with total
		// put into result
		return result;
	}

}
