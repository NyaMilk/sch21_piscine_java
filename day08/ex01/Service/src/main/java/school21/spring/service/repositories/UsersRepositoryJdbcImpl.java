package school21.spring.service.repositories;

import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository<User> {
    private DataSource dataSource;

    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try {
            User user = new User();

            Connection connection = dataSource.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM UsersNew WHERE email=?");

            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User((long) resultSet.getInt("id"), resultSet.getString("email"));
            }

            connection.close();
            return Optional.of(user);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        ArrayList<User> allUsers = new ArrayList<>();

        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM UsersNew");

            while (resultSet.next()) {
                allUsers.add(new User((long) resultSet.getInt("id"), resultSet.getString("email")));
            }

            connection.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return allUsers;
    }

    @Override
    public User findById(Long id) {
        try {
            User user = null;

            Connection connection = dataSource.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM UsersNew WHERE id=?");

            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User((long) resultSet.getInt("id"), resultSet.getString("email"));
            }

            connection.close();

            return user;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public void delete(Long id) {
        try {
            Connection connection = dataSource.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM UsersNew WHERE id=?");

            preparedStatement.setLong(1, id);

            preparedStatement.execute();

            connection.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(User user) {
        try {
            Connection connection = dataSource.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE UsersNew SET email=? WHERE id=?");

            preparedStatement.setString(1, user.getEmail());

            preparedStatement.setLong(2, user.getId());

            preparedStatement.executeUpdate();

            connection.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void save(User user) {
        try {
            Connection connection = dataSource.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO UsersNew VALUES (?, ?)");

            preparedStatement.setLong(1, user.getId());

            preparedStatement.setString(2, user.getEmail());

            preparedStatement.executeUpdate();

            connection.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
