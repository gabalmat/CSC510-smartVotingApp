package com.aws.codestar.projecttemplates.model;

public class Poll {
	
	private String title;
	private String description;
	private int userId;
	
	public Poll() {}
	
	public Poll(String title, String description, int userId) {
		this.title = title;
		this.description = description;
		this.setUserId(userId);
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
	
	
}
