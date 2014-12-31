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

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public User getUser(UserBean userBean) {
		User u= userDao.getUser(userBean);
		if(!u.getPassword().equals(userBean.getPassword())){
			//System.err.println("Diff");
			return null;
		}
		return u;
	}

}
