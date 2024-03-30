package sg.com.smartinventory.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.com.smartinventory.entities.Product;

// ProductRepository simply extends JpaRepository, giving us a lot of default methods to access the database without the hard work. 
// This is a very powerful feature of Spring Data JPA. It will automatically do a property check and traverse the supported nested properties, translating them into the relevant queries. 
// We can also create custom queries using the JPA criteria API by simply creating a method with a certain naming convention specified by the query creation mechanism.
public interface ProductRepository extends JpaRepository<Product, Long> {
  // Custom query to find all products with a certain ID.
  // List<Product> findById(long id);

  // Custom query to find all products with a certain category.
  List<Product> findByCategoryContaining(String category);

  // Custom query to find all products with a certain name.
  List<Product> findByName(String name);

  // Custom query to find all products with a certain description.
  List<Product> findByDescription(String description);

  // Custom query to find all products with a certain price.
  List<Product> findByPrice(double price);

  // Custom query to find all products with a certain stock quantity.
  List<Product> findByStockQuantity(int stockQuantity);
}