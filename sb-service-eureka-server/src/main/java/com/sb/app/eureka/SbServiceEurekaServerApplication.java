package com.sb.app.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class SbServiceEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbServiceEurekaServerApplication.class, args);
	}

}
