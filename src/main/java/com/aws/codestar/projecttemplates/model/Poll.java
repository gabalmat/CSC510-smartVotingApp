package com.aws.codestar.projecttemplates.model;

public class Poll {
	
	private String title;
	private String description;
	private int userId;
	private int categoryId;
	
	public Poll() {}
	
	public Poll(String title, String description, int userId, int categoryId) {
		this.title = title;
		this.description = description;
		this.userId = userId;
		this.categoryId = categoryId;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	
	
}
