package com.aws.codestar.projecttemplates.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.aws.codestar.projecttemplates.model.Comment;

public class CommentRowMapper implements RowMapper<Comment> {

    @Override
    public Comment mapRow(ResultSet rs, int row) throws SQLException {
        Comment comment = new Comment();
        comment.setUserId(rs.getInt("user_id"));
        comment.setPollId(rs.getInt("poll_id"));
        comment.setParentId(rs.getInt("parent_id"));
        comment.setContent(rs.getString("content"));
        comment.setId(rs.getInt("id"));
        
        return comment;
    }
}
