package com.aws.codestar.projecttemplates.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.aws.codestar.projecttemplates.model.SignComment;

public class SignCommentRowMapper implements RowMapper<SignComment> {

    @Override
    public SignComment mapRow(ResultSet rs, int row) throws SQLException {
        SignComment signComment = new SignComment();
        signComment.setUserId(rs.getInt("user_id"));
        signComment.setCommentId(rs.getInt("comment_id"));
        signComment.setId(rs.getInt("id"));
        
        return signComment;
    }
}
