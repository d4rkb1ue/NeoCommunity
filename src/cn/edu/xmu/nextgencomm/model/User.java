package cn.edu.xmu.nextgencomm.model;

public class User {
	/** ���� **/
	private long id;
	/** �û��� **/
	private String username;
	/** ���� **/
	private String password;
	/** ���� **/
	private String name;
	/** �Ա� **/
	/** ���֤�� **/
	private String idCard;
	/** ��ϵ�绰 **/
	private String phone;
	/** �����ַ **/
	private String email;
	/** �û����� **/
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
