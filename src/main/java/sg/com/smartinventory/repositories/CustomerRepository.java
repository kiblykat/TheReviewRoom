package sg.com.smartinventory.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.com.smartinventory.entities.Customer;

//CustomerRepository simply extends JpaRepository, giving us a lot of default methods to access db without the hardwork  
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}