package com.tilgungsplan.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {

		//SpringApplication.DEFAULT_REACTIVE_WEB_CONTEXT_CLASS
		SpringApplication.run(DemoApplication.class, args);

	}


}
