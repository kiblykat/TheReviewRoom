package sg.com.smartinventory.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import sg.com.smartinventory.entities.Customer;
import sg.com.smartinventory.repositories.CustomerRepository;
import sg.com.smartinventory.serviceImpls.CustomerServiceImpl;

@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationTest {
        @Mock
        private CustomerRepository customerRepository;

        @InjectMocks
        CustomerServiceImpl customerService;

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper;

        @DisplayName("Integration Test 1")
        @Test
        public void createCustomerTest() {
                // 1. Setup.
                Customer testObject1 = Customer.builder().firstName("Jackie").lastName("Chan").country("Hong Kong")
                                .address("123 HK St")
                                .postalCode(654321).mobileNumber(87654321).email("jackie.chan@example.com")
                                .reviewId(110).build();

                // mock the save method of the customer repository
                when((customerRepository.save(testObject1))).thenReturn(testObject1);

                // 2. Execution.
                Customer savedObject1 = customerService.createCustomer(testObject1);

                // 3. Assertion.
                assertEquals(testObject1, savedObject1, "The saved object should be the same as the new object.");

                // Verify that the save method of the customer repository is called once.
                verify(customerRepository, times(1)).save(testObject1);
        }

        @DisplayName("Integration Test 2")
        @Test
        public void runIntegratedTest() throws Exception {
                // Step 1: Create the test objects.
                Customer testObject1 = Customer.builder().firstName("Jackie").lastName("Chan").country("Hong Kong")
                                .address("123 HK St").postalCode(654321).mobileNumber(87654321)
                                .email("jackie.chan@example.com").build();
                Customer testObject2 = Customer.builder().firstName("Jackie").lastName("Chang").country("Hong Kong")
                                .address("123 HK St").postalCode(654321).mobileNumber(87654321)
                                .email("jackie.chang@example.com").build();

                // Step 2: Convert the Java objects to JSON using ObjectMapper.
                String testObject1AsJSON = objectMapper.writeValueAsString(testObject1);
                String testObject2AsJSON = objectMapper.writeValueAsString(testObject2);

                // Step 3: Build the request.
                RequestBuilder request = MockMvcRequestBuilders.post("/customers")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(testObject1AsJSON);
                RequestBuilder request2 = MockMvcRequestBuilders.post("/customers")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(testObject2AsJSON);

                // Step 4: Perform the request and get the response and assert.
                mockMvc.perform(request).andExpect(status().isCreated())
                                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$.firstName").value("Jackie"))
                                .andExpect(jsonPath("$.lastName").value("Chan"));
                mockMvc.perform(request2).andExpect(status().isCreated())
                                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$.firstName").value("Jackie"))
                                .andExpect(jsonPath("$.lastName").value("Chang"));
        }
}