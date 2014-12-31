package cn.edu.xmu.nextgencomm.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

import cn.edu.xmu.nextgencomm.model.Test;
import cn.edu.xmu.nextgencomm.service.TestService;

public class TestAction extends ActionSupport{
	//test!!!!
		@Autowired
		private TestService testService;
		public String test1() {
			System.err.println("-----------test------------------");
			Test user=new Test();
			user.setUserName("dd");
			user.setUserGroup("users");
			user.setPassWord("123");
			testService.saveUser(user);
			return "fail";
		}
}
