package com.aws.codestar.projecttemplates.model;

public class Poll {
	
	private String title;
	private String description;
	private int pollId;
	private int userId;
	private int categoryId;
	private String username;
	private String created;
	private String categoryName;
	
	public Poll() {}
	
	public Poll(String title, String description, int pollId, int userId, int categoryId) {
		this.title = title;
		this.description = description;
		this.pollId = pollId;
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

	public int getPollId() {
		return pollId;
	}

	public void setPollId(int pollId) {
		this.pollId = pollId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	
	public String getUsername() {
	    return username;
	}

	public void setUsername(String username) {
	    this.username = username;
	}
	
    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
	
}
