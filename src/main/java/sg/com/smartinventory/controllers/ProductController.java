package sg.com.smartinventory.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import sg.com.smartinventory.entities.Product;
import sg.com.smartinventory.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    // @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // CREATE.
    @PostMapping("")
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
        // if(bindingResult.hasErrors()) {
        // return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        // }

        Product newProduct = productService.createProduct(product);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }
}