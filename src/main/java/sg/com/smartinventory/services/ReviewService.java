package sg.com.smartinventory.services;

import java.util.ArrayList;

import sg.com.smartinventory.entities.Review;

public interface ReviewService {
    Review createReview(Review review);

    Review getReview(Long id);

    ArrayList<Review> getAllReviews();

    Review updateReview(Long id, Review review);

    void deleteReview(long id);

    ArrayList<Review> searchCustomerReviews(long customerId);

    ArrayList<Review> searchProductReviews(long productId);
}