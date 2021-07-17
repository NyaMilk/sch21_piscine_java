package edu.school21.sockets.models;

import sun.rmi.runtime.Log;

import java.sql.Timestamp;

public class Message {
    private Long id;
    private Long authorId;
    private String text;
    private Timestamp createdAt;

    public Message(Long id, Long authorId, String text, Timestamp createdAt) {
        this.id = id;
        this.authorId = authorId;
        this.text = text;
        this.createdAt = createdAt;
    }

    public Message(Long authorId, String text) {
        this.authorId = authorId;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public String getText() {
        return text;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
