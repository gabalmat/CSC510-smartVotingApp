package com.aws.codestar.projecttemplates.service;

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
	
	public void addPoll(final Poll poll) {
		pollDao.addPoll(poll);
	}
	
	public List<Poll> getPolls() {
		return pollDao.getPolls();
	}
	
	public List<Poll> getPollsWhere(final String whereClause) {
		return pollDao.getPollsWhere(whereClause);
	}
}
