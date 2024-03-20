package sg.com.smartinventory.serviceImpls;

import org.springframework.stereotype.Service;

import sg.com.smartinventory.entities.Review;
import sg.com.smartinventory.repositories.ReviewRepository;
import sg.com.smartinventory.services.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {
    private ReviewRepository reviewRepository;

    // @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Review createReview(Review review) {
        Review newReview = reviewRepository.save(review);

        return newReview;
    }
}