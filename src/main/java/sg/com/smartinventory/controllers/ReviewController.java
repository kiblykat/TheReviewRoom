package sg.com.smartinventory.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import sg.com.smartinventory.entities.Review;
import sg.com.smartinventory.services.ReviewService;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private ReviewService reviewService;

    // @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // CREATE.
    @PostMapping("")
    public ResponseEntity<Review> createReview(@Valid @RequestBody Review review) {
        // if(bindingResult.hasErrors()) {
        // return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        // }

        Review newReview = reviewService.createReview(review);
        return new ResponseEntity<>(newReview, HttpStatus.CREATED);
    }
}