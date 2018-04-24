package com.aws.codestar.projecttemplates.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.aws.codestar.projecttemplates.model.Vote;

public class VoteRowMapper implements RowMapper<Vote> {

    @Override
    public Vote mapRow(ResultSet rs, int row) throws SQLException {
        Vote vote = new Vote();
        vote.setUserId(rs.getInt("user_id"));
        vote.setPollId(rs.getInt("poll_id"));
        vote.setPollOptionId(rs.getInt("poll_option_id"));
        vote.setId(rs.getInt("id"));
        
        return vote;
    }
}
