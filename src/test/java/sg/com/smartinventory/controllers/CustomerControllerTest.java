package sg.com.smartinventory.controllers;

import static sg.com.smartinventory.utility.Utility.*;

import static org.junit.jupiter.api.Assertions.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
import sg.com.smartinventory.entities.Product;
import sg.com.smartinventory.entities.Review;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class CustomerControllerTest {
        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper;

        /// Name this according to your class name.
        // The Logback library defines 5 log levels in order of priority: TRACE, DEBUG,
        // INFO, WARN, ERROR, with each of these having a corresponding logging method:
        // trace(), debug(), info(), warn(), error().
        private static final Logger test_logger = LoggerFactory.getLogger(CustomerControllerTest.class);

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

        @DisplayName("Create Customer")
        @Test
        public void createCustomerTest() throws Exception {
                test_logger.info("Starting test: " + getCurrentMethodName() + ". ");

                // Step 1: Create a Customer object.
                Customer newCustomer = Customer.builder().firstName("Jackie").lastName("Chan").country("Hong Kong")
                                .address("123 HK St").postalCode(654321).phoneNumber(87654321)
                                .email("jackie.chan@example.com").build();

                // Step 2: Convert the Java objects to JSON using ObjectMapper.
                String newCustomerAsJSON = objectMapper.writeValueAsString(newCustomer);

                // Step 3: Build the request.
                RequestBuilder request = MockMvcRequestBuilders.post("/customers")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(newCustomerAsJSON);

                // Step 4: Perform the request and get the response and assert.
                mockMvc.perform(request)
                                .andDo(print())
                                .andExpect(status().isCreated())
                                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$.firstName").value("Jackie"))
                                .andExpect(jsonPath("$.lastName").value("Chan"));

                test_logger.info("Ending test: " + getCurrentMethodName() + ". ");
        }

        @DisplayName("Add Review To Customer")
        @Test
        public void addReviewToCustomerTest() throws Exception {
                test_logger.info("Starting test: " + getCurrentMethodName() + ". ");

                // Step 1: Create the test objects.
                Customer newCustomer = Customer.builder().firstName("Jackie").lastName("Chan").country("Hong Kong")
                                .address("123 HK St").postalCode(654321).phoneNumber(87654321)
                                .email("jackie.chan@example.com").build();

                Product newProduct = Product.builder().category("Electronics").name("Smartphone")
                                .description("High-end smartphone with advanced features. ")
                                .price(999.99).stockQuantity(100).build();

                Review newReview = Review.builder().category("Electronics")
                                .reviewContent("Great smartphone with excellent features. ").rating(5)
                                .customer(newCustomer)
                                .product(newProduct).build();

                // Step 2: Convert the Java object to JSON using ObjectMapper.
                String newCustomerAsJSON = objectMapper.writeValueAsString(newCustomer);
                String newProductAsJSON = objectMapper.writeValueAsString(newProduct);
                String newReviewAsJSON = objectMapper.writeValueAsString(newReview);

                // Step 3: Build the request.
                RequestBuilder request = MockMvcRequestBuilders.post("/customers")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(newCustomerAsJSON);

                RequestBuilder request2 = MockMvcRequestBuilders.post("/products")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(newProductAsJSON);

                RequestBuilder request3 = MockMvcRequestBuilders
                                .post("/customers/{id}/products/{productId}/reviews", "1", "1")
                                .contentType(MediaType.APPLICATION_JSON)
                                // .sessionAttr("review", newReview)
                                // .param("id", "1")
                                .content(newReviewAsJSON);

                // Step 4: Perform the request and get the response and assert.
                mockMvc.perform(request)
                                .andDo(print())
                                .andExpect(status().isCreated());

                mockMvc.perform(request2)
                                .andDo(print())
                                .andExpect(status().isCreated());

                mockMvc.perform(request3).andDo(print())
                                .andExpect(status().isOk())
                                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                // .andExpect(jsonPath("$[0].id").exists())
                                .andExpect(jsonPath("$.category").value("Electronics"))
                                .andExpect(jsonPath("$.rating").value(5));

                test_logger.info("Ending test: " + getCurrentMethodName() + ". ");
        }

        @DisplayName("Get All Customers")
        @Test
        public void getAllCustomersTest() throws Exception {
                test_logger.info("Starting test: " + getCurrentMethodName() + ". ");

                // Step 1: Create the test objects.
                Customer newCustomer = Customer.builder().firstName("Jackie").lastName("Chan").country("Hong Kong")
                                .address("123 HK St").postalCode(654321).phoneNumber(87654321)
                                .email("jackie.chan@example.com").build();

                Customer newCustomer2 = Customer.builder().firstName("Jack").lastName("Chang").country("China")
                                .address("321 HK St").postalCode(123456).phoneNumber(12345678)
                                .email("jack.chang@example.com").build();

                // Step 2: Convert the Java objects to JSON using ObjectMapper.
                String newCustomerAsJSON = objectMapper.writeValueAsString(newCustomer);
                String newCustomerAsJSON2 = objectMapper.writeValueAsString(newCustomer2);

                // Step 3: Build the request.
                RequestBuilder request = MockMvcRequestBuilders.post("/customers")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(newCustomerAsJSON);

                RequestBuilder request2 = MockMvcRequestBuilders.post("/customers")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(newCustomerAsJSON2);

                RequestBuilder request3 = MockMvcRequestBuilders.get("/customers")
                                .contentType(MediaType.APPLICATION_JSON);

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

                mockMvc.perform(request3)
                                .andDo(print())
                                .andExpect(status().isOk())
                                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$[0].firstName").value("Jackie"))
                                .andExpect(jsonPath("$[0].lastName").value("Chan"))
                                .andExpect(jsonPath("$[1].firstName").value("Jack"))
                                .andExpect(jsonPath("$[1].lastName").value("Chang"));

                test_logger.info("Ending test: " + getCurrentMethodName() + ". ");
        }
}