package com.aws.codestar.projecttemplates.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aws.codestar.projecttemplates.dao.PollDao;
import com.aws.codestar.projecttemplates.model.Poll;

@Service
public class PollService {

	@Autowired
	private PollDao pollDao;
	
	public Poll getPoll(final int id) {
		return pollDao.getPoll(id);
	}
	
	public int addPoll(final Poll poll) throws SQLException {
		int id = pollDao.addPoll(poll);
		return id;
	}
	
	public List<Poll> getPolls() {
		return pollDao.getPolls();
	}
	
	public List<Poll> getPollsWhere(final String whereClause) {
		return pollDao.getPollsWhere(whereClause);
	}

	public List<Poll> getPollsWhereWithCat(final String whereClause, final int catId) {
		return pollDao.getPollsWhereWithCat(whereClause, catId);
	}

	public List<Poll> getUserPolls(final int id) {
		return pollDao.getUserPolls(id);
	}

	public List<Poll> getUserPollsWithCat(final int id) {
		return pollDao.getUserPollsWithCat(id);
	}

}
