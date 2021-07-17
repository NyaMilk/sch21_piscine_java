package edu.school21.chat.models;

import java.sql.Timestamp;
import java.util.Objects;

public class Message {
    private Integer id;
    private User author;
    private ChatRoom chatRoom;
    private String message;
    private Timestamp createdAt;

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
        return "Message {" +
                "id=" + id +
                ", author=" + author +
                ", chatRoom=" + chatRoom +
                ", message='" + message + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
