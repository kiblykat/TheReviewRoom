package sg.com.smartinventory.services;

import java.util.ArrayList;

import sg.com.smartinventory.entities.Product;

public interface ProductService {
    Product createProduct(Product product);

    Product getProduct(Long id);

    ArrayList<Product> getAllProducts();

    Product updateProduct(Long id, Product product);

    void deleteProduct(long id);
}