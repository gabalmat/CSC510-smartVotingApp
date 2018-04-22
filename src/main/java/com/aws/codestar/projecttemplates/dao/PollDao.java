package com.aws.codestar.projecttemplates.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aws.codestar.projecttemplates.mapper.PollRowMapper;
import com.aws.codestar.projecttemplates.model.Poll;

@Repository
@Transactional
public class PollDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public Poll getPoll(final int id) {
		Poll poll = jdbcTemplate.queryForObject("select * from polls where id = ?", new Object[] {id}, new PollRowMapper());
		return poll;
	}
	
	public void addPoll(final Poll poll) {
		jdbcTemplate.update("insert into polls (title, description, user_id) values(?,?,?)",
				new Object[] { poll.getTitle(), poll.getDescription(), poll.getUserId() });
	}
}
