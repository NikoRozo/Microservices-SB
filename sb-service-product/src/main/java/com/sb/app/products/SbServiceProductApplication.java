package com.sb.app.products;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class SbServiceProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbServiceProductApplication.class, args);
	}

}
