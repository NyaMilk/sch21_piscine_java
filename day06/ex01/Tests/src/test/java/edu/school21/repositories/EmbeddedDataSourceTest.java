package edu.school21.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import javax.sql.DataSource;
import java.sql.SQLException;

public class EmbeddedDataSourceTest {
    private DataSource dataSource;

    @BeforeEach
    public void init() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        dataSource = builder
                .addScript("schema.sql")
                .addScript("data.sql")
                .build();
    }

    @Test
    void connectionDbTest() throws SQLException {
        Assertions.assertNotNull(dataSource.getConnection());
    }
}