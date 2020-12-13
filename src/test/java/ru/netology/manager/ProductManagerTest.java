package ru.netology.manager;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.TShirt;
import ru.netology.exception.NotFoundException;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProductManagerTest {
    ProductRepository repository = new ProductRepository();
    ProductManager manager = new ProductManager(repository);
    Book first = new Book(1, "Name1", 10, "Author1");
    Book second = new Book(2, "Name2", 20, "Author2");
    Book third = new Book(3, "Name3", 30, "Author3");
    TShirt fourth = new TShirt (4, "NameS1", 100, "Green", "S1" );
    TShirt fifth = new TShirt(5, "NameS2", 200, "Red", "S2" );
    TShirt sixth = new TShirt(6, "NameS3", 300, "Blue", "S3" );


    @Test
    public void RemoveExistingItem() {

        manager.add(first);
        manager.add(second);
        manager.add(third);
        manager.add(fourth);
        manager.add(fifth);
        manager.add(sixth);

        repository.removeById(6);
        Product[] actual = repository.findAll();
        Product[] expected = new Product[] {first, second,third, fourth,fifth};
        assertArrayEquals(expected, actual);

    }

    @Test
    public void RemoveGenerateExceptionWhenTryToRemoveMissingElement() {
        assertThrows(NotFoundException.class, () -> repository.removeById(7));
    }

}