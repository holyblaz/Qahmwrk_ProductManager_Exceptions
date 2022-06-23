package ru.netology.manager;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

public class ProductManagerTestNonEmptyWithSetUp {
    private ProductRepository repository = new ProductRepository();
    private ProductManager manager = new ProductManager(repository);

    Product first = new Book(1, "История", 500, "Антонов");
    Product second = new Book(2, "Математика", 700, "Антонов");
    Product third = new Book(3, "Английский", 400, "Зайцева");
    Product fourth = new Smartphone(4, "Сиаоми", 1000, "Китай");
    Product fifth = new Smartphone(5, "Айфон", 1500, "Америка");

    @BeforeEach
    void setUp() {
        manager.add(first);
        manager.add(second);
        manager.add(third);
        manager.add(fourth);
    }

    @Test
    public void shouldReturnAllItems() {
        assertArrayEquals(new Product[]{first, second, third, fourth}, repository.findAll());
    }

    @Test
    public void shouldAddOneItemMore() {
        manager.add(fifth);
        assertArrayEquals(new Product[]{first, second, third, fourth, fifth}, repository.findAll());
    }

    @Test
    public void shouldFindBookByName() {
        assertArrayEquals(new Product[]{first}, manager.searchBy("Ист"));
    }

    @Test
    public void shouldFindSmartphoneByName() {
        assertArrayEquals(new Product[]{fourth}, manager.searchBy("Сиаоми"));
    }

    @Test
    public void shouldFindNothing() {
        assertArrayEquals(new Product[]{}, manager.searchBy("Стол"));
    }

}


