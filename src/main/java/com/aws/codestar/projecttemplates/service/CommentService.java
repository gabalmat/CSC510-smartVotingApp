package com.aws.codestar.projecttemplates.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aws.codestar.projecttemplates.dao.CommentDao;
import com.aws.codestar.projecttemplates.model.Comment;

@Service
public class CommentService {
    
    @Autowired
    private CommentDao commentDao;
    
    public Comment getCommentById(final int id) {
        return commentDao.getCommentById(id);
    }
    
    public Comment getCommentParent(final int id) {
        return commentDao.getCommentParent(id);
    }

    public void addComment(final Comment comment) {
        commentDao.addComment(comment);
    }
    
    public List<Comment> getComments() {
        return commentDao.getComments();
    }

    public List<Comment> getCommentsByPollId(final int pollId) {
        return commentDao.getCommentsByPollId(pollId);
    }

    public List<Comment> getCommentsByUserId(final int userId) {
        return commentDao.getCommentsByUserId(userId);
    }

}
