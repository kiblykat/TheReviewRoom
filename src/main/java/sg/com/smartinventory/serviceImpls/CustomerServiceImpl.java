package sg.com.smartinventory.serviceImpls;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import sg.com.smartinventory.entities.Customer;
import sg.com.smartinventory.entities.Review;
// import sg.com.smartinventory.entities.Review;
import sg.com.smartinventory.exceptions.CustomerNotFoundException;
import sg.com.smartinventory.repositories.CustomerRepository;
import sg.com.smartinventory.repositories.ReviewRepository;
import sg.com.smartinventory.services.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;
    private ReviewRepository reviewRepository;

    // @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, ReviewRepository reviewRepository) {
        this.customerRepository = customerRepository;
        this.reviewRepository = reviewRepository;
    }

    // - - - POST METHODS
    @Override
    public Customer createCustomer(Customer customer) {
        Customer newCustomer = customerRepository.save(customer);
        return newCustomer;
    }

    public Review addReviewToCustomer(long id, Review review) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
        review.setCustomer(customer);
        Review newReview = reviewRepository.save(review);
        return newReview;
    }

    // - - - GET METHODS
    @Override
    public Customer getCustomer(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
    }

    @Override
    public ArrayList<Customer> getAllCustomers() {
        List<Customer> allCustomers = customerRepository.findAll();
        return (ArrayList<Customer>) allCustomers;
    }

    // searches for first name that contains search term
    @Override
    public ArrayList<Customer> searchCustomer(String firstName) {
        List<Customer> customers = customerRepository.findByFirstNameContaining(firstName);
        return (ArrayList<Customer>) customers;
    }

    // - - - PUT METHODS
    @Override
    public Customer updateCustomer(Long id, Customer customer) {
        // Retrieve the customer from the database.
        Customer customerToUpdate = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));

        // Update the customer retrieved from the database.
        customerToUpdate.setFirstName(customer.getFirstName());
        customerToUpdate.setLastName(customer.getLastName());
        customerToUpdate.setCountry(customer.getCountry());
        customerToUpdate.setAddress(customer.getAddress());
        customerToUpdate.setPostalCode(customer.getPostalCode());
        customerToUpdate.setPhoneNumber(customer.getPhoneNumber());
        customerToUpdate.setEmail(customer.getEmail());

        // Save the updated customer back to the database.
        return customerRepository.save(customerToUpdate);
    }

    // - - - DELETE METHODS
    @Override
    public void deleteCustomer(long id) {
        // findById method allows deleteCustomer to throw exception if no id of
        // such type is avail to be deleted i.e response will be 404 (Expected) instead
        // of 204, to tell client that resource not found
        customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
        customerRepository.deleteById(id);
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