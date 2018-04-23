package com.aws.codestar.projecttemplates.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.aws.codestar.projecttemplates.model.PollOption;

public class PollOptionRowMapper implements RowMapper<PollOption> {

	@Override
	public PollOption mapRow(ResultSet rs, int row) throws SQLException {
		PollOption pollOption = new PollOption();
		pollOption.setDescription(rs.getString("description"));
		pollOption.setPollId(rs.getInt("poll_id"));
		
		return pollOption;
	}
}
