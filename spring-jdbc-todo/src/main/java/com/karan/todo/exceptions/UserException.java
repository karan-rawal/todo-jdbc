package com.karan.todo.exceptions;

public class UserException extends RuntimeException {
	private static final long serialVersionUID = 1624943833503353522L;
	public static final String USER_EXISTS = "User with the given email address already exists";
	public static final int CODE_USER_EXISTS = 1000;
	public static final String PASSWORD_MISSMATCH = "Passwords do not match";
	public static final int CODE_PASSWORD_MISSMATCH = 1001;
	public static final String USER_NOT_FOUND = "User not found";
	public static final int CODE_USER_NOT_FOUND = 1002;

	private int errorCode;
	private Object data;

	public UserException(String message, int errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public UserException(String message, int errorCode, Object data) {
		super(message);
		this.errorCode = errorCode;
		this.data = data;
	}

	public Object getData() {
		return data;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

}
