package com.sb.app.oauth.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sb.app.commons.users.models.entity.User;

@FeignClient("service.users")
public interface UserFeignClient {

	@GetMapping("/users/search/findUsername")
	public User findByUsername(@RequestParam String username);
}
