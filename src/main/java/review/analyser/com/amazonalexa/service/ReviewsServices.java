package review.analyser.com.amazonalexa.service;

import java.util.List;

import review.analyser.com.amazonalexa.model.RatingsCategory;
import review.analyser.com.amazonalexa.model.ReviewRequest;
import review.analyser.com.amazonalexa.model.Reviews;

public interface ReviewsServices {
	Reviews acceptsReviews(Reviews reviews);

	List<Reviews> fetchReviews();

	List<Reviews> fetchReviewsByFilters(ReviewRequest reviewRequest);

	double fetchAverageMonthlyRatingsBySource(String reviewSource);

	List<RatingsCategory> fetchReviewsByCategory();
}
