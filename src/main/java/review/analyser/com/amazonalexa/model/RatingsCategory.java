package review.analyser.com.amazonalexa.model;


import lombok.*;
import lombok.ToString;

@ToString
@Data
public class RatingsCategory {

	private int rateCategory;
	private long count;
	
	public RatingsCategory(Object object, long count) {
		this.rateCategory = (int) object;
		this.count = count;
	}
	
	
}
