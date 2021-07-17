package edu.school21.sockets.server;

import edu.school21.sockets.config.SocketsApplicationConfig;
import edu.school21.sockets.models.Message;
import edu.school21.sockets.models.User;
import edu.school21.sockets.responses.MessageResponse;
import edu.school21.sockets.services.MessagesService;
import edu.school21.sockets.services.UsersService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;
import java.util.List;

import static edu.school21.sockets.server.Server.connections;

public class Connection extends Thread {
    private final Socket socket;
    private final BufferedReader in;
    private final BufferedWriter out;
    private final MessagesService messagesService;
    private final UsersService usersService;
    private User user;

    public Connection(Socket socket) throws IOException, SQLException {
        ApplicationContext context = new AnnotationConfigApplicationContext(SocketsApplicationConfig.class);
        this.socket = socket;
        this.messagesService = context.getBean(MessagesService.class);
        this.usersService = context.getBean(UsersService.class);
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        try {
            firstStage();
            printMessages();
            start();
        } catch (IOException | SQLException e) {
            closeConnection();
        }
    }

    private void firstStage() throws IOException, SQLException {
        sendMessage("Hello from Server! Type 'signUp' command to register or 'signIn' to login.");

        String command = in.readLine().toLowerCase();

        if (!command.equals("signup") && !command.equals("signin")) {
            sendMessage("Error command!");
            closeConnection();
        }

        sendMessage("Enter username:");

        String username = in.readLine();

        sendMessage("Enter password:");

        String password = in.readLine();

        if (command.equals("signup")) {
            if (usersService.signUp(username, password)) {
                sendMessage("Start messaging!");
            } else {
                sendMessage("User already exist!");
                closeConnection();
            }
        } else if (command.equals("signin")) {
            if (usersService.signIn(username, password)) {
                sendMessage("Start messaging!");
            } else {
                sendMessage("Wrong password or username!");
                closeConnection();
            }
        } else {
            sendMessage("Wrong command!");
            closeConnection();
        }

        user = usersService.getUser(username);
    }

    private void printMessages() throws SQLException {
        List<MessageResponse> messages = messagesService.getMessages();

        for (MessageResponse message : messages) {
            sendMessage(message.toString());
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                String message = in.readLine().trim();

                if (message.toLowerCase().equals("exit")) {
                    closeConnection();
                    break;
                }

                messagesService.sendMessage(new Message(user.getId(), message));

                for (Connection connection : connections) {
                    connection.sendMessage(new MessageResponse(user.getUsername(), message).toString());
                }
            } catch (IOException | SQLException | NullPointerException e) {
                closeConnection();
            }
        }
    }

    private void sendMessage(String message) {
        try {
            out.write(message + "\n");
            out.flush();
        } catch (IOException ignored) {
        }
    }

    private void closeConnection() {
        if (!socket.isClosed()) {
            try {
                socket.close();
                in.close();
                out.close();

                for (Connection connection : connections) {
                    if (connection.equals(this)) {
                        connection.interrupt();
                        break;
                    }
                }

                connections.remove(this);

                if (user != null) {
                    System.out.printf("%s leave from server\n", user.getUsername());
                }
            } catch (IOException ignored) {
            }
        }
    }
}
