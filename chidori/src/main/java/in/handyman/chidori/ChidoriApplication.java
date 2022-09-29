package in.handyman.chidori;

import in.handyman.raven.lambda.access.repo.HandymanRepo;
import in.handyman.raven.lambda.access.repo.HandymanRepoImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ChidoriApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChidoriApplication.class, args);
	}

	@Bean
	public HandymanRepo createHandymanRepoBean() {
		return new HandymanRepoImpl();
	}

}
