package com.karan.todo.model;

public class ResponseObject {
	public int errorCode;
	public String error;
	public Object data;

	public ResponseObject(int errorCode, String error, Object data) {
		super();
		this.errorCode = errorCode;
		this.error = error;
		this.data = data;
	}

	public ResponseObject(int errorCode, String error) {
		super();
		this.errorCode = errorCode;
		this.error = error;
	}

	public ResponseObject(Object data) {
		super();
		this.data = data;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
