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

import sg.com.smartinventory.entities.Product;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class ProductControllerTest {
        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper;

        /// Name this according to your class name.
        // The Logback library defines 5 log levels in order of priority: TRACE, DEBUG,
        // INFO, WARN, ERROR, with each of these having a corresponding logging method:
        // trace(), debug(), info(), warn(), error().
        private static final Logger test_logger = LoggerFactory.getLogger(ProductControllerTest.class);

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

        @DisplayName("Create Product")
        @Test
        public void createProductTest() throws Exception {
                test_logger.info("Starting test: " + getCurrentMethodName() + ". ");

                // Step 1: Create a Product object.
                Product newProduct = Product.builder().category("Electronics").name("Smartphone")
                                .description("High-end smartphone with advanced features. ")
                                .price(999.99).stockQuantity(100).build();

                // Step 2: Convert the Java object to JSON using ObjectMapper.
                String newProductAsJSON = objectMapper.writeValueAsString(newProduct);

                // Step 3: Build the request.
                RequestBuilder request = MockMvcRequestBuilders.post("/products")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(newProductAsJSON);

                // Step 4: Perform the request and get the response and assert.
                mockMvc.perform(request)
                                .andDo(print())
                                .andExpect(status().isCreated())
                                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$.category").value("Electronics"))
                                .andExpect(jsonPath("$.name").value("Smartphone"));

                test_logger.info("Ending test: " + getCurrentMethodName() + ". ");
        }

        @DisplayName("Get All Products")
        @Test
        public void getAllProductsTest() throws Exception {
                test_logger.info("Starting test: " + getCurrentMethodName() + ". ");

                // Step 1: Create the test objects.
                Product newProduct = Product.builder().category("Electronics").name("Smartphone")
                                .description("High-end smartphone with advanced features. ")
                                .price(999.99).stockQuantity(100).build();

                Product newProduct2 = Product.builder().category("Clothing").name("Men's T-Shirt")
                                .description("Comfortable cotton t-shirt for everyday wear. ")
                                .price(29.99).stockQuantity(500).build();

                // Step 2: Convert the Java objects to JSON using ObjectMapper.
                String newProductAsJSON = objectMapper.writeValueAsString(newProduct);
                String newProductAsJSON2 = objectMapper.writeValueAsString(newProduct2);

                // Step 3: Build the request.
                RequestBuilder request = MockMvcRequestBuilders.post("/products")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(newProductAsJSON);

                RequestBuilder request2 = MockMvcRequestBuilders.post("/products")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(newProductAsJSON2);

                RequestBuilder request3 = MockMvcRequestBuilders.get("/products")
                                .contentType(MediaType.APPLICATION_JSON);

                // Step 4: Perform the request and get the response and assert.
                mockMvc.perform(request)
                                .andDo(print())
                                .andExpect(status().isCreated())
                                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$.category").value("Electronics"))
                                .andExpect(jsonPath("$.name").value("Smartphone"));

                mockMvc.perform(request2)
                                .andDo(print())
                                .andExpect(status().isCreated())
                                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$.category").value("Clothing"))
                                .andExpect(jsonPath("$.name").value("Men's T-Shirt"));

                mockMvc.perform(request3)
                                .andDo(print())
                                .andExpect(status().isOk())
                                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$[0].category").value("Electronics"))
                                .andExpect(jsonPath("$[0].name").value("Smartphone"))
                                .andExpect(jsonPath("$[1].category").value("Clothing"))
                                .andExpect(jsonPath("$[1].name").value("Men's T-Shirt"));

                test_logger.info("Ending test: " + getCurrentMethodName() + ". ");
        }

        @DisplayName("Get One Product")
        @Test
        public void getOneProductsTest() throws Exception {
                test_logger.info("Starting test: " + getCurrentMethodName() + ". ");

                // Step 1: Create the test objects.
                Product newProduct = Product.builder().category("Electronics").name("Smartphone")
                                .description("High-end smartphone with advanced features. ")
                                .price(999.99).stockQuantity(100).build();

                // Step 2: Convert the Java objects to JSON using ObjectMapper.
                String newProductAsJSON = objectMapper.writeValueAsString(newProduct);

                // Step 3: Build the request.
                RequestBuilder request = MockMvcRequestBuilders.post("/products")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(newProductAsJSON);

                RequestBuilder request2 = MockMvcRequestBuilders.get("/products/{uuid}", "1")
                                .contentType(MediaType.APPLICATION_JSON);

                // Step 4: Perform the request and get the response and assert.
                mockMvc.perform(request)
                                .andDo(print())
                                .andExpect(status().isCreated())
                                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$.category").value("Electronics"))
                                .andExpect(jsonPath("$.name").value("Smartphone"));

                mockMvc.perform(request2)
                                .andDo(print())
                                .andExpect(status().isOk())
                                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$.category").value("Electronics"))
                                .andExpect(jsonPath("$.name").value("Smartphone"));

                test_logger.info("Ending test: " + getCurrentMethodName() + ". ");
        }
}