package review.analyser.com.amazonalexa.serviceTest;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import review.analyser.com.amazonalexa.model.Reviews;
import review.analyser.com.amazonalexa.repository.ReviewsRepository;
import review.analyser.com.amazonalexa.service.ReviewService;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness=Strictness.LENIENT)
public class ReviewServiceTest {

	@InjectMocks
	ReviewService reviewService;
	@Mock
	ReviewsRepository mockReviewsRepository;
	

	@Test
	void acceptsReviewsTest() throws Exception{
		List<Reviews> reviewsList = new ArrayList<>();
		Reviews reviews = new Reviews();
		reviews.setAuthor("abs");
		reviews.setId(78);
		reviews.setRating(8);
		reviews.setReview("Excellent");
		
		Reviews reviews1 = new Reviews();
		reviews1.setAuthor("abs");
		reviews1.setId(78);
		reviews1.setRating(8);
		reviews1.setReview("poor");
		reviewsList.add(reviews1);
		reviewsList.add(reviews);
		

		when(mockReviewsRepository.findAll()).thenReturn(reviewsList);
		reviewService.acceptsReviews(reviews);
		verify(mockReviewsRepository).save(reviews);

	}

	@Test
	void fetchReviewsTest() throws Exception{
		List<Reviews> reviewsList = new ArrayList<>();
		Reviews reviews = new Reviews();
		reviews.setAuthor("abs");
		reviews.setId(78);
		reviews.setRating(8);
		reviews.setReview("Excellent");
		
		Reviews reviews1 = new Reviews();
		reviews1.setAuthor("abs");
		reviews1.setId(78);
		reviews1.setRating(8);
		reviews1.setReview("poor");
		reviewsList.add(reviews1);
		reviewsList.add(reviews);
		

		when(mockReviewsRepository.findAll()).thenReturn(reviewsList);
		reviewService.fetchReviews();
		verify(mockReviewsRepository,times(1)).findAll();

	}
}
