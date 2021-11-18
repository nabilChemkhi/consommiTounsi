package com.consomiTounsi;

import org.apache.log4j.BasicConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConsomiTounsiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsomiTounsiApplication.class, args);
		BasicConfigurator.configure();
	}

}
