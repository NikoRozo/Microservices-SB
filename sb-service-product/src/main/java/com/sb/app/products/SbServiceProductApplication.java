package com.sb.app.products;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@EntityScan({"com.sb.app.commons.models.entity"})
public class SbServiceProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbServiceProductApplication.class, args);
	}

}
