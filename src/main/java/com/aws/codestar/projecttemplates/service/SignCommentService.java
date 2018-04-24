package com.aws.codestar.projecttemplates.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aws.codestar.projecttemplates.dao.SignCommentDao;
import com.aws.codestar.projecttemplates.model.SignComment;

@Service
public class SignCommentService {
    
    @Autowired
    private SignCommentDao SignCommentDao;
    
    public SignComment getSignCommentById(final int id) {
        return SignCommentDao.getSignCommentById(id);
    }

    public void addSignComment(final SignComment signComment) {
        SignCommentDao.addSignComment(signComment);
    }
    
    public List<SignComment> getSignComments() {
        return SignCommentDao.getSignComments();
    }

    public List<SignComment> getSignCommentsByCommentId(final int commentId) {
        return SignCommentDao.getSignCommentsByCommentId(commentId);
    }

    public List<SignComment> getSignCommentsByUserId(final int userId) {
        return SignCommentDao.getSignCommentsByUserId(userId);
    }

}
