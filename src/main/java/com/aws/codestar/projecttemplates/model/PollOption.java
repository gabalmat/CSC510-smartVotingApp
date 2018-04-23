package com.aws.codestar.projecttemplates.model;

public class PollOption {

	private int pollId;
	private String description;
	
	public PollOption() {}
	
	public PollOption(String description, int pollId) {
		this.setDescription(description);
		this.setPollId(pollId);
	}

	public int getPollId() {
		return pollId;
	}

	public void setPollId(int pollId) {
		this.pollId = pollId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
