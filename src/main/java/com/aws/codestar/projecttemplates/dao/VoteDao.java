package com.aws.codestar.projecttemplates.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aws.codestar.projecttemplates.mapper.VoteRowMapper;
import com.aws.codestar.projecttemplates.model.Vote;

@Repository
@Transactional
public class VoteDao {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public Vote getVoteById(final int id) {
        Vote vote = jdbcTemplate.queryForObject("select * from votes where id = ?", new Object[] {id}, new VoteRowMapper());
        return vote;
    }
    
    public void addVote(final Vote vote) {
        jdbcTemplate.update("insert into votes (user_id, poll_id, poll_option_id) values (?,?,?)",
                new Object[] { vote.getUserId(), vote.getPollId(), vote.getPollOptionId()});
    }
    
    public List<Vote> getVotes() {
        List<Vote> votes = jdbcTemplate.query("select * from votes", new VoteRowMapper());
        return votes;
    }

    public List<Vote> getVotesByPollId(final int pollId) {
        List<Vote> votes = jdbcTemplate.query("select * from votes where poll_id = ?", new Object[] {pollId}, new VoteRowMapper());
        return votes;
    }

}
