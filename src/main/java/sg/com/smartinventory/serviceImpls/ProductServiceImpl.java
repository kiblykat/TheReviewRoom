package sg.com.smartinventory.serviceImpls;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import sg.com.smartinventory.entities.Product;
import sg.com.smartinventory.exceptions.ProductNotFoundException;
import sg.com.smartinventory.repositories.ProductRepository;
import sg.com.smartinventory.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
  private ProductRepository productRepository;

  // @Autowired
  public ProductServiceImpl(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  // - - - POST METHODS
  @Override
  public Product createProduct(Product product) {
    Product newProduct = productRepository.save(product);

    return newProduct;
  }

  // - - - GET METHODS
  @Override
  public Product getProduct(Long id) {
    // Optional<Product> optionalProduct = productRepository.findById(id);
    // if(optionalProduct.isPresent()) {
    // Product foundProduct = optionalProduct.get();
    // return foundProduct;
    // }
    // throw new ProductNotFoundException(id);
    return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
  }

  @Override
  public ArrayList<Product> getAllProducts() {
    List<Product> allProducts = productRepository.findAll();
    return (ArrayList<Product>) allProducts;
  }

  @Override
  public ArrayList<Product> searchProduct(String name) {
    List<Product> products = productRepository.findByName(name);
    return (ArrayList<Product>) products;
  }

  @Override
  public ArrayList<Product> searchProductCat(String category) {
    List<Product> products = productRepository.findByCategoryContaining(category);
    return (ArrayList<Product>) products;
  }

  // - - - PUT METHODS
  @Override
  public Product updateProduct(Long id, Product product) {
    // Retrieve the product from the database.
    Product productToUpdate = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));

    // Update the product retrieved from the database.
    productToUpdate.setCategory(product.getCategory());
    productToUpdate.setName(product.getName());
    productToUpdate.setDescription(product.getDescription());
    productToUpdate.setPrice(product.getPrice());
    productToUpdate.setStockQuantity(product.getStockQuantity());

    // Save the updated product back to the database.
    return productRepository.save(productToUpdate);
  }

  // - - - DELETE METHODS
  @Override // use long primitive type as args cannot be null
  public void deleteProduct(long id) {
    productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    productRepository.deleteById(id);
  }
}