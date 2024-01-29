package b612.bicyclecommunity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class BicycleCommunityApplication {

	public static void main(String[] args) {
		SpringApplication.run(BicycleCommunityApplication.class, args);
	}

}
