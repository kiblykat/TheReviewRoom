package sg.com.smartinventory.utility;

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

import sg.com.smartinventory.repositories.CustomerRepository;
// import sg.com.smartinventory.serviceImpls.CustomerServiceImpl;
import sg.com.smartinventory.repositories.ProductRepository;
// import sg.com.smartinventory.serviceImpls.ProductServiceImpl;
import sg.com.smartinventory.repositories.ReviewRepository;
// import sg.com.smartinventory.serviceImpls.ReviewServiceImpl;

public class DataLoaderTest {
    @Mock
    private CustomerRepository customerRepository;
    private ProductRepository productRepository;
    private ReviewRepository reviewRepository;

    // @InjectMocks
    // CustomerServiceImpl customerService;
    // ProductServiceImpl productService;
    // ReviewServiceImpl reviewService;

    /// Name this according to your class name.
    // The Logback library defines 5 log levels in order of priority: TRACE, DEBUG,
    // INFO, WARN, ERROR, with each of these having a corresponding logging method:
    // trace(), debug(), info(), warn(), error().
    private static final Logger test_logger = LoggerFactory.getLogger(DataLoaderTest.class);

    // Test Setup and Teardown configuration.
    @BeforeEach
    void init() {

    }

    @AfterEach
    void teardown() {

    }

    @DisplayName("DataLoader Test")
    @Test
    public void dataLoaderTest() throws Exception {
        test_logger.info("Starting test: " + getCurrentMethodName() + ". ");

        test_logger.info("Ending test: " + getCurrentMethodName() + ". ");
    }
}