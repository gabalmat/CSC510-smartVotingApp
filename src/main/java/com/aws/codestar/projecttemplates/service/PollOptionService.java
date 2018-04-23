package com.aws.codestar.projecttemplates.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aws.codestar.projecttemplates.dao.PollOptionDao;
import com.aws.codestar.projecttemplates.model.PollOption;

@Service
public class PollOptionService {
	
	@Autowired
	private PollOptionDao pollOptionDao;
	
	public List<PollOption> getPollOptionsByPoll(final int pollId) {
		return pollOptionDao.getPollOptionsByPoll(pollId);
	}
	
	public void addPollOption(final PollOption pollOption) {
		pollOptionDao.addPollOption(pollOption);
	}

}
