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

        try {
            Optional<Message> messageOptional = messagesRepository.findById(4L);
            if (messageOptional.isPresent()) {
                Message message = messageOptional.get();
                message.setMessage("Bye");
                message.setCreatedAt(null);
                messagesRepository.update(message);
            }
        }
        catch (SQLException ex) {
            System.out.println("Something wrong with query!");
        }

        dataSource.close();
    }
}
