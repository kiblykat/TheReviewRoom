package sg.com.smartinventory.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.com.smartinventory.entities.Customer;

// CustomerRepository simply extends JpaRepository, giving us a lot of default methods to access the database without the hard work. 
// This is a very powerful feature of Spring Data JPA. It will automatically do a property check and traverse the supported nested properties, translating them into the relevant queries. 
// We can also create custom queries using the JPA criteria API by simply creating a method with a certain naming convention specified by the query creation mechanism.
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Custom query to find all customers with a certain ID.
    List<Customer> findById(long id);

    // Custom query to find all customers with a certain first name.
    List<Customer> findByFirstName(String firstName);

    // Custom query to find all customers with a certain last name.
    List<Customer> findByLastName(String lastName);

    // Custom query to find all customers with a certain first name and last name.
    List<Customer> findByFirstNameAndLastName(String firstName, String lastName);

    // Custom query to find all customers with a certain country.
    List<Customer> findByCountry(String country);

    // Custom query to find all customers with a certain address.
    List<Customer> findByAddress(String address);

    // Custom query to find all customers with a certain postal code.
    List<Customer> findByPostalCode(int postalCode);

    // Custom query to find all customers with a certain phone number.
    List<Customer> findByPhoneNumber(int phoneNumber);

    // Custom query to find all customers with a certain email.
    List<Customer> findByEmail(String email);
}