package com.ssafy.webterview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"com.ssafy.webterview.entity"} )
@EnableJpaRepositories(basePackages = {"com.ssafy.webterview.repository"})
public class WebterviewBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebterviewBeApplication.class, args);
	}

}
