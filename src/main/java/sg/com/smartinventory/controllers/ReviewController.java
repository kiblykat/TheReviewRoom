package sg.com.smartinventory.controllers;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    // - - - NO POST HERE, due to data integrity with Customer FK - - -
    // @PostMapping("")...

    // READ (GET ALL).
    @GetMapping("")
    public ResponseEntity<ArrayList<Review>> getAllReviews() {
        ArrayList<Review> allReviews = reviewService.getAllReviews();
        return new ResponseEntity<>(allReviews, HttpStatus.OK);
    }

    // READ (GET ONE).
    @GetMapping("/{id}")
    public ResponseEntity<Review> getReview(@PathVariable long id) {
        Review foundReview = reviewService.getReview(id);
        return new ResponseEntity<>(foundReview, HttpStatus.OK);
    }

    // UPDATE.
    @PutMapping("/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable long id, @RequestBody Review review) {
        Review updatedReview = reviewService.updateReview(id, review);
        return new ResponseEntity<>(updatedReview, HttpStatus.OK);
    }

    // DELETE.
    @DeleteMapping("/{id}")
    public ResponseEntity<Review> deleteReview(@PathVariable long id) {
        reviewService.deleteReview(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}