package sg.com.smartinventory.integration;

import static sg.com.smartinventory.utility.Utility.*;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import sg.com.smartinventory.entities.Customer;
import sg.com.smartinventory.repositories.CustomerRepository;
import sg.com.smartinventory.serviceImpls.CustomerServiceImpl;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class IntegrationTest {
        @Mock
        private CustomerRepository customerRepository;

        @InjectMocks
        CustomerServiceImpl customerService;

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper;

        /// Name this according to your class name.
        // The Logback library defines 5 log levels in order of priority: TRACE, DEBUG,
        // INFO, WARN, ERROR, with each of these having a corresponding logging method:
        // trace(), debug(), info(), warn(), error().
        private static final Logger test_logger = LoggerFactory.getLogger(IntegrationTest.class);

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

        @DisplayName("Integration Test 1")
        @Test
        public void createCustomerTest() {
                test_logger.info("Starting test: " + getCurrentMethodName() + ". ");

                // 1. Setup.
                Customer testObject1 = Customer.builder().firstName("Jackie").lastName("Chan").country("Hong Kong")
                                .address("123 HK St").postalCode(654321).phoneNumber(87654321)
                                .email("jackie.chan@example.com").build();

                // Mock the save method of the customer repository.
                when((customerRepository.save(testObject1))).thenReturn(testObject1);

                // 2. Execution.
                Customer savedObject1 = customerService.createCustomer(testObject1);

                // 3. Assertion.
                assertEquals(testObject1, savedObject1, "The saved object should be the same as the new object.");

                // Verify that the save method of the customer repository is called once.
                verify(customerRepository, times(1)).save(testObject1);

                test_logger.info("Ending test: " + getCurrentMethodName() + ". ");
        }

        @DisplayName("Integration Test 2")
        @Test
        public void runIntegratedTest() throws Exception {
                test_logger.info("Starting test: " + getCurrentMethodName() + ". ");

                // Step 1: Create the test objects.
                Customer testObject1 = Customer.builder().firstName("Jackie").lastName("Chan").country("Hong Kong")
                                .address("123 HK St").postalCode(654321).phoneNumber(87654321)
                                .email("jackie.chan@example.com").build();

                Customer testObject2 = Customer.builder().firstName("Jack").lastName("Chang").country("China")
                                .address("321 HK St").postalCode(123456).phoneNumber(12345678)
                                .email("jack.chang@example.com").build();

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
                mockMvc.perform(request)
                                .andDo(print())
                                .andExpect(status().isCreated())
                                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$.firstName").value("Jackie"))
                                .andExpect(jsonPath("$.lastName").value("Chan"));

                mockMvc.perform(request2)
                                .andDo(print())
                                .andExpect(status().isCreated())
                                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$.firstName").value("Jack"))
                                .andExpect(jsonPath("$.lastName").value("Chang"));

                test_logger.info("Ending test: " + getCurrentMethodName() + ". ");
        }
}