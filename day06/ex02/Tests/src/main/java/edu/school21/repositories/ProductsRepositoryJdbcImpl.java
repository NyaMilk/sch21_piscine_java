package edu.school21.repositories;

        import edu.school21.models.Product;

        import javax.sql.DataSource;
        import java.sql.Connection;
        import java.sql.ResultSet;
        import java.sql.SQLException;
        import java.sql.Statement;
        import java.sql.PreparedStatement;
        import java.util.ArrayList;
        import java.util.List;
        import java.util.Optional;

public class ProductsRepositoryJdbcImpl implements ProductsRepository {

    private DataSource dataSource;

    public ProductsRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Product> findAll() {

        ArrayList<Product> allProduct = new ArrayList<>();

        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM product");

            while (resultSet.next()) {
                allProduct.add(new Product((long) resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("price")));
            }

            connection.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return allProduct;
    }

    @Override
    public Optional<Product> findById(Long id) {
        try {
            Product product = new Product();

            Connection connection = dataSource.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM product WHERE id=?");

            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                product = new Product((long) resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("price"));
            }

            connection.close();

            return Optional.of(product);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public void delete(Long id) {
        try {
            Connection connection = dataSource.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM product WHERE id=?");

            preparedStatement.setLong(1, id);

            preparedStatement.execute();

            connection.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Product product) {
        try {
            Connection connection = dataSource.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE product SET name=?, price=? WHERE id=?");

            preparedStatement.setString(1, product.getName());

            preparedStatement.setInt(2, product.getPrice());

            preparedStatement.setLong(3, product.getId());

            preparedStatement.executeUpdate();

            connection.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void save(Product product) {
        try {
            Connection connection = dataSource.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO product VALUES (?, ?, ?)");

            preparedStatement.setLong(1, product.getId());

            preparedStatement.setString(2, product.getName());

            preparedStatement.setInt(3, product.getPrice());

            preparedStatement.executeUpdate();

            connection.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}