package com.aws.codestar.projecttemplates.model;

public class Comment{

    private int userId;
    private int pollId;
    private int parentId;
    private int id;
    private String content;
    private String created;
    private String username;

    public Comment() {}

    public Comment(int userId, int pollId, int parentId, int id, String content, String created) {
        this.setUserId(userId);
        this.setPollId(pollId);
        this.setParentId(parentId);
        this.setId(id);
        this.setContent(content);
        this.setCreated(created);
        
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

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}