package com.example.dell.pinnacle.models;

import com.google.gson.annotations.SerializedName;


public class BlogDetails {

	@SerializedName("id")
	private String id;

	@SerializedName("blog")
	private String blog;

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setBlog(String blog){
		this.blog = blog;
	}

	public String getBlog(){
		return blog;
	}
}