package review.analyser.com.amazonalexa.resource;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import review.analyser.com.amazonalexa.model.Reviews;
import review.analyser.com.amazonalexa.service.ReviewsServices;

@ExtendWith(MockitoExtension.class)
public class ReviewsResourceTest extends BaseTest {

	@InjectMocks
	ReviewsResource reviewsResource;

	@Mock
	ReviewsServices reviewsServices;

	@Test
	void testAcceptReviews() throws Exception {
		String uri = "/review";
		Reviews reviews = new Reviews();
		reviews.setAuthor("ABCssss");
		reviews.setProductName("amazon alexa");
		reviews.setRating(5);
		String inputJson = super.mapToJson(reviews);

		reviewsResource.acceptReviews(reviews);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);

	}

	@Test
	void testfetchReviews() throws Exception {
		String uri = "/fetchReviews?rating=4";
		List<Reviews> listReviews = new ArrayList<>();
		Reviews reviews = new Reviews();
		reviews.setAuthor("ABCssss");
		reviews.setProductName("amazon alexa");
		reviews.setRating(5);
		Reviews reviews1 = new Reviews();
		reviews.setAuthor("ABCssss");
		reviews.setProductName("amazon alexa");
		reviews.setRating(5);
		listReviews.add(reviews1);
		listReviews.add(reviews1);
	  	reviewsResource.fetchReviews("abc", LocalDate.now(), 5);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertFalse(content.isEmpty());
	}
	@Test
	void testaverageRatingsPerStore() throws Exception {
		String uri = "/fetchAverageMonthlyRatings/mobile";
		List<Reviews> listReviews = new ArrayList<>();
		Reviews reviews = new Reviews();
		reviews.setAuthor("ABCssss");
		reviews.setProductName("amazon alexa");
		reviews.setReviewSource("web");
		reviews.setRating(5);
		Reviews reviews1 = new Reviews();
		reviews1.setAuthor("ABCssss");
		reviews1.setProductName("amazon alexa");
		reviews.setReviewSource("mobile");
		reviews1.setRating(5);
		listReviews.add(reviews);
		listReviews.add(reviews1);
	  	reviewsResource.averageRatingsPerStore("mobile");
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
	}

}
