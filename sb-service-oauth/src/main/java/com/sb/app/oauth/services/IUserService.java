package com.sb.app.oauth.services;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.sb.app.commons.users.models.entity.User;

public interface IUserService {

	public User findByUsername(String username);
	
	public User update(User user, Long id);
}
