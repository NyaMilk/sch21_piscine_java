package edu.school21.sockets.repositories;

import javax.sql.DataSource;

import edu.school21.sockets.models.User;
import edu.school21.sockets.repositories.UsersRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.Optional;

public class UsersRepositoryImpl implements UsersRepository {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public UsersRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<User> userRowMapper = (resultSet, rowNum) -> {
        return new User(resultSet.getLong("id"), resultSet.getString("username"), resultSet.getString("password"));
    };

    @Override
    public Optional<User> findByUsername(String username) {
        List<User> userList = jdbcTemplate.query("SELECT * FROM Users WHERE username = ?", userRowMapper, username);

        if (userList.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(userList.get(0));
        }
    }

    @Override
    public void save(User entry) {
        jdbcTemplate.update("INSERT INTO Users (username, password) VALUES (?, ?)", entry.getUsername(), entry.getPassword());
    }
}
