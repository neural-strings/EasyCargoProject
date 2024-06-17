package org.easyCargoProject.org;

import org.easyCargoProject.org.config.Initializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EasyCargoProjectApplication {
	private static Logger logger = LoggerFactory.getLogger(Initializer.class);
	public static void main(String[] args) {
		SpringApplication.run(EasyCargoProjectApplication.class, args);
		logger.debug("Start TEST");
	}
}
