package com.sb.app.oauth.services;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.sb.app.commons.users.models.entity.User;
import com.sb.app.oauth.client.UserFeignClient;

@Service
public class UserService implements UserDetailsService, IUserService {
	
	private Logger log = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserFeignClient client;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = client.findByUsername(username);
		
		if (user == null) {
			log.error("Error en el login, No existe el usuario '" + username + "' en el sistema.");
			throw new UsernameNotFoundException("Error en el login, No existe el usuario '" + username + "' en el sistema.");
		}
		
		List<GrantedAuthority> authorities = user.getRoles()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getNombre()))
				.peek(autority -> log.info("Role: " + autority.getAuthority()))
				.collect(Collectors.toList());
		
		log.info("Usuario Autenticado: " + username);
		
		return new org.springframework.security.core.userdetails.User(
				user.getUsername(), 
				user.getPassword(), 
				user.getEnabled(), 
				true, true, true, 
				authorities);
	}

	@Override
	public User findByUsername(String username) {
		return client.findByUsername(username);
	}

}
