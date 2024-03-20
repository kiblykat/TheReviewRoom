package sg.com.smartinventory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SmartInventoryApplication {
	// Name this according to your class name.
	private static final Logger app_logger = LoggerFactory.getLogger(SmartInventoryApplication.class);

	public static void main(String[] args) {
		app_logger.info("Starting SmartInventoryApplication. ");

		SpringApplication.run(SmartInventoryApplication.class, args);

		app_logger.info("Exiting SmartInventoryApplication. ");
	}
}