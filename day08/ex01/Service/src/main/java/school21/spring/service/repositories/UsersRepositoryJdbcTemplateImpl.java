package school21.spring.service.repositories;

import school21.spring.service.models.User;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcTemplateImpl implements UsersRepository<User> {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public UsersRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<User> userRowMapper = (resultSet, rowNum) -> {
        return new User(resultSet.getLong("id"), resultSet.getString("email"));
    };

    @Override
    public Optional<User> findByEmail(String email) {
        List<User> userList = jdbcTemplate.query("SELECT * FROM UsersNew WHERE email = ?", userRowMapper, email);
        if (userList.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(userList.get(0));
        }
    }

    @Override
    public User findById(Long id) {
        List<User> userList = jdbcTemplate.query("SELECT * FROM UsersNew WHERE id = ?", userRowMapper, id);

        if (userList.isEmpty()) {
            return null;
        } else {
            return userList.get(0);
        }
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * FROM UsersNew", userRowMapper);
    }

    @Override
    public void save(User entry) {
        jdbcTemplate.update("INSERT INTO UsersNew (email) VALUES (?)", entry.getEmail());
    }

    @Override
    public void update(User entry) {
        jdbcTemplate.update("UPDATE UsersNew SET email = ? WHERE id = ?", entry.getEmail(), entry.getId());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM UsersNew WHERE id = ?", id);
    }
}
