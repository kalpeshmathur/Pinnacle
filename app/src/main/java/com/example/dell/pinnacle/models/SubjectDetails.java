package com.example.dell.pinnacle.models;

import com.google.gson.annotations.SerializedName;


public class SubjectDetails {

	@SerializedName("dececription")
	private String dececription;

	@SerializedName("subject_name")
	private String subjectName;

	@SerializedName("days")
	private String days;

	@SerializedName("language")
	private String language;

	@SerializedName("type")
	private String type;

	@SerializedName("title")
	private String title;

	@SerializedName("url")
	private String url;

	public void setDececription(String dececription){
		this.dececription = dececription;
	}

	public String getDececription(){
		return dececription;
	}

	public void setSubjectName(String subjectName){
		this.subjectName = subjectName;
	}

	public String getSubjectName(){
		return subjectName;
	}

	public void setDays(String days){
		this.days = days;
	}

	public String getDays(){
		return days;
	}

	public void setLanguage(String language){
		this.language = language;
	}

	public String getLanguage(){
		return language;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}
}