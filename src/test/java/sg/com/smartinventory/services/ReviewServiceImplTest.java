package sg.com.smartinventory.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.boot.test.context.SpringBootTest;

import sg.com.smartinventory.entities.Review;
import sg.com.smartinventory.repositories.ReviewRepository;
import sg.com.smartinventory.serviceImpls.ReviewServiceImpl;

@SpringBootTest
public class ReviewServiceImplTest {
    @Mock
    private ReviewRepository reviewRepository;

    @InjectMocks
    ReviewServiceImpl reviewService;

    @Test
    public void createReviewTest() {

        // 1. SETUP
        // Create a new review.
        Review review = Review.builder().category("Electronics")
                .reviewContent("Great smartphone with excellent features. ").rating(5).customerId(1)
                .productId(2).build();

        // Mock the save method of the review repository.
        when((reviewRepository.save(review))).thenReturn(review);

        // 2. EXECUTE.
        Review savedReview = reviewService.createReview(review);

        // 3. ASSERT.
        assertEquals(review, savedReview, "The saved review should be the same as the new review created. ");

        // Verify that the save method of the review repository is called once only.
        verify(reviewRepository, times(1)).save(review);
    }
}