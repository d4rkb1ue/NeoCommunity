package cn.edu.xmu.nextgencomm.model;

public class User {
	/** 主键 **/
	private long id;
	/** 用户名 **/
	private String username;
	/** 密码 **/
	private String password;
	/** 姓名 **/
	private String name;
	/** 性别 **/
	/** 身份证号 **/
	private String idCard;
	/** 联系电话 **/
	private String phone;
	/** 邮箱地址 **/
	private String email;
	/** 用户分组 **/
	private String usergroup;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsergroup() {
		return usergroup;
	}

	public void setUsergroup(String usergroup) {
		this.usergroup = usergroup;
	}

}
