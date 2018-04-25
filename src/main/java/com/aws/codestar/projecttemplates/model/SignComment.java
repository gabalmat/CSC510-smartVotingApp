package com.aws.codestar.projecttemplates.model;

public class SignComment{

    private int userId;
    private int commentId;
    private int id;

    public SignComment() {}
    
    public SignComment(int userId, int commentId, int id) {
        this.setId(id);
        this.setCommentId(commentId);
        this.setUserId(userId);
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}