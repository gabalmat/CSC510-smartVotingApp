package com.aws.codestar.projecttemplates.model;

public class Vote{

    private int userId;
    private int pollId;
    private int pollOptionId;
    private int id;

    public Vote() {}
    
    public Vote(int userId, int pollId, int pollOptionId, int id) {
        this.setPollId(pollId);
        this.setPollOptionId(pollOptionId);
        this.setUserId(userId);
        this.setId(id);
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

    public int getPollOptionId() {
        return pollOptionId;
    }

    public void setPollOptionId(int pollOptionId) {
        this.pollOptionId = pollOptionId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}