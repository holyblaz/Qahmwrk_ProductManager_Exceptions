package ru.netology.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;

public class ProductRepositoryTestNonEmptyWithSetUp {

    private ProductRepository repository = new ProductRepository();

    Product first = new Book(1, "apex", 10, "Anton");
    Product second = new Smartphone(2, "xiaomi", 5_000, "China");
    Product third = new Book(3, "Junit", 500, "Pavlov");
    Product fourth = new Smartphone(4, "iphone", 600, "china");

    @BeforeEach
    void SetUp() {
        repository.save(first);
        repository.save(second);
        repository.save(third);
        repository.save(fourth);
    }

    @Test
    public void shouldRemoveById() {
        repository.removeById(1);
        assertArrayEquals(new Product[]{second, third, fourth}, repository.findAll());
    }

    @Test
    public void shouldRemoveByIdTwoItems() {
        repository.removeById(1);
        repository.removeById(4);
        assertArrayEquals(new Product[]{second, third}, repository.findAll());
    }

    @Test
    public void shouldReturnAllItems() {
        assertArrayEquals(new Product[]{first, second, third, fourth}, repository.findAll());
    }
}