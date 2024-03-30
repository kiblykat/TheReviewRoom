package sg.com.smartinventory.utility;

import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

import sg.com.smartinventory.entities.Customer;
import sg.com.smartinventory.entities.Product;
import sg.com.smartinventory.entities.Review;
import sg.com.smartinventory.entities.User;
import sg.com.smartinventory.entities.UserRole;
import sg.com.smartinventory.repositories.CustomerRepository;
import sg.com.smartinventory.repositories.ProductRepository;
import sg.com.smartinventory.repositories.ReviewRepository;
import sg.com.smartinventory.repositories.UserRepository;
import sg.com.smartinventory.repositories.UserRoleRepository;

@Component
public class DataLoader {
    // Inject all repositories.
    private CustomerRepository customerRepository;
    private ProductRepository productRepository;
    private ReviewRepository reviewRepository;
    private UserRepository userRepository;
    private UserRoleRepository userRoleRepository;
    private PasswordEncoder encoder;

    private Review review;

    // @Autowired
    public DataLoader(CustomerRepository customerRepository, ProductRepository productRepository,
            ReviewRepository reviewRepository, Review review, UserRepository userRepository,
            UserRoleRepository userRoleRepository, PasswordEncoder encoder) {
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.review = review;
        this.encoder = encoder;
    }

    @PostConstruct
    public void loadData() {
        // Clear all data.
        customerRepository.deleteAll();
        productRepository.deleteAll();
        reviewRepository.deleteAll();
        userRepository.deleteAll();
        userRoleRepository.deleteAll();
        // }

        // <<<<<<< SIS-39
        // = = = CUSTOMER DATA = = =
        Customer customer1 = customerRepository
                .save(new Customer("John", "Doe", "USA", "123 Main St", 123456, 12345678,
                        "john.doe@example.com"));
        Customer customer2 = customerRepository
                .save(new Customer("Alice", "Smith", "Canada", "456 Maple Ave", 543210, 98765432,
                        "alice.smith@example.com"));
        Customer customer3 = customerRepository
                .save(new Customer("Michael", "Johnson", "UK", "789 Oak Rd", 567890, 98761234,
                        "michael.johnson@example.com"));
        Customer customer4 = customerRepository
                .save(new Customer("Emily", "Brown", "Australia", "321 Elm St", 135790, 45678912,
                        "emily.brown@example.com"));
        Customer customer5 = customerRepository
                .save(new Customer("David", "Wilson", "Germany", "654 Pine Rd", 987655, 36985214,
                        "david.wilson@example.com"));
        // =======
        // public void generateFakeData() {
        // // Create fake data.
        // customerRepository.save(new Customer("John", "Doe", "USA", "123 Main St",
        // 123456, 12345678,
        // "john.doe@example.com"));
        // customerRepository.save(new Customer("Alice", "Smith", "Canada", "456 Maple
        // Ave", 543210, 98765432,
        // "alice.smith@example.com"));
        // customerRepository.save(new Customer("Michael", "Johnson", "UK", "789 Oak
        // Rd", 567890, 98761234,
        // "michael.johnson@example.com"));
        // customerRepository.save(new Customer("Emily", "Brown", "Australia", "321 Elm
        // St", 135790, 45678912,
        // "emily.brown@example.com"));
        // customerRepository.save(new Customer("David", "Wilson", "Germany", "654 Pine
        // Rd", 987655, 36985214,
        // "david.wilson@example.com"));
        // >>>>>>> main

        // = = = PRODUCT = = =
        Product product1 = productRepository.save(new Product("Electronics", "Smartphone",
                "High-end smartphone with advanced features. ", 999.99, 100));
        Product product2 = productRepository.save(new Product("Clothing", "Men's T-Shirt",
                "Comfortable cotton t-shirt for everyday wear. ", 29.99, 500));
        Product product3 = productRepository.save(new Product("Home & Kitchen", "Coffee Maker",
                "Automatic coffee maker with programmable settings. ", 49.99, 50));
        Product product4 = productRepository.save(new Product("Beauty", "Perfume",
                "Elegant fragrance with floral and citrus notes. ", 79.99, 200));
        Product product5 = productRepository.save(new Product("Books", "Science Fiction Novel",
                "Bestselling sci-fi novel set in a dystopian future. ", 14.99, 300));

        // = = = REVIEW DATA = = =
        // Adding review data (note that the "same" review object is being used in all,
        // this is because of Bean injection so its actually diff instances)
        Review review = new Review("Books", "Expected more from the ending, felt rushed. ", 3, 1, 5);
        review.setCustomer(customer1);
        review.setProduct(product5);
        reviewRepository.save(review);

        review = new Review("Electronics", "Fast delivery, product works as expected. ", 4, 1, 1);
        review.setCustomer(customer2);
        review.setProduct(product1);
        reviewRepository.save(review);

        review = new Review("Home & Kitchen", "Difficult to assemble, but sturdy once done. ", 3, 2, 3);
        review.setCustomer(customer3);
        review.setProduct(product3);
        reviewRepository.save(review);

        review = new Review("Electronics", "Great smartphone with excellent features. ", 5, 3, 1);
        review.setCustomer(customer4);
        review.setProduct(product1);
        reviewRepository.save(review);

        review = new Review("Clothing", "The color faded after a few washes. ", 2, 3, 2);
        review.setCustomer(customer5);
        review.setProduct(product2);
        reviewRepository.save(review);

        review = (new Review("Beauty", "Lovely fragrance, long-lasting. ", 5, 4, 4));
        review.setCustomer(customer1);
        review.setProduct(product4);
        reviewRepository.save(review);

        review = (new Review("Home & Kitchen", "Makes delicious coffee, easy to use. ", 4, 4, 3));
        review.setCustomer(customer2);
        review.setProduct(product3);
        reviewRepository.save(review);

        review = (new Review("Books", "Intriguing plot, couldn't put it down. ", 5, 5, 5));
        review.setCustomer(customer3);
        review.setProduct(product5);
        reviewRepository.save(review);

        review = (new Review("Beauty", "Disappointed with the scent, doesn't last long. ", 2, 5, 4));
        review.setCustomer(customer4);
        review.setProduct(product4);
        reviewRepository.save(review);

        // = = = NOTE: DATALOADER DONT WORK FOR ENTITIES WITH FK* (cant specify
        // customer_id column) = = =

    }
}