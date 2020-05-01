package com.oneup.tvseries;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
public class TvSeriesApplication {

	public static void main(String[] args) {
		SpringApplication.run(TvSeriesApplication.class, args);
	}

}
