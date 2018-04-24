package com.aws.codestar.projecttemplates.model;

public class Category {

	private int userId;
	private String name;
	private String description;
	private int id;
	
	public Category() {}
	
	public Category(String name, String description, int userId) {
		this.setName(name);
		this.setDescription(description);
		this.setUserId(userId);
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
