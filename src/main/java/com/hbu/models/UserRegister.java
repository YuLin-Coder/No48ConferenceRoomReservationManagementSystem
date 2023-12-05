package com.hbu.models;

/**
 * 用户注册
 * @author jensin
 *2020-03-24
 *
 *name		类型：字符串		姓名
userNumber	类型：字符串		学号
departId	类型：int			学院ID
sexId		类型：int			性别ID
birthday	类型：date			生日
userPhone	类型：string		手机号
numberCheck	类型：int			验证码
userCard	类型：string		学生证路径
userName	类型：String		用户昵称
userPassword类型：String		用户密码

 */

public class UserRegister {

	private String name;
	private String userNumber;
	private int departId;
	private int sexId;
	private String birthday;
	private String userPhone;
	private String userCard;
	private String userName;
	private String userPassword;
	private int numberCheck;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserNumber() {
		return userNumber;
	}
	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}
	public int getDepartId() {
		return departId;
	}
	public void setDepartId(int departId) {
		this.departId = departId;
	}
	public int getSexId() {
		return sexId;
	}
	public void setSexId(int sexId) {
		this.sexId = sexId;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserCard() {
		return userCard;
	}
	public void setUserCard(String userCard) {
		this.userCard = userCard;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public int getNumberCheck() {
		return numberCheck;
	}
	public void setNumberCheck(int numberCheck) {
		this.numberCheck = numberCheck;
	}
	@Override
	public String toString() {
		return "UserRegister [name=" + name + ", userNumber=" + userNumber + ", departId=" + departId + ", sexId="
				+ sexId + ", birthday=" + birthday + ", userPhone=" + userPhone + ", userCard=" + userCard
				+ ", userName=" + userName + ", userPassword=" + userPassword + ", numberCheck=" + numberCheck + "]";
	}

	
}
