package com.oneup.tvseries;

import com.oneup.external.tvdb.configuration.TvDbClientConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Import;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@Import(value = TvDbClientConfiguration.class)
public class TvSeriesApplication {

	public static void main(String[] args) {
		SpringApplication.run(TvSeriesApplication.class, args);
	}

}
