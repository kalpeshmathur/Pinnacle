package com.example.dell.pinnacle.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class SubjectsModel{

	@SerializedName("detail")
	private List<SubjectDetails> detail;

	public void setDetail(List<SubjectDetails> detail){
		this.detail = detail;
	}

	public List<SubjectDetails> getDetail(){
		return detail;
	}
}