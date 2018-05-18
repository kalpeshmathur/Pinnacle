package com.example.dell.pinnacle.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class BlogModel{

	@SerializedName("blog")
	private List<BlogDetails> blog;

	public void setBlog(List<BlogDetails> blog){
		this.blog = blog;
	}

	public List<BlogDetails> getBlog(){
		return blog;
	}
}