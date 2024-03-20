package sg.com.smartinventory.entities;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import sg.com.smartinventory.repositories.CustomerRepository;
import sg.com.smartinventory.serviceImpls.CustomerServiceImpl;

public class CustomerTest {
    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    CustomerServiceImpl customerService;

    @DisplayName("Customer Test")
    @Test
    public void customerTest() throws Exception {

    }
}