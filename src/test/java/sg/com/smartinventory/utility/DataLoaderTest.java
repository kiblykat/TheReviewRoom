package sg.com.smartinventory.utility;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;

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

    @DisplayName("DataLoader Test")
    @Test
    public void dataLoaderTest() throws Exception {

    }
}