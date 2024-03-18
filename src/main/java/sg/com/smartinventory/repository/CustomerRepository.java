package sg.com.smartinventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.com.smartinventory.entity.Customer;

//CustomerRepository simply extends JpaRepository, giving us a lot of default methods to access db without the hardwork  
public interface CustomerRepository extends JpaRepository<Customer, Long> {
 
}
