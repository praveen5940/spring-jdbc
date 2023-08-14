package com.jdbc.demo.model;

public class Response {
	
	private int reponseCode;
	private String responseMessage;
	private Object jData;
	private String data;

	public int getReponseCode() {
		return reponseCode;
	}

	public void setReponseCode(int reponseCode) {
		this.reponseCode = reponseCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public Object getjData() {
		return jData;
	}

	public void setjData(Object jData) {
		this.jData = jData;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
