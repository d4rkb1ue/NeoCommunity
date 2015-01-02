package cn.edu.xmu.nextgencomm.dao;

import java.util.List;

import cn.edu.xmu.nextgencomm.model.House;

public interface HouseDao {

	public House get(long id);

	public House get(String serialNum);

	public void saveOrUpadate(House house);

	public void saveOrUpdate(List<House> houses);

	public void delete(long id);

	public void delete(House house);

	public void delete(List<House> houses);
}
