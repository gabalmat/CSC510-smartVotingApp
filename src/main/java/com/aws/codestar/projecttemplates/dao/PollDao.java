package com.aws.codestar.projecttemplates.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aws.codestar.projecttemplates.mapper.PollRowMapper;
import com.aws.codestar.projecttemplates.model.Poll;

@Repository
@Transactional
public class PollDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private DataSource dataSource;
	
	
	public Poll getPoll(final int id) {
		Poll poll = jdbcTemplate.queryForObject("select * from polls where id = ?", new Object[] {id}, new PollRowMapper());
		return poll;
	}
	
	public int addPoll(final Poll poll) {
		
		String sql = "insert into polls (title, description, user_id, category_id) values(?,?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		jdbcTemplate.update(
			new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement ps = 
						connection.prepareStatement(sql, new String[] {"id"});
						ps.setString(1, poll.getTitle());
						ps.setString(2, poll.getDescription());
						ps.setInt(3, poll.getUserId());
						ps.setInt(4, poll.getCategoryId());
						
						return ps;
				}
			
		}, keyHolder);
		
		return keyHolder.getKey().intValue();
	}
	
	public List<Poll> getPolls() {
		List<Poll> polls = jdbcTemplate.query("select * from polls", new PollRowMapper());
		return polls;
	}
	
	public List<Poll> getPollsWhere(final String keyword) {
		List<Poll> polls = jdbcTemplate.query("select * from polls where lower(description) LIKE ? or lower(title) LIKE ?", 
				new Object[] {"%" + keyword + "%", "%" + keyword + "%"}, new PollRowMapper());
		return polls;
	}

	public List<Poll> getPollsWhereWithCat(final String keyword, final int catId) {
		List<Poll> polls = jdbcTemplate.query("select * from polls where (lower(description) LIKE ? or lower(title) LIKE ?) and category_id = ?", 
				new Object[] {"%" + keyword + "%", "%" + keyword + "%", catId}, new PollRowMapper());
		return polls;
	}

	public List<Poll> getUserPolls(final int id) {
		List<Poll> polls = jdbcTemplate.query("select * from polls where user_id = ?", 
				new Object[] {id}, new PollRowMapper());
		return polls;
	}

	public List<Poll> getUserPollsWithCat(final int id) {
		List<Poll> polls = jdbcTemplate.query("select title, polls.description, polls.user_id, polls.id, categories.name, polls.created, polls.category_id from polls, categories where polls.user_id = ? and polls.category_id = categories.id", 
				new Object[] {id}, new PollRowMapper());
		return polls;
	}

}
