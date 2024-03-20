package sg.com.smartinventory.serviceImpls;

import org.springframework.stereotype.Service;

import sg.com.smartinventory.entities.Product;
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
}