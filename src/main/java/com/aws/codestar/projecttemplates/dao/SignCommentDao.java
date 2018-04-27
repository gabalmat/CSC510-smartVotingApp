package com.aws.codestar.projecttemplates.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aws.codestar.projecttemplates.mapper.SignCommentRowMapper;
import com.aws.codestar.projecttemplates.model.SignComment;

@Repository
@Transactional
public class SignCommentDao {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public SignComment getSignCommentById(final int id) {
        SignComment signComments = jdbcTemplate.queryForObject("select * from significant_comments where id = ?", new Object[] {id}, new SignCommentRowMapper());
        return signComments;
    }
    
    public void addSignComment(final SignComment signComments) {
        jdbcTemplate.update("insert into significant_comments (user_id, comment_id) values (?,?)",
                new Object[] { signComments.getUserId(), signComments.getCommentId()});
    }
    
    public List<SignComment> getSignComments() {
        List<SignComment> signComments = jdbcTemplate.query("select * from significant_comments", new SignCommentRowMapper());
        return signComments;
    }

    public List<SignComment> getSignCommentsByCommentId(final int commentId) {
        List<SignComment> signComments = jdbcTemplate.query("select * from significant_comments where comment_id = ?", new Object[] {commentId}, new SignCommentRowMapper());
        return signComments;
    }

    public List<SignComment> getSignCommentsByUserId(final int userId) {
        List<SignComment> signComments = jdbcTemplate.query("select * from significant_comments where user_id = ?", new Object[] {userId}, new SignCommentRowMapper());
        return signComments;
    }

}
