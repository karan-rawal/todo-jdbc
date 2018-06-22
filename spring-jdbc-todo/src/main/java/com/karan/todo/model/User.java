package com.karan.todo.model;

public class User {
	private Integer id;
	private String fname;
	private String lname;
	private String email;
	private String password;
	private String confirmPassword;
	
	public String getConfirmPassword() {
		return confirmPassword;
	}

	public String getEmail() {
		return email;
	}

	public String getFname() {
		return fname;
	}

	public Integer getId() {
		return id;
	}

	public String getLname() {
		return lname;
	}

	public String getPassword() {
		return password;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", fname=" + fname + ", lname=" + lname + ", email=" + email + ", password="
				+ password + "]";
	}

}
