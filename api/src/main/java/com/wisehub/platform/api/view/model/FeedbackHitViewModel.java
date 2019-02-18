package com.wisehub.platform.api.view.model;

public class FeedbackHitViewModel {

	private String feedback;
	private Long hits;

	public FeedbackHitViewModel() {
	}

	public FeedbackHitViewModel(String feedback, Long hits) {
		super();
		this.feedback = feedback;
		this.hits = hits;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public Long getHits() {
		return hits;
	}

	public void setHits(Long hits) {
		this.hits = hits;
	}

}
