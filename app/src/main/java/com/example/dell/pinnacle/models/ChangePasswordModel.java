package com.example.dell.pinnacle.models;

import com.google.gson.annotations.SerializedName;


public class ChangePasswordModel{

	@SerializedName("success")
	private int success;

	@SerializedName("detail")
	private Detail detail;

	@SerializedName("message")
	private String message;

	public void setSuccess(int success){
		this.success = success;
	}

	public int getSuccess(){
		return success;
	}

	public void setDetail(Detail detail){
		this.detail = detail;
	}

	public Detail getDetail(){
		return detail;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}
}