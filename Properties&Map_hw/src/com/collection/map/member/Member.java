package com.collection.map.member;

import java.util.Objects;


public class Member {
	private String userId;		// 유저 ID
	private String userPwd;		// 유저 Password
	private String userName;	// 유저 이름
	private int age;			// 유저 나이
	private String phoneNumber;	// 유저 폰번호
	
	public Member() {
	}
	public Member(String userId, String userPwd, String userName, int age, String phoneNumber) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.age = age;
		this.phoneNumber = phoneNumber;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	//이건 조건에 없지만 내가 출력하기 위해서..
	@Override
	public String toString() {
		return "Member [userId=" + userId + ", userPwd=" + userPwd + ", userName=" + userName + ", age=" + age
				+ ", phoneNumber=" + phoneNumber + "]";
	}
	

	
}
