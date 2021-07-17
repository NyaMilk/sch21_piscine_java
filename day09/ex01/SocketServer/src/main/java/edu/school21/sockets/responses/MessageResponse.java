package edu.school21.sockets.responses;

import java.sql.Timestamp;

public class MessageResponse {
    private Long id;
    private String username;
    private String text;
    private Timestamp createdAt;

    public MessageResponse(Long id, String username, String text, Timestamp createdAt) {
        this.id = id;
        this.username = username;
        this.text = text;
        this.createdAt = createdAt;
    }

    public MessageResponse(String username, String text) {
        this.username = username;
        this.text = text;
    }

    @Override
    public String toString() {
        return username + ": " + text;
    }
}
