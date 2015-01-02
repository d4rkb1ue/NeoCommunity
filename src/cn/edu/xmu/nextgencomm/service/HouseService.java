package cn.edu.xmu.nextgencomm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.xmu.nextgencomm.dao.impl.HouseDaoImpl;

@Service("houseService")
@Transactional(propagation = Propagation.SUPPORTS)
public class HouseService {

	@Autowired
	private HouseDaoImpl houseDaoImpl;
}
