package edu.school21.repositories;

import edu.school21.models.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import java.util.Arrays;
import java.util.List;

class ProductsRepositoryJdbcImplTest {
    private EmbeddedDatabase database;

    private ProductsRepository repository;

    final List<Product> EXPECTED_FIND_ALL_PRODUCTS =
            Arrays.asList(
                    new Product(1L, "Sheldon", 10),
                    new Product(2L, "Leonard", 20),
                    new Product(3L, "Howard", 30),
                    new Product(4L, "Raj", 40),
                    new Product(5L, "Penny", 50));
    final Product EXPECTED_FIND_BY_ID_PRODUCT = new Product(2L, "Leonard", 20);
    final Product EXPECTED_UPDATED_PRODUCT = new Product(1L, "Sheldon", 60);
    final Product EXPECTED_SAVED_PRODUCT = new Product(6L, "Stuart", 80);

    @BeforeEach
    public void init() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();

        database = builder
                .addScript("schema.sql")
                .addScript("data.sql")
                .build();

        repository = new ProductsRepositoryJdbcImpl(database);
    }

    @Test
    public void testFindAll() {
        Assertions.assertEquals(EXPECTED_FIND_ALL_PRODUCTS, repository.findAll());
    }

    @Test
    public void testFindById() {
        Assertions.assertEquals(EXPECTED_FIND_BY_ID_PRODUCT, repository.findById(2L).get());
    }

    @Test
    public void testUpdate() {
        repository.update(new Product(1L, "Sheldon", 60));
        Assertions.assertEquals(EXPECTED_UPDATED_PRODUCT, repository.findById(1L).get());
    }

    @Test
    public void testSave() {
        repository.save(new Product(6L, "Stuart", 80));
        Assertions.assertEquals(EXPECTED_SAVED_PRODUCT, repository.findById(6L).get());
    }

    @Test
    public void testDelete() {
        repository.delete(6L);
        Assertions.assertEquals(new Product(null, null, 0), repository.findById(6L).get());
    }

    @AfterEach
    public void close() {
        database.shutdown();
    }
}