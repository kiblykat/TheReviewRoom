package sg.com.smartinventory.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;

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

    // READ (all)
    @GetMapping("")
    public ResponseEntity<ArrayList<Customer>> getAllCustomers() {
        ArrayList<Customer> allCustomers = customerService.getAllCustomers();
        return new ResponseEntity<>(allCustomers, HttpStatus.OK);
    }

    // READ (id)
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable long id) {
        Customer foundCustomer = customerService.getCustomer(id);
        return new ResponseEntity<>(foundCustomer, HttpStatus.OK);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable long id, @RequestBody Customer customer) {
        // TODO: process PUT request
        Customer updatedCustomer = customerService.updateCustomer(id, customer);
        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable long id) {
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // // Nested route - Add review to customer.
    // @PostMapping("/{id}/reviews")
    // public ResponseEntity<Review> addReviewToCustomer(@PathVariable Long id,
    // @Valid @RequestBody Review review) {
    // Review newReview = customerService.addReviewToCustomer(id, review);
    // return new ResponseEntity<>(newReview, HttpStatus.CREATED);
    // }
}