package edu.school21.chat.repositories;

import edu.school21.chat.models.ChatRoom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
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
}
