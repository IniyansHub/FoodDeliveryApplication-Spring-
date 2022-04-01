package foodDeliveryApp.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"foodDeliveryApp.controllers","foodDeliveryApp.configurations","foodDeliveryApp.Services","foodDeliveryApp.filters","foodDeliveryApp.util"})
@EntityScan("foodDeliveryApp.models")
@EnableJpaRepositories("foodDeliveryApp.repository")
public class FoodDeliveryApp {

	public static void main(String[] args) {
		SpringApplication.run(FoodDeliveryApp.class, args);
	}

}
