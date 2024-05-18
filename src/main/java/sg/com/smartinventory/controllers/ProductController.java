package sg.com.smartinventory.controllers;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import sg.com.smartinventory.entities.Product;
import sg.com.smartinventory.services.ProductService;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "http://localhost:5173")
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

  // Get All
  @GetMapping({ "", "/" })
  public ResponseEntity<ArrayList<Product>> getAllProducts() {
    ArrayList<Product> allProducts = productService.getAllProducts();
    return new ResponseEntity<>(allProducts, HttpStatus.OK);
  }

  // READ (id)
  @GetMapping("/{id}")
  public ResponseEntity<Product> getProduct(@PathVariable long id) {
    Product foundProduct = productService.getProduct(id);
    return new ResponseEntity<>(foundProduct, HttpStatus.OK);
  }

  // READ (search by name)
  @GetMapping("/search")
  public ResponseEntity<ArrayList<Product>> searchProduct(@RequestParam String name) {
    ArrayList<Product> products = productService.searchProduct(name);
    return new ResponseEntity<>(products, HttpStatus.OK);
  }

  // READ (search by category)
  @GetMapping("/search/category")
  public ResponseEntity<ArrayList<Product>> searchCategory(@RequestParam String category) {
    ArrayList<Product> foundCategory = productService.searchProductCat(category);
    return new ResponseEntity<>(foundCategory, HttpStatus.OK);
  }

  // UPDATE
  @PutMapping("/{id}")
  public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
    // try {
    Product updateProduct = productService.updateProduct(id, product);
    return new ResponseEntity<>(updateProduct, HttpStatus.OK);
    // } catch (ProductNotFoundException ex) {
    // return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    // } -> Not needed already throw error in serviceImpls
  }

  // Delete - refactor try catch block by adding throw in ProductServiceImpl
  @DeleteMapping("/{id}")
  public ResponseEntity<HttpStatus> deleteProduct(@PathVariable long id) {
    productService.deleteProduct(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}