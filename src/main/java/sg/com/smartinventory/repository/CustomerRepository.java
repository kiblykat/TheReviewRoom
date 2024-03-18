package sg.com.smartinventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.com.smartinventory.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
  
}
