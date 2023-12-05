package com.hbu.models;

public class Result <T>{

	private String code = "000000"; //FIXED ME 这里优化为枚举
	
	private String message = "success"; //FIXED ME 这也需要优化为枚举类型
	
	private T data;


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Result{" +
				"code='" + code + '\'' +
				", message='" + message + '\'' +
				", data=" + data +
				'}';
	}
}
