package edu.school21.sockets.repositories;

import edu.school21.sockets.models.Message;
import edu.school21.sockets.responses.MessageResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.List;

public class MessagesRepositoryImpl implements MessagesRepository {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public MessagesRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<MessageResponse> messageResponseRowMapper = (resultSet, rowNum) -> {
        return new MessageResponse(resultSet.getLong("id"), resultSet.getString("username"),
                resultSet.getString("message"), resultSet.getTimestamp("createdAt"));
    };

    @Override
    public List<MessageResponse> findAll() {
        return jdbcTemplate.query("SELECT m.id, u.username, m.message, m.createdAt FROM Messages m, Users u WHERE m.author = u.id ORDER BY createdAt", messageResponseRowMapper);
    }

    @Override
    public void save(Message entry) {
        jdbcTemplate.update("INSERT INTO Messages (author, message) VALUES (?, ?)", entry.getAuthorId(), entry.getText());
    }
}
