package com.test_practice;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ProductControllerTest {

    @Mock
    private ProductRepository repository;

    private ProductController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new ProductController(repository);
    }

    @Test
    void list_returnsAllProducts() {
        List<Product> products = Arrays.asList(
                new Product(1L, "A", 10.0),
                new Product(2L, "B", 20.0)
        );
        when(repository.findAll()).thenReturn(products);

        List<Product> result = controller.list();

        assertThat(result).hasSize(2).containsExactlyElementsOf(products);
        verify(repository).findAll();
    }

    @Test
    void create_savesAndReturnsCreated() {
        Product input = new Product(null, "New", 5.0);
        Product saved = new Product(1L, "New", 5.0);

        when(repository.save(input)).thenReturn(saved);

        ResponseEntity<Product> response = controller.create(input);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getHeaders().getLocation().toString()).isEqualTo("/products/1");
        assertThat(response.getBody()).isEqualTo(saved);
        verify(repository).save(input);
    }
}
