package edu.school21.chat.models;

import java.util.List;
import java.util.Objects;

public class ChatRoom {
    private Long id;
    private String name;
    private User creator;
    private List<Message> messages;

    public ChatRoom(Long id, String name) {
        this.id = id;
        this.name = name;
        this.creator = null;
        this.messages = null;
    }

    public ChatRoom(Long id, String name, User creator) {
        this.id = id;
        this.name = name;
        this.creator = creator;
        this.messages = null;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

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
        return "ChatRoom{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", creator=" + creator +
                ", messages=" + messages +
                '}';
    }
}
