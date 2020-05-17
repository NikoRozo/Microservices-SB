package com.sb.app.oauth.services;

import com.sb.app.commons.users.models.entity.User;

public interface IUserService {

	public User findByUsername(String username);
	
	public User update(User user, Long id);
}
