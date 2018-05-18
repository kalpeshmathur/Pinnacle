package com.example.dell.pinnacle.models;

import com.google.gson.annotations.SerializedName;


public class ForgotPasswordModel{

	@SerializedName("success")
	private int success;

	@SerializedName("detail")
	private ForgotPasswordDetails detail;

	@SerializedName("message")
	private String message;

	public void setSuccess(int success){
		this.success = success;
	}

	public int getSuccess(){
		return success;
	}

	public void setDetail(ForgotPasswordDetails detail){
		this.detail = detail;
	}

	public ForgotPasswordDetails getDetail(){
		return detail;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}
}