package com.sb.app.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class SbServiceConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbServiceConfigServerApplication.class, args);
	}

}
