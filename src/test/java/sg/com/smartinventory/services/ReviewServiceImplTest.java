package sg.com.smartinventory.services;

import static sg.com.smartinventory.utility.Utility.*;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    /// Name this according to your class name.
    // The Logback library defines 5 log levels in order of priority: TRACE, DEBUG,
    // INFO, WARN, ERROR, with each of these having a corresponding logging method:
    // trace(), debug(), info(), warn(), error().
    private static final Logger test_logger = LoggerFactory.getLogger(ReviewServiceImplTest.class);

    // Test Setup and Teardown configuration.
    @BeforeAll
    static void initAll() {

    }

    @AfterAll
    static void teardownAll() {

    }

    @BeforeEach
    void init() {

    }

    @AfterEach
    void teardown() {

    }

    @Test
    public void createReviewTest() {
        test_logger.info("Starting test: " + getCurrentMethodName() + ". ");

        // 1. SETUP.
        // Create a new review.
        Review review = Review.builder().category("Electronics")
                .reviewContent("Great smartphone with excellent features. ").rating(5)
                .productId(2).build();

        // Mock the save method of the review repository.
        when((reviewRepository.save(review))).thenReturn(review);

        // 2. EXECUTE.
        Review savedReview = reviewService.createReview(review);

        // 3. ASSERT.
        assertEquals(review, savedReview, "The saved review should be the same as the new review created. ");

        // Verify that the save method of the review repository is called once only.
        verify(reviewRepository, times(1)).save(review);

        test_logger.info("Ending test: " + getCurrentMethodName() + ". ");
    }
}