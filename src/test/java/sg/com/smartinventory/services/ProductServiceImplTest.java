package sg.com.smartinventory.services;

import static sg.com.smartinventory.utility.Utility.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.test.context.SpringBootTest;

import sg.com.smartinventory.entities.Product;
import sg.com.smartinventory.repositories.ProductRepository;
import sg.com.smartinventory.serviceImpls.ProductServiceImpl;

@SpringBootTest
public class ProductServiceImplTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    ProductServiceImpl productService;

    /// Name this according to your class name.
    // The Logback library defines 5 log levels in order of priority: TRACE, DEBUG,
    // INFO, WARN, ERROR, with each of these having a corresponding logging method:
    // trace(), debug(), info(), warn(), error().
    private static final Logger test_logger = LoggerFactory.getLogger(ProductServiceImplTest.class);

    // Test Setup and Teardown configuration.
    @BeforeEach
    void init() {

    }

    @AfterEach
    void teardown() {

    }

    @Test
    public void createProductTest() {
        test_logger.info("Starting test: " + getCurrentMethodName() + ". ");

        // 1. SETUP
        // Create a new product.
        Product product = Product.builder().category("Electronics").name("Smartphone")
                .description("High-end smartphone with advanced features. ")
                .price(999.99).stockQuantity(100).build();

        // Mock the save method of the product repository.
        when((productRepository.save(product))).thenReturn(product);

        // 2. EXECUTE.
        Product savedProduct = productService.createProduct(product);

        // 3. ASSERT.
        assertEquals(product, savedProduct, "The saved product should be the same as the new product created. ");

        // Verify that the save method of the product repository is called once only.
        verify(productRepository, times(1)).save(product);

        test_logger.info("Ending test: " + getCurrentMethodName() + ". ");
    }
}