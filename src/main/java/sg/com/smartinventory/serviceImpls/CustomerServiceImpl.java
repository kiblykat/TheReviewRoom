package sg.com.smartinventory.serviceImpls;

import org.springframework.stereotype.Service;

import sg.com.smartinventory.entities.Customer;
// import sg.com.smartinventory.entities.Review;
// import sg.com.smartinventory.exceptions.CustomerNotFoundException;
import sg.com.smartinventory.repositories.CustomerRepository;
import sg.com.smartinventory.services.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;

    // @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        Customer newCustomer = customerRepository.save(customer);

        return newCustomer;
    }

    // @Override
    // public Review addReviewToCustomer(long customerId, long productId, Review
    // review) {
    // // retrieve the customer from the database.
    // Customer selectedCustomer =
    // customerRepository.findById(customerId).orElseThrow(() -> new
    // CustomerNotFoundException(customerId));

    // // add the customer to the review
    // review.setCustomer(selectedCustomer);

    // // save the review to the database
    // return reviewRepository.save(review);
    // }
}