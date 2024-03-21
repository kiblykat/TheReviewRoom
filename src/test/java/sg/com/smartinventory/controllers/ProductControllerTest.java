package sg.com.smartinventory.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import sg.com.smartinventory.entities.Product;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {
        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper;

        @DisplayName("Create product")
        @Test
        public void createProductTest() throws Exception {
                // Step 1: Create a Product object
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
                mockMvc.perform(request).andExpect(status().isCreated())
                                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$.category").value("Electronics"))
                                .andExpect(jsonPath("$.name").value("Smartphone"));
        }
}