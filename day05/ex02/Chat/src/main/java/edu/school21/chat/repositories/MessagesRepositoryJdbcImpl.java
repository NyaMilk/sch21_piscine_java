package edu.school21.chat.repositories;

import edu.school21.chat.exceptions.NotSavedSubEntityException;
import edu.school21.chat.models.ChatRoom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {
    private DataSource dataSource;

    public MessagesRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Message> findById(Long id) throws SQLException {
        try {
            Connection connection = dataSource.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("select m.id, m.message, m.createdat, u.id uid, u.login, u.password, c.id cid, c.name chatname\n" +
                    "from messages m, users u, chatrooms c\n" +
                    "where m.author = u.id and c.id = m.chatroom and m.id=?");

            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                return Optional.empty();
            }

            User user = new User((long) resultSet.getInt("uid"), resultSet.getString("login"), resultSet.getString("password"));

            ChatRoom chatRoom = new ChatRoom((long) resultSet.getInt("cid"), resultSet.getString("chatname"));

            Message message = new Message((long) resultSet.getInt("id"), user, chatRoom, resultSet.getString("message"), resultSet.getTimestamp("createdat"));

            connection.close();

            return Optional.of(message);
        } catch (SQLException e) {
            System.out.println("Something wrong with database!");
        }

        return Optional.empty();
    }

    @Override
    public void save(Message message) throws NotSavedSubEntityException {
        if (message.getAuthor().getId() == null || message.getChatRoom().getId() == null) {
            throw new NotSavedSubEntityException("There is no current id in database!!");
        }

        long authorId = message.getAuthor().getId();

        long chatRoomId = message.getChatRoom().getId();

        String text = message.getMessage();

        try {
            Connection connection = dataSource.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO MESSAGES (author, chatRoom, message) VALUES (?, ?, ?) RETURNING ID");

            preparedStatement.setLong(1, authorId);

            preparedStatement.setLong(2, chatRoomId);

            preparedStatement.setString(3, text);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                throw new NotSavedSubEntityException("There is no current id in database!");
            }

            message.setId(resultSet.getLong("id"));

            connection.close();
        } catch (SQLException e) {
            throw new NotSavedSubEntityException("There is no current id in database!");
        }

    }
}
