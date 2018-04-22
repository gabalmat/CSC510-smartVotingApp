package com.aws.codestar.projecttemplates.dao;

import java.util.List;

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
	
	public List<Poll> getPolls() {
		List<Poll> polls = jdbcTemplate.query("select * from polls", new PollRowMapper());
		return polls;
	}
	
	public List<Poll> getPollsWhere(String keyword) {
		List<Poll> polls = jdbcTemplate.query("select * from polls where description LIKE ? or title LIKE ?", 
				new Object[] {"%" + keyword + "%", "%" + keyword + "%"}, new PollRowMapper());
		return polls;
	}
}
