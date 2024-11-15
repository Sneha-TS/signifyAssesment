package review.analyser.com.amazonalexa.resource;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import review.analyser.com.amazonalexa.model.RatingsCategory;
import review.analyser.com.amazonalexa.model.ReviewRequest;
import review.analyser.com.amazonalexa.model.Reviews;
import review.analyser.com.amazonalexa.service.ReviewsServices;

@RestController
@Slf4j
public class ReviewsResource {

	public static final String REVIEW = "/review";

	@Autowired
	ReviewsServices reviewServices;

	/* Accepts reviews and stores them */

	@PostMapping(REVIEW)
	public String acceptReviews(@RequestBody Reviews reviews) {
		reviewServices.acceptsReviews(reviews);
		return "YOUR REVIEWS HAS BEEN ACCEPTED";

	}

	/*
	 * Reviews can be filtered by date, store type or rating. All filters are
	 * optional; fetch all reviews if no filters are applied.
	 */

	@GetMapping("/fetchReviews")
	public List<Reviews> fetchReviews(@RequestParam(value = "reviewSource", required = false) String reviewSource,
			@RequestParam(value = "date", required = false) LocalDate localdate,
			@RequestParam(value = "rating", required = false, defaultValue = "0") int rating) {

		ReviewRequest reviewRequest = ReviewRequest.builder().rating(rating).reviewedDate(localdate)
				.reviewSource(reviewSource).build();
		reviewServices.fetchReviewsByFilters(reviewRequest);

		return reviewServices.fetchReviewsByFilters(reviewRequest);
	}

	// Allows to get average monthly ratings per store.

	@GetMapping("/fetchAverageMonthlyRatings/{reviewSource}")
	public double averageRatingsPerStore(@PathVariable(value = "reviewSource", required = false) String reviewSource) {
		return reviewServices.fetchAverageMonthlyRatingsBySource(reviewSource);
	}

	// Allows to get total ratings for each category. Meaning, how many 5*, 4*, 3*
	// and so on
	@GetMapping("/fetchReviewsByCategory")
	public List<RatingsCategory> getRatingsBasedOnCategory() {

		return reviewServices.fetchReviewsByCategory();

	}

}
