package review.analyser.com.amazonalexa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("review.analyser.com.amazonalexa.repository")
@ComponentScan("review.analyser.com.amazonalexa.*")
public class AmazonalexaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmazonalexaApplication.class, args);
	}

}
