package edu.smxy.associationmanagement.domain;

public class MyCookie {
	private int userId;
	private String sessionId;

	public MyCookie(final int userId, final String sessionId) {
		this.userId = userId;
		this.sessionId = sessionId;
	}

	@Override
	public String toString() {
		return "MyCookie{userId=" + this.userId + ", sessionId='" + this.sessionId + '\'' + '}';
	}

	public String getSessionId() {
		return this.sessionId;
	}

	public void setSessionId(final String sessionId) {
		this.sessionId = sessionId;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(final int userId) {
		this.userId = userId;
	}
}
