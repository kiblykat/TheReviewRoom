package sg.com.smartinventory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SmartInventoryApplication {
	// Name this according to your class name.
	// The Logback library defines 5 log levels in order of priority: TRACE, DEBUG,
	// INFO, WARN, ERROR, with each of these having a corresponding logging method:
	// trace(), debug(), info(), warn(), error().
	private static final Logger app_logger = LoggerFactory.getLogger(SmartInventoryApplication.class);

	public static void main(String[] args) {
		app_logger.info("Starting Smart Inventory Application initialization... ");

		SpringApplication.run(SmartInventoryApplication.class, args);

		app_logger.info("Smart Inventory Application initialization complete! ");
	}
}