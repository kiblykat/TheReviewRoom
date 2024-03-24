package sg.com.smartinventory.entities;

import static sg.com.smartinventory.utility.Utility.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sg.com.smartinventory.repositories.ReviewRepository;
// import sg.com.smartinventory.serviceImpls.ReviewServiceImpl;

public class ReviewTest {
    @Mock
    private ReviewRepository reviewRepository;

    // @InjectMocks
    // ReviewServiceImpl reviewService;

    /// Name this according to your class name.
    // The Logback library defines 5 log levels in order of priority: TRACE, DEBUG,
    // INFO, WARN, ERROR, with each of these having a corresponding logging method:
    // trace(), debug(), info(), warn(), error().
    private static final Logger test_logger = LoggerFactory.getLogger(ReviewTest.class);

    // Test Setup and Teardown configuration.
    @BeforeEach
    void init() {

    }

    @AfterEach
    void teardown() {

    }

    @DisplayName("Review Test")
    @Test
    public void reviewTest() throws Exception {
        test_logger.info("Starting test: " + getCurrentMethodName() + ". ");

        test_logger.info("Ending test: " + getCurrentMethodName() + ". ");
    }
}