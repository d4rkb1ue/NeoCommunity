package cn.edu.xmu.nextgencomm.action;

import org.springframework.beans.factory.annotation.Autowired;

import cn.edu.xmu.nextgencomm.bean.UserBean;
import cn.edu.xmu.nextgencomm.model.User;
import cn.edu.xmu.nextgencomm.service.UserService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class LoginAction extends ActionSupport implements ModelDriven<UserBean> {

	/**
	 * Author :ss
	 */
	@Autowired
	private UserBean userBean;
	@Autowired
	private UserService userService;
	private User user;

	private static final long serialVersionUID = 1L;

	public String execute() {
		System.out.println(userBean.getUsername() + userBean.getPassword());
		user = userService.getUser(userBean);
		if (user != null) {
			if (user.getUsergroup().equals("admin")) {
				return "admin";
			} else if (user.getUsergroup().equals("officer")) {
				return "officer";
			} else if (user.getUsergroup().equals("owner")) {
				return "owner";
			} else if (user.getUsergroup().equals("security")) {
				return "security";
			}
		}
		return "fail";

	}

	@Override
	public UserBean getModel() {
		// TODO Auto-generated method stub
		return userBean;
	}

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
