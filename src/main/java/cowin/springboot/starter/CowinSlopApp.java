package cowin.springboot.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import cowin.slots.CheckSlotController;

@SpringBootApplication
@ComponentScan(basePackageClasses = CheckSlotController.class)
public class CowinSlopApp {
	
	public static void main(String[] args) {
		SpringApplication.run(CowinSlopApp.class, args);
	}

}
