package com.sun.model;

import com.sun.utils.MD5Util;

public class User {
	private int userId;
	private String email;
	private String password;
	private String validateCode;
	private String userName;
	private int schoolId;
	private String schoolName;
	private int roleId;
	private String roleName;
	private int gender;// 1-male and 0-female
	private int status;// 1-active and 0-not
	private String selfInfo;
	private String expectedInfo;
	private String photoAddress;
	private boolean whetherLiking;
	private String studentId;
	private int graduateType;//	研究生类型：1-本校推免	2-外校推免	3-本校考研	4-外校考研



	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public int getGraduateType() {
		return graduateType;
	}

	public void setGraduateType(int graduateType) {
		this.graduateType = graduateType;
	}

	public boolean isWhetherLiking() {
		return whetherLiking;
	}

	public void setWhetherLiking(boolean whetherLiking) {
		this.whetherLiking = whetherLiking;
	}

	public String getPhotoAddress() {
		return photoAddress;
	}

	public void setPhotoAddress(String photoAddress) {
		this.photoAddress = photoAddress;
	}

	public String getSelfInfo() {
		return selfInfo;
	}

	public void setSelfInfo(String selfInfo) {
		this.selfInfo = selfInfo;
	}

	public String getExpectedInfo() {
		return expectedInfo;
	}

	public void setExpectedInfo(String expectedInfo) {
		this.expectedInfo = expectedInfo;
	}

	/**
	 * 1-active and 0-not
	 * 
	 * @return
	 */
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public int getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getValidateCode() {
		return validateCode;
	}

	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = MD5Util.string2MD5(password);
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
