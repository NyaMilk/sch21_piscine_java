package edu.school21.chat.models;

import java.sql.Timestamp;
import java.util.Objects;

public class Message {
    private Long id;
    private User author;
    private ChatRoom chatRoom;
    private String message;
    private Timestamp createdAt;

    public Message(Long id, User author, ChatRoom chatRoom, String message, Timestamp createdAt) {
        this.id = id;
        this.author = author;
        this.chatRoom = chatRoom;
        this.message = message;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public edu.school21.chat.models.ChatRoom getChatRoom() {
        return chatRoom;
    }

    public void setChatRoom(edu.school21.chat.models.ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message1 = (Message) o;
        return id.equals(message1.id) && author.equals(message1.author) && chatRoom.equals(message1.chatRoom) && message.equals(message1.message) && createdAt.equals(message1.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, chatRoom, message, createdAt);
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", author=" + author +
                ", chatRoom=" + chatRoom +
                ", message='" + message + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
