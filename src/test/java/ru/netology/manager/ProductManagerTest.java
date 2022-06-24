package ru.netology.manager;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;


class ProductManagerTest {
    private ProductRepository repository = new ProductRepository();
    private ProductManager manager = new ProductManager(repository);

    Product first = new Book(1, "История", 500, "Антонов");
    Product second = new Book(2, "История России", 700, "Антонов");
    Product third = new Book(3, "Английский", 400, "Зайцева");
    Product fourth = new Smartphone(4, "Сиаоми", 1000, "Китай");
    Product fifth = new Smartphone(5, "Айфон", 1500, "Америка");

    @Test
    public void addProduct() {
        manager.add(first);
        assertArrayEquals(new Product[]{first}, repository.findAll());
    }

    @Test
    public void searchByName() {
        manager.add(second);
        assertArrayEquals(new Product[]{second}, manager.searchBy("История России"));
    }

    @Test
    public void searchByNotFullNameBook() {
        manager.add(first);
        assertArrayEquals(new Product[]{first}, manager.searchBy("Истор"));
    }
    @Test
    public void addAllItemsAndFindBook() {
        manager.add(first);
        manager.add(second);
        manager.add(third);
        manager.add(fourth);
        manager.add(fifth);

        assertArrayEquals(new Product[]{third}, manager.searchBy("Английский"));
    }
    /*@Test
    public void searchMatchesBookAuthor() {
        manager.add(third);
        assertArrayEquals(new Product[]{third}, manager.searchBy("Зайцева"));
    }*/

   /* @Test
    public void searchMatchesSmartphoneProducer() {
        manager.add(fourth);
        assertArrayEquals(new Product[]{fourth}, manager.searchBy("Китай"));
    }*/

    @Test
    public void searchMatchesNameSmartphone() {
        manager.add(fourth);
        assertArrayEquals(new Product[]{fourth}, manager.searchBy("Сиаоми"));
    }

    @Test
    public void searchNotFullNameSmartphone() {
        manager.add(fifth);
        assertArrayEquals(new Product[]{fifth}, manager.searchBy("Айф"));
    }
    /*@Test
    public void searchAllSameAuthors() {
        manager.add(first);
        manager.add(second);
        assertArrayEquals(new Product[]{first,second}, manager.searchBy("Антонов"));
    }*/

    @Test
    public void searchInEmptyCollection() {
        assertArrayEquals(new Product[]{}, manager.searchBy("Кит"));
    }

    @Test
    public void searchForNothing() {
        assertArrayEquals(new Product[]{}, manager.searchBy(null));
    }
    @Test
    public void searchForFewSameElements() {
        manager.add(first);
        manager.add(second);
        manager.add(third);
        manager.add(fourth);
        assertArrayEquals(new Product[]{first, second}, manager.searchBy("История"));
    }
    @Test
    public void searchInArrayForSimilarElement() {
        manager.add(first);
        manager.add(second);
        manager.add(third);
        manager.add(fourth);
        assertArrayEquals(new Product[]{second}, manager.searchBy("России"));
    }
}

