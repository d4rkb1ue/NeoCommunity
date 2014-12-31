package cn.edu.xmu.nextgencomm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.xmu.nextgencomm.dao.Test1DAO;
import cn.edu.xmu.nextgencomm.model.Test;



@Service("testService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true) 
public class TestService {

	@Autowired
	private Test1DAO test1Dao;
	

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)  
	public void saveUser(Test user) {
		test1Dao.saveUser(user);
	}

	
}
