package review.analyser.com.amazonalexa.model;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;


@ToString
@Data
public class ReviewRequest {

	private String reviewSource;
	private int rating;
	private LocalDate reviewedDate;
	
	@Builder
	public ReviewRequest(String reviewSource, int rating, LocalDate reviewedDate) {
		this.reviewSource = reviewSource;
		this.rating = rating;
		this.reviewedDate = reviewedDate;
	}
	
	
}
