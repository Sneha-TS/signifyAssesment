package review.analyser.com.amazonalexa.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import review.analyser.com.amazonalexa.model.RatingsCategory;
import review.analyser.com.amazonalexa.model.ReviewRequest;
import review.analyser.com.amazonalexa.model.Reviews;
import review.analyser.com.amazonalexa.repository.ReviewsRepository;

@Service
public class ReviewService implements ReviewsServices {

	@Autowired
	ReviewsRepository reviewsRepository;

	@Override
	public Reviews acceptsReviews(Reviews reviews) {
		reviews.setReviewedDate(LocalDateTime.now());
		return reviewsRepository.save(reviews);
	}

	@Override
	public List<Reviews> fetchReviews() {
		return reviewsRepository.findAll();
	}

	@Override
	public List<Reviews> fetchReviewsByFilters(ReviewRequest reviewRequest) {
		if (reviewRequest != null && (reviewRequest.getRating() > 0 || reviewRequest.getReviewedDate() != null
				|| reviewRequest.getReviewSource() != null)) {
			System.out.println("received requests for the reviewRequest : " + reviewRequest.toString());
			System.out.println("Response from JPA " + reviewsRepository.findByReviewSourceOrRatingOrReviewedDate(
					reviewRequest.getReviewSource(), reviewRequest.getRating(), reviewRequest.getReviewedDate()));
			return reviewsRepository.findByReviewSourceOrRatingOrReviewedDate(reviewRequest.getReviewSource(),
					reviewRequest.getRating(), reviewRequest.getReviewedDate());
		} else {
			return reviewsRepository.findAll();
		}
	}

	@Override
	public double fetchAverageMonthlyRatingsBySource(String reviewSource) {
		List<Reviews> reviews = reviewsRepository.findByReviewSource(reviewSource);
		double average = reviews.stream().map(ratings -> ratings.getRating()).mapToInt(Integer::valueOf).average()
				.orElse(0);
		return average;
	}

	@Override
	public List<RatingsCategory> fetchReviewsByCategory() {
		List<Reviews> reviews = reviewsRepository.findAll();
		List<RatingsCategory> ratingsCategoryList = new ArrayList<>() ;
		RatingsCategory ratingsCategory;
		Map<Object, Long> ratingsByCategory = reviews.stream().map(ratings -> ratings.getRating())
				.collect(Collectors.groupingBy(s -> s, Collectors.counting()));
		for (Entry<Object, Long> entry : ratingsByCategory.entrySet()) {
			ratingsCategory = new RatingsCategory(entry.getKey(),entry.getValue());
			if(ratingsCategory!=null) {
			ratingsCategoryList.add(ratingsCategory);}
		}
		return ratingsCategoryList;
	}

}
