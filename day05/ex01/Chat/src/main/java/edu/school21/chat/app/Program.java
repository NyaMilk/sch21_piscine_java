package edu.school21.chat.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Message;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.sql.SQLException;
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
        Scanner in = new Scanner(System.in);

        System.out.println("Enter message id:");
        while (in.hasNextInt()) {
            Optional<Message> result = Optional.empty();

            try {
                result = messagesRepository.findById((long) in.nextInt());
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            if (result.isPresent()) {
                Message message = result.get();
                System.out.println(message.toString() + '\n');
            } else {
                System.out.println("Message not found!\n");
            }

            System.out.println("Enter message id:");
        }
    }
}
