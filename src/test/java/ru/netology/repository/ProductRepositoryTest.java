package ru.netology.repository;

import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.exception.AlreadyExistsException;
import ru.netology.exception.NotFoundException;

class ProductRepositoryTest {
    private ProductRepository repository = new ProductRepository();

    private Book apex = new Book(1, "apex", 10, "Anton");
    private Smartphone xiaomi = new Smartphone(2, "Model8", 5_000, "China");
    Product first = new Book(3, "Junit", 500, "Pavlov");
    Product second = new Smartphone(4, "iphone", 600, "china");

    @Test
    public void shouldSaveOneItem() {
        repository.save(apex);

        Product[] expected = new Product[]{apex};
        Product[] actual = repository.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSaveAllItems() {
        repository.save(apex);
        repository.save(xiaomi);
        repository.save(first);
        repository.save(second);

        Product[] expected = new Product[]{apex, xiaomi, first, second};
        Product[] actual = repository.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSaveTwoDifferentItems() {
        repository.save(apex);
        repository.save(xiaomi);

        Product[] expected = repository.findAll();
        Product[] actual = repository.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldReturnAllItems() {
        repository.save(apex);
        repository.save(xiaomi);
        repository.save(first);
        repository.save(second);

        Product[] expected = repository.findAll();
        Product[] actual = new Product[]{apex, xiaomi, first, second};

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldRemoveById() {
        repository.save(first);
        repository.save(second);

        repository.removeById(4);

        Product[] expected = new Product[]{first};
        Product[] actual = repository.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldRemoveAllItems() {
        repository.save(apex);
        repository.save(xiaomi);
        repository.save(first);
        repository.save(second);

        repository.removeById(1);
        repository.removeById(2);
        repository.removeById(3);
        repository.removeById(4);

        Assertions.assertArrayEquals(new Product[]{}, repository.findAll());
    }

    @Test
    public void shouldRemoveByIdTwoItems() {
        repository.save(apex);
        repository.save(xiaomi);
        repository.save(first);
        repository.save(second);

        repository.removeById(2);
        repository.removeById(4);

        Product[] expected = new Product[]{apex, first};
        Product[] actual = repository.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldToRemoveByExistsID() {
        repository.save(apex);
        repository.save(xiaomi);
        repository.save(first);
        repository.save(second);

        repository.removeById(1);

        Assertions.assertArrayEquals(new Product[]{xiaomi, first, second}, repository.findAll());
    }

    @Test
    public void shouldToRemoveNotExistsID() {
        assertThrows(NotFoundException.class, () -> repository.removeById(6));
    }

    @Test
    public void shouldSaveOneMoreItem() {
        repository.save(apex);
        Assertions.assertArrayEquals(new Product[]{apex}, repository.findAll());
    }
    @Test
    public void shouldNotSaveItemThatAlreadyExist() {
        repository.save(apex);
        assertThrows(AlreadyExistsException.class, ()-> repository.save(apex));
    }
}