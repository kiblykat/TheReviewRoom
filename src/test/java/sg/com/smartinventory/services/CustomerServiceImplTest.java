package sg.com.smartinventory.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;

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

    @Test
    public void createCustomerTest() {

        // 1. SETUP
        // Create a new customer.
        Customer customer = Customer.builder().firstName("John").lastName("Wick").country("USA").address("123 Main St")
                .postalCode(123456).mobileNumber(12345678).email("john.wick@example.com").reviewId(111).build();

        // Mock the save method of the customer repository.
        when((customerRepository.save(customer))).thenReturn(customer);

        // 2. EXECUTE.
        Customer savedCustomer = customerService.createCustomer(customer);

        // 3. ASSERT.
        assertEquals(customer, savedCustomer, "The saved customer should be the same as the new customer created. ");

        // Verify that the save method of the customer repository is called once only.
        verify(customerRepository, times(1)).save(customer);
    }
}