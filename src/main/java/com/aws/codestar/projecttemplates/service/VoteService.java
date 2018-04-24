package com.aws.codestar.projecttemplates.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aws.codestar.projecttemplates.dao.VoteDao;
import com.aws.codestar.projecttemplates.model.Vote;

@Service
public class VoteService {
    
    @Autowired
    private VoteDao voteDao;
    
    public Vote getVoteById(final int id) {
        return voteDao.getVoteById(id);
    }
    
    public void addVote(final Vote vote) {
        voteDao.addVote(vote);
    }
    
    public List<Vote> getVotes() {
        return voteDao.getVotes();
    }

    public List<Vote> getVotesByPollId(final int pollId) {
        return voteDao.getVotesByPollId(pollId);
    }

}
