package sg.com.smartinventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.com.smartinventory.entity.Review;

public interface ReviewRepository extends JpaRepository<Review,Long> {
  
}
