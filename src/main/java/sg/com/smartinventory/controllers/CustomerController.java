package sg.com.smartinventory.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import sg.com.smartinventory.entities.Customer;
// import sg.com.smartinventory.entities.Review;
import sg.com.smartinventory.services.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private CustomerService customerService;

    // @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // CREATE.
    @PostMapping("")
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer) {
        // if(bindingResult.hasErrors()) {
        // return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        // }

        Customer newCustomer = customerService.createCustomer(customer);
        return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
    }

    // // Nested route - Add review to customer.
    // @PostMapping("/{id}/reviews")
    // public ResponseEntity<Review> addReviewToCustomer(@PathVariable Long id,
    // @Valid @RequestBody Review review) {
    // Review newReview = customerService.addReviewToCustomer(id, review);
    // return new ResponseEntity<>(newReview, HttpStatus.CREATED);
    // }
}