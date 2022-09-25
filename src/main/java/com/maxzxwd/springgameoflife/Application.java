package com.maxzxwd.springgameoflife;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication()
@EnableScheduling
public class Application {

	@Autowired(required = false)
	private ServletWebServerApplicationContext webServerAppContext;

	private final static Logger logger = LoggerFactory.getLogger(Application.class);

	public static void main(String... args) {
		SpringApplication.run(Application.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void logRunningAddress() {

		if (webServerAppContext != null) {

			var port = Integer.toString(webServerAppContext.getWebServer().getPort());

			logger.info("Swagger at http://localhost:{}/swagger-ui.html", port);
		}
	}
}
