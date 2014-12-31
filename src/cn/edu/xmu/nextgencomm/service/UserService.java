package cn.edu.xmu.nextgencomm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import cn.edu.xmu.nextgencomm.bean.UserBean;
import cn.edu.xmu.nextgencomm.dao.UserDao;
import cn.edu.xmu.nextgencomm.model.User;

@Service("userService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserService {
	@Autowired
	private UserDao userDao;

	public User getUser(UserBean userBean) {
		User u = userDao.getUser(userBean);
		if (userBean.getPassword() == null
				|| !u.getPassword().equals(userBean.getPassword())) {

			return null;
		}
		return u;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void saveUser(User user) {
		userDao.saveUser(user);
	}

}
