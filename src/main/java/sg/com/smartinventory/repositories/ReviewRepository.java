package sg.com.smartinventory.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.com.smartinventory.entities.Review;

// ReviewRepository simply extends JpaRepository, giving us a lot of default methods to access the database without the hard work. 
// This is a very powerful feature of Spring Data JPA. It will automatically do a property check and traverse the supported nested properties, translating them into the relevant queries. 
// We can also create custom queries using the JPA criteria API by simply creating a method with a certain naming convention specified by the query creation mechanism.
public interface ReviewRepository extends JpaRepository<Review, Long> {
    // Custom query to find all reviews with a certain ID.
    // List<Review> findById(long id);

    // Custom query to find all reviews with a certain rating.
    List<Review> findByRating(int rating);

    // Custom query to find all reviews with a certain category.
    List<Review> findByCategory(String category);

    // Custom query to find all reviews with a certain review content.
    List<Review> findByReviewContent(String reviewContent);

    // Custom query to find all reviews with a certain customer ID.
    List<Review> findByCustomerId(long customerId);

    // Custom query to find all reviews with a certain product ID.
    List<Review> findByProductId(long productId);

    // Custom query to find all reviews with a certain customer ID and product ID.
    List<Review> findByCustomerIdAndProductId(long customerId, long productId);
}