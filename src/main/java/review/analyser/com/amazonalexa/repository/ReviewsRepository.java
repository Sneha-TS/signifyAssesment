package review.analyser.com.amazonalexa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import review.analyser.com.amazonalexa.model.Reviews;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


public interface ReviewsRepository extends JpaRepository<Reviews,Integer>{

	List<Reviews> findByRating(int rating);
	List<Reviews> findByReviewSource(String reviewSource);
	List<Reviews> findByReviewedDate(LocalDateTime reviewedDate);
	List<Reviews> findByReviewSourceOrRatingOrReviewedDate(String reviewSource, int rating, LocalDate localDate);
	
}
