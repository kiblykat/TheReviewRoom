package sg.com.smartinventory.serviceImpls;

import org.springframework.stereotype.Service;

import sg.com.smartinventory.entity.Customer;
import sg.com.smartinventory.repository.CustomerRepository;
import sg.com.smartinventory.services.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;

    // @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        Customer newCustomer = customerRepository.save(customer);

        return newCustomer;
    }
}