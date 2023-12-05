package com.hbu.models;

/**
 * 
 * @author 杨建兴
 *2020-03-24
 */
/**
 * 用户登录
userNumber		类型：字符串 学号
userPassword	类型：字符串 密码

 *
 */
public class User {

	private String username;
	private String userPassword;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", userPassword=" + userPassword + "]";
	}
	
}
