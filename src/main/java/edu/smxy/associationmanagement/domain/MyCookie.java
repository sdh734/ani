package edu.smxy.associationmanagement.domain;

public class MyCookie {
    private int userId;
    private String sessionId;

    public MyCookie(int userId, String sessionId) {
        this.userId = userId;
        this.sessionId = sessionId;
    }

    @Override
    public String toString() {
        return "MyCookie{" +
                "userId=" + userId +
                ", sessionId='" + sessionId + '\'' +
                '}';
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
