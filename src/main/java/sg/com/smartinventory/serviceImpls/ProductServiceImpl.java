package sg.com.smartinventory.serviceImpls;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import sg.com.smartinventory.entities.Product;
// import sg.com.smartinventory.exceptions.ProductNotFoundException;
import sg.com.smartinventory.repositories.ProductRepository;
import sg.com.smartinventory.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    // @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product createProduct(Product product) {
        Product newProduct = productRepository.save(product);

        return newProduct;
    }

    @Override
    public Product getProduct(long id) {
        // Optional<Product> optionalProduct = productRepository.findById(id);
        // if(optionalProduct.isPresent()) {
        // Product foundProduct = optionalProduct.get();
        // return foundProduct;
        // }
        // throw new ProductNotFoundException(id);
        // return productRepository.findById(id).orElseThrow(() -> new
        // ProductNotFoundException(id));
        return productRepository.findById(id).get(0);
    }

    @Override
    public ArrayList<Product> getAllProducts() {
        List<Product> allProducts = productRepository.findAll();
        return (ArrayList<Product>) allProducts;
    }

    @Override
    public Product updateProduct(long id, Product product) {
        // Retrieve the product from the database.
        // Product productToUpdate = productRepository.findById(id).orElseThrow(() ->
        // new
        // ProductNotFoundException(id));
        Product productToUpdate = productRepository.findById(id).get(0);

        // Update the product retrieved from the database.
        productToUpdate.setCategory(product.getCategory());
        productToUpdate.setName(product.getName());
        productToUpdate.setDescription(product.getDescription());
        productToUpdate.setPrice(product.getPrice());
        productToUpdate.setStockQuantity(product.getStockQuantity());

        // Save the updated product back to the database.
        return productRepository.save(productToUpdate);
    }

    @Override
    public void deleteProduct(long id) {
        productRepository.deleteById(id);
    }
}