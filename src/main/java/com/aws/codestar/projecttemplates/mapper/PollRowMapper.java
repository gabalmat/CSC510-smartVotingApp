package com.aws.codestar.projecttemplates.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.aws.codestar.projecttemplates.model.Poll;

public class PollRowMapper implements RowMapper<Poll> {
	
	@Override
	public Poll mapRow(ResultSet rs, int row) throws SQLException {
		Poll poll = new Poll();
		poll.setTitle(rs.getString("title"));
		poll.setDescription(rs.getString("description"));
		poll.setPollId(rs.getInt("id"));
		poll.setUserId(rs.getInt("user_id"));
		poll.setCategoryId(rs.getInt("category_id"));
		
		return poll;
	}
	
}
