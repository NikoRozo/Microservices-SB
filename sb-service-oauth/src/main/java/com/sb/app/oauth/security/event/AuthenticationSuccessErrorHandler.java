package com.sb.app.oauth.security.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.sb.app.commons.users.models.entity.User;
import com.sb.app.oauth.services.IUserService;

import feign.FeignException;

@Component
public class AuthenticationSuccessErrorHandler implements AuthenticationEventPublisher {

	private Logger log = LoggerFactory.getLogger(AuthenticationSuccessErrorHandler.class);

	@Autowired
	private IUserService userService;

	@Override
	public void publishAuthenticationSuccess(Authentication authentication) {
		UserDetails user = (UserDetails) authentication.getPrincipal();
		System.out.println("Success Login: " + user.getUsername());
		log.info("Success Login: " + user.getUsername());
	}

	@Override
	public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {
		log.error("Error en el Login: " + exception.getMessage());
		System.out.println("Error en el Login: " + exception.getMessage());

		try {
			User user = userService.findByUsername(authentication.getName());
			if (user.getIntentos() == null) {
				user.setIntentos(0);
			}
			user.setIntentos(user.getIntentos() + 1);
			
			if (user.getIntentos() >= 3) {
				log.error(String.format("El User %s des-habilitado por maximo de intentos", authentication.getName()));
				user.setEnabled(false);
			}
			
			userService.update(user, user.getId());
		} catch (FeignException e) {
			log.error(String.format("El User %s no existe en el sistema", authentication.getName()));
		}
	}

}
