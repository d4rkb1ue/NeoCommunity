package cn.edu.xmu.nextgencomm.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.edu.xmu.nextgencomm.model.Test;


@Repository("test1Dao")  
public class Test1DAO {
	@Autowired  
	private SessionFactory sessionFactory;  
	
	public void saveUser(Test user) {
		sessionFactory.getCurrentSession().saveOrUpdate(user);
	}

	
}
