package sg.com.smartinventory.services;

import java.util.ArrayList;

import sg.com.smartinventory.entities.Customer;
// import sg.com.smartinventory.entities.Review;
import sg.com.smartinventory.entities.Review;

public interface CustomerService {
  Customer createCustomer(Customer customer);

  // Review addReviewToCustomer(long id, Review review);

  Customer getCustomer(Long id);

  ArrayList<Customer> searchCustomer(String name);

  ArrayList<Customer> getAllCustomers();

  Customer updateCustomer(Long id, Customer customer);

  void deleteCustomer(long id);

  Review addReviewToCustomer(long id, long productId, Review review);
  // Review addReviewToCustomer(long customerId, long productId, Review review);
}