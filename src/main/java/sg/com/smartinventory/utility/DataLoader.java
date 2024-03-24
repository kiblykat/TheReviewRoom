package sg.com.smartinventory.utility;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

import sg.com.smartinventory.entities.Customer;
import sg.com.smartinventory.entities.Product;
import sg.com.smartinventory.entities.Review;
import sg.com.smartinventory.repositories.CustomerRepository;
import sg.com.smartinventory.repositories.ProductRepository;
import sg.com.smartinventory.repositories.ReviewRepository;

@Component
public class DataLoader {
        // Inject all repositories.
        private CustomerRepository customerRepository;
        private ProductRepository productRepository;
        private ReviewRepository reviewRepository;

        // @Autowired
        public DataLoader(CustomerRepository customerRepository, ProductRepository productRepository,
                        ReviewRepository reviewRepository) {
                this.customerRepository = customerRepository;
                this.productRepository = productRepository;
                this.reviewRepository = reviewRepository;
        }

        @PostConstruct
        public void loadData() {
                // Clear all data.
                customerRepository.deleteAll();
                productRepository.deleteAll();
                reviewRepository.deleteAll();

                // Create fake data.
                customerRepository.save(new Customer("John", "Doe", "USA", "123 Main St", 123456, 12345678,
                                "john.doe@example.com"));
                customerRepository.save(new Customer("Alice", "Smith", "Canada", "456 Maple Ave", 543210, 98765432,
                                "alice.smith@example.com"));
                customerRepository.save(new Customer("Michael", "Johnson", "UK", "789 Oak Rd", 567890, 98761234,
                                "michael.johnson@example.com"));
                customerRepository.save(new Customer("Emily", "Brown", "Australia", "321 Elm St", 135790, 45678912,
                                "emily.brown@example.com"));
                customerRepository.save(new Customer("David", "Wilson", "Germany", "654 Pine Rd", 987655, 36985214,
                                "david.wilson@example.com"));

                productRepository.save(new Product("Electronics", "Smartphone",
                                "High-end smartphone with advanced features. ", 999.99, 100));
                productRepository.save(new Product("Clothing", "Men's T-Shirt",
                                "Comfortable cotton t-shirt for everyday wear. ", 29.99, 500));
                productRepository.save(new Product("Home & Kitchen", "Coffee Maker",
                                "Automatic coffee maker with programmable settings. ", 49.99, 50));
                productRepository.save(new Product("Beauty", "Perfume",
                                "Elegant fragrance with floral and citrus notes. ", 79.99, 200));
                productRepository.save(new Product("Books", "Science Fiction Novel",
                                "Bestselling sci-fi novel set in a dystopian future. ", 14.99, 300));

                reviewRepository.save(new Review("Books", "Expected more from the ending, felt rushed. ",
                                3, 1, 1));
                reviewRepository.save(new Review("Electronics", "Fast delivery, product works as expected. ",
                                4, 1, 2));
                reviewRepository.save(new Review("Clothing", "Very comfortable t-shirt, fits perfectly. ",
                                4, 2, 2));
                reviewRepository.save(new Review("Home & Kitchen", "Difficult to assemble, but sturdy once done. ",
                                3, 2, 4));
                reviewRepository.save(new Review("Electronics", "Great smartphone with excellent features. ",
                                5, 3, 1));
                reviewRepository.save(new Review("Clothing", "The color faded after a few washes. ",
                                2, 3, 3));
                reviewRepository.save(new Review("Beauty", "Lovely fragrance, long-lasting. ",
                                5, 4, 4));
                reviewRepository.save(new Review("Home & Kitchen", "Makes delicious coffee, easy to use. ",
                                4, 4, 3));
                reviewRepository.save(new Review("Books", "Intriguing plot, couldn't put it down. ",
                                5, 5, 5));
                reviewRepository.save(new Review("Beauty", "Disappointed with the scent, doesn't last long. ",
                                2, 5, 5));
        }
}