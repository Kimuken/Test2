package jp.co.axiz.web.entity;

public class User {
	private Integer userId;
	private String userName;
	private String telephone;
	private String password;

	public User() {

	}

	public User(Integer id) {
		this.userId = id;
	}

	public User(int id, String userName, String telephone, String password) {
		this.userId = id;
		this.userName = userName;
		this.telephone = telephone;
		this.password = password;
	}

	public User(String name, String tel, String pass) {
		this.userName = name;
		this.telephone = tel;
		this.password = pass;
	}

	public User(Integer id, String name, String telephone) {
		this.userId = id;
		this.userName = name;
		this.telephone = telephone;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
