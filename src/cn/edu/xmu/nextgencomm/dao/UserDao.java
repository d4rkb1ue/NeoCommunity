package cn.edu.xmu.nextgencomm.dao;

import org.springframework.stereotype.Repository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.edu.xmu.nextgencomm.bean.UserBean;
import cn.edu.xmu.nextgencomm.model.User;

@Repository("userDao")
public class UserDao {
	@Autowired
	private SessionFactory sessionFactory;

	public User getUser(UserBean userBean) {
		Session session = sessionFactory.getCurrentSession();

		String qu = "from User u where username = ? and password = ?";
		User user = (User) session.createQuery(qu)
				.setString(0, userBean.getUsername())
				.setString(1, userBean.getPassword()).uniqueResult();
		return user;
	}

	public void saveUser(User user) {
		sessionFactory.getCurrentSession().saveOrUpdate(user);
	}
}
