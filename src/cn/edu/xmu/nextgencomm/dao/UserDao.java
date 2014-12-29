package cn.edu.xmu.nextgencomm.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.edu.xmu.nextgencomm.bean.UserBean;
import cn.edu.xmu.nextgencomm.model.User;

@Repository("userDao")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true) 
public class UserDao {
	@Autowired  
	private SessionFactory sessionFactory;  
	
	public User getUser(UserBean userBean){
		Session session=sessionFactory.getCurrentSession();
		//Transaction trans=session.beginTransaction();
		String qu="select distinct u from User u where username = :username";
		List users=session.createQuery(qu).setString("username",userBean.getUserName()).list();
		if(users.isEmpty())
			return null;
		User u=(User)users.get(0);
		//System.err.println(u.toString());
		if(!u.getPassword().equals(userBean.getPassword())){
			//System.err.println("Diff");
			return null;
		}
			
		return u;
		
	}
}
