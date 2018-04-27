package com.aws.codestar.projecttemplates.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aws.codestar.projecttemplates.mapper.CommentRowMapper;
import com.aws.codestar.projecttemplates.mapper.SignCommentRowMapper;
import com.aws.codestar.projecttemplates.model.Comment;
import com.aws.codestar.projecttemplates.model.SignComment;

@Repository
@Transactional
public class CommentDao {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public Comment getCommentById(final int id) {
        Comment comment = jdbcTemplate.queryForObject("select * from comments where id = ?", new Object[] {id}, new CommentRowMapper());
        return comment;
    }

    public Comment getCommentParent(final int id) {
        Comment comment = jdbcTemplate.queryForObject("select * from comments where parent_id = ?", new Object[] {id}, new CommentRowMapper());
        return comment;
    }
    
    public void addComment(final Comment comment) {
        jdbcTemplate.update("insert into comments (user_id, poll_id, parent_id, content) values (?,?,?,?)",
                new Object[] { comment.getUserId(), comment.getPollId(), comment.getParentId(), comment.getContent()});
    }
    
    public List<Comment> getComments() {
        List<Comment> comments = jdbcTemplate.query("select * from comments", new CommentRowMapper());
        return comments;
    }

    public List<Comment> getCommentsByPollId(final int pollId) {
        List<Comment> comments = jdbcTemplate.query("select * from comments where poll_id = ? order by created", new Object[] {pollId}, new CommentRowMapper());
        
        // for each comment, determine if it is significant; if it is, set the significant property to tru
        for (Comment comment : comments) {
        	List<SignComment> signComms = jdbcTemplate.query("select * from significant_comments where comment_id = ?", new Object[] {comment.getId()}, new SignCommentRowMapper());
        	if (signComms.size() > 0) {		//significant comment
        		comment.setSignificant(true);
        	}
        }
        return comments;
    }

    public List<Comment> getCommentsByUserId(final int userId) {
        List<Comment> comments = jdbcTemplate.query("select * from comments where user_id = ?", new Object[] {userId}, new CommentRowMapper());
        return comments;
    }

}
