package sg.com.smartinventory.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
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

        /**
         * Convert JSON Result to object.
         * 
         * @param result The contents.
         * @param tClass The expected object class.
         * @return The result as a class object.
         * @throws Exception
         */
        public <T> T fromJsonResult(MvcResult result, Class<T> tClass) throws Exception {
                return this.objectMapper.readValue(result.getResponse().getContentAsString(), tClass);
        }

        /**
         * Convert object to JSON bytes.
         * 
         * @param object The object to JSON-ify.
         * @return Byte array with JSON representation.
         * @throws Exception
         */
        public byte[] toJson(Object object) throws Exception {
                return this.objectMapper.writeValueAsString(object).getBytes();
        }

        /// Name this according to your class name.
        // The Logback library defines 5 log levels in order of priority: TRACE, DEBUG,
        // INFO, WARN, ERROR, with each of these having a corresponding logging method:
        // trace(), debug(), info(), warn(), error().
        private static final Logger test_logger = LoggerFactory.getLogger(CustomerControllerTest.class);

        @DisplayName("Create review")
        @Test
        public void createReviewTest() throws Exception {
                test_logger.info("Starting test: createReviewTest. ");

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
                test_logger.info("Ending test: createReviewTest. ");
        }
}