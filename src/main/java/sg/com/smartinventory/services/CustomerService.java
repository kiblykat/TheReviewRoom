package sg.com.smartinventory.services;

import sg.com.smartinventory.entities.Customer;
// import sg.com.smartinventory.entities.Review;

public interface CustomerService {
    Customer createCustomer(Customer customer);

    // Review addReviewToCustomer(long customerId, long productId, Review review);
}