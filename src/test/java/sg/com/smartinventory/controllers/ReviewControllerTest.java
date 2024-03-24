package sg.com.smartinventory.controllers;

import static sg.com.smartinventory.utility.Utility.*;

import static org.junit.jupiter.api.Assertions.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

        /// Name this according to your class name.
        // The Logback library defines 5 log levels in order of priority: TRACE, DEBUG,
        // INFO, WARN, ERROR, with each of these having a corresponding logging method:
        // trace(), debug(), info(), warn(), error().
        private static final Logger test_logger = LoggerFactory.getLogger(ReviewControllerTest.class);

        // Test Setup and Teardown configuration.
        @BeforeEach
        void init() {

        }

        @AfterEach
        void teardown() {

        }

        /**
         * Test the creation of a Review.
         * 
         * @throws Exception
         */
        @DisplayName("Create Review")
        @Test
        public void createReviewTest() throws Exception {
                test_logger.info("Starting test: " + getCurrentMethodName() + ". ");

                // Step 1: Create a Review object
                Review newReview = Review.builder().category("Electronics")
                                .reviewContent("Great smartphone with excellent features. ").rating(5).customerId(1)
                                .productId(2).build();

                // Step 2: Convert the Java object to JSON using ObjectMapper.
                String newReviewAsJSON = objectMapper.writeValueAsString(newReview);

                // Step 3: Build the request.
                RequestBuilder request = MockMvcRequestBuilders.post("/reviews")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(newReviewAsJSON);

                // Step 4: Perform the request and get the response and assert.
                mockMvc.perform(request)
                                // MvcResult Result = mockMvc.perform(request)
                                // .andDo(print -> test_logger.error("Starting Request: createReviewTest. "))
                                .andExpect(status().isCreated())
                                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$.category").value("Electronics"))
                                .andExpect(jsonPath("$.rating").value(5));
                // .andDo(print -> test_logger.error("Customer's id: {}",
                // print.getResponse().getContentAsString()))
                // .andDo(print -> test_logger.error("Starting Request: createReviewTest. "))
                // .andReturn();

                // Review ReviewObject = fromJsonResult(Result, Review.class);

                // test_logger.error("Ending test: createReviewTest. ");
                test_logger.info("Ending test: " + getCurrentMethodName() + ". ");
        }
        }
}