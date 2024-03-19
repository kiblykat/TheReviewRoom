package sg.com.smartinventory.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.com.smartinventory.entities.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

}