package com.sb.app.items;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class SbServiceItemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbServiceItemApplication.class, args);
	}

}
