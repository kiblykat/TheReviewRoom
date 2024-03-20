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

import sg.com.smartinventory.entities.Review;

@SpringBootTest
@AutoConfigureMockMvc
public class ReviewControllerTest {
        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper;

        @DisplayName("Create review")
        @Test
        public void createReviewTest() throws Exception {
                // Step 1: Create a Review object
                Review newReview = Review.builder().category("Electronics")
                                .reviewContent("Great smartphone with excellent features. ").rating(5).customerId(101)
                                .productId(201).build();

                // Step 2: Convert the Java object to JSON using ObjectMapper.
                String newReviewAsJSON = objectMapper.writeValueAsString(newReview);

                // Step 3: Build the request.
                RequestBuilder request = MockMvcRequestBuilders.post("/reviews")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(newReviewAsJSON);

                // Step 4: Perform the request and get the response and assert.
                mockMvc.perform(request).andExpect(status().isCreated())
                                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$.category").value("Electronics"))
                                .andExpect(jsonPath("$.rating").value(5));
        }
}