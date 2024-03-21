package sg.com.smartinventory.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;

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

    @Test
    public void createProductTest() {

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
    }
}