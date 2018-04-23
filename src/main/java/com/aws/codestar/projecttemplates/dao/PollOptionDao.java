package com.aws.codestar.projecttemplates.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aws.codestar.projecttemplates.mapper.PollOptionRowMapper;
import com.aws.codestar.projecttemplates.model.PollOption;

@Repository
@Transactional
public class PollOptionDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<PollOption> getPollOptionsByPoll(final int pollId) {
		List<PollOption> pollOptions = jdbcTemplate.query("select * from poll_options where poll_id = ?", 
				new Object[] {pollId}, new PollOptionRowMapper());
		return pollOptions;
	}
	
	public void addPollOption(final PollOption pollOption) {
		jdbcTemplate.update("insert into poll_options (poll_id, description) values(?,?)",
				new Object[] { pollOption.getPollId(), pollOption.getDescription() });
	}
}
