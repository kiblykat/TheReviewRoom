package sg.com.smartinventory.services;

import static sg.com.smartinventory.utility.Utility.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.test.context.SpringBootTest;

import sg.com.smartinventory.entities.Customer;
import sg.com.smartinventory.repositories.CustomerRepository;
import sg.com.smartinventory.serviceImpls.CustomerServiceImpl;

@SpringBootTest
public class CustomerServiceImplTest {
    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    CustomerServiceImpl customerService;

    /// Name this according to your class name.
    // The Logback library defines 5 log levels in order of priority: TRACE, DEBUG,
    // INFO, WARN, ERROR, with each of these having a corresponding logging method:
    // trace(), debug(), info(), warn(), error().
    private static final Logger test_logger = LoggerFactory.getLogger(CustomerServiceImplTest.class);

    // Test Setup and Teardown configuration.
    @BeforeEach
    void init() {

    }

    @AfterEach
    void teardown() {

    }

    @Test
    public void createCustomerTest() {
        test_logger.info("Starting test: " + getCurrentMethodName() + ". ");

        // 1. SETUP.
        // Create a new customer.
        Customer customer = Customer.builder().firstName("John").lastName("Wick").country("USA").address("123 Main St")
                .postalCode(123456).phoneNumber(12345678).email("john.wick@example.com").build();

        // Mock the save method of the customer repository.
        when((customerRepository.save(customer))).thenReturn(customer);

        // 2. EXECUTE.
        Customer savedCustomer = customerService.createCustomer(customer);

        // 3. ASSERT.
        assertEquals(customer, savedCustomer, "The saved customer should be the same as the new customer created. ");

        // Verify that the save method of the customer repository is called once only.
        verify(customerRepository, times(1)).save(customer);

        test_logger.info("Ending test: " + getCurrentMethodName() + ". ");
    }
}