package edu.school21.chat.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.ChatRoom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost/piscine");
        config.setUsername("super");
        config.setPassword("1234");
        HikariDataSource dataSource = new HikariDataSource(config);
        MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl(dataSource);

        User creator = new User(1L, "Clink", "Clink");
        ChatRoom room = new ChatRoom(1L, "Jedi", creator);
        Message message = new Message(null, creator, room, "Hello!", new Timestamp(System.currentTimeMillis()));
        messagesRepository.save(message);
        System.out.println("Message id: " + message.getId());

        User incorrectUser = new User(null, "Wrong", "Id");
        Message incorrectMessage = new Message(null, incorrectUser, room, "Hello!", new Timestamp(System.currentTimeMillis()));
        messagesRepository.save(incorrectMessage);

        dataSource.close();
    }
}
