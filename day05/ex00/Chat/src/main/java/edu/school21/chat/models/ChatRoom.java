package edu.school21.chat.models;

import java.util.List;
import java.util.Objects;

public class ChatRoom {
    private Integer id;
    private String name;
    private User creator;
    private List<Message> messages;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatRoom chatRoom = (ChatRoom) o;
        return id.equals(chatRoom.id) && name.equals(chatRoom.name) && creator.equals(chatRoom.creator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, creator);
    }

    @Override
    public String toString() {
        return "ChatRoom {" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", creator=" + creator +
                ", messages=" + messages +
                '}';
    }
}
