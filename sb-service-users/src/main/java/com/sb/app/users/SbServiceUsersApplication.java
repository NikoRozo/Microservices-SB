package com.sb.app.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan({"com.sb.app.commons.users.models.entity"})
@SpringBootApplication
public class SbServiceUsersApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbServiceUsersApplication.class, args);
	}

}
