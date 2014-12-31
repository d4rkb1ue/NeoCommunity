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
public class UserDao {
	@Autowired  
	private SessionFactory sessionFactory;  
	
	public User getUser(UserBean userBean){
		Session session=sessionFactory.getCurrentSession();
		
		String qu="select distinct u from User u where username = :username";
		List users=session.createQuery(qu).setString("username",userBean.getUserName()).list();
		if(users.isEmpty())
			return null;
				
		return (User)users.get(0);
		
	}
	public void save(User user) {
		sessionFactory.getCurrentSession().saveOrUpdate(user);
		sessionFactory.getCurrentSession().flush();
	}
}
