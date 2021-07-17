package edu.school21.chat.models;

import java.util.List;
import java.util.Objects;

public class User {
    private Long id;
    private String login;
    private String password;
    private List<ChatRoom> createdChatRooms;
    private List<ChatRoom> activeChatRooms;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User(Long id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.createdChatRooms = null;
        this.activeChatRooms = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) && login.equals(user.login) && password.equals(user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password);
    }

    @Override
    public String toString() {
        return "User {" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", createdChatRooms=" + createdChatRooms +
                ", activeChatRooms=" + activeChatRooms +
                '}';
    }
}
