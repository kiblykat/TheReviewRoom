package sg.com.smartinventory.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.com.smartinventory.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}