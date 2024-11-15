package review.analyser.com.amazonalexa.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;


@Entity
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Reviews {

	@Id
	@GeneratedValue
	private int id;
	
	private String review;
	private String author;
	@Column(name="review_source")
	private String reviewSource;
	private int rating;
	private String title;
	@Column(name="product_name")
	private String productName;
	@Column(name="reviewed_date")
	private LocalDateTime reviewedDate;
}
