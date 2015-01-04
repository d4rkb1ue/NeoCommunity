package cn.edu.xmu.nextgencomm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.xmu.nextgencomm.dao.impl.HouseDaoImpl;
import cn.edu.xmu.nextgencomm.model.House;

@Repository
@Transactional
public class HouseService {

	@Autowired
	private HouseDaoImpl houseDaoImpl;

	public List<House> get(String serialNum) {
		List<House> houses = houseDaoImpl.get(serialNum);
		return houses;
	}

	public House get(long id) {
		House house = houseDaoImpl.get(id);
		return house;
	}
}
