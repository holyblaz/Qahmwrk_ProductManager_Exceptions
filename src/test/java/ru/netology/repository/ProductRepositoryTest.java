package ru.netology.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {
    private ProductRepository repository = new ProductRepository();

    private Book apex = new Book(1, "apex", 10, "Anton");
    private Smartphone xiaomi = new Smartphone(4, "Model8", 5_000, "China");
    Product first = new Book(1, "Junit", 500, "Pavlov");
    Product second = new Smartphone(2, "iphone", 600, "china");

    @Test
    public void shouldSaveOneItem() {
        repository.save(apex);

        Product[] expected = new Product[]{apex};
        Product[] actual = repository.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSetTwoDifferentItems() {
        repository.save(apex);
        repository.save(xiaomi);

        Product[] expected = repository.findAll();
        Product[] actual = repository.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldReturnAllItems() {
        repository.save(first);
        repository.save(second);

        Product[] expected = repository.findAll();
        Product[] actual = new Product[]{first, second};

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldRemoveById() {
        repository.save(first);
        repository.save(second);
        repository.removeById(2);

        Product[] expected = new Product[]{first};
        Product[] actual = repository.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }
}