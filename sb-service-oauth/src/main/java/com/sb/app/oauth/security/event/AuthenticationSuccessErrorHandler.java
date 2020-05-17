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

import brave.Tracer;
import feign.FeignException;

@Component
public class AuthenticationSuccessErrorHandler implements AuthenticationEventPublisher {

	private Logger log = LoggerFactory.getLogger(AuthenticationSuccessErrorHandler.class);

	@Autowired
	private IUserService userService;
	
	@Autowired
	private Tracer tracer;

	@Override
	public void publishAuthenticationSuccess(Authentication authentication) {
		UserDetails user = (UserDetails) authentication.getPrincipal();
		
		String mensaje = "Success Login: " + user.getUsername();
		System.out.println(mensaje);
		log.info(mensaje);
		
		User usuario = userService.findByUsername(authentication.getName());
		
		if(usuario.getIntentos() != null && usuario.getIntentos() > 0) {
			usuario.setIntentos(0);
			userService.update(usuario, usuario.getId());
		}
	}

	@Override
	public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {
		String mensaje = "Error en el Login: " + exception.getMessage();
		
		log.error(mensaje);
		System.out.println(mensaje);

		try {
			StringBuilder errors = new StringBuilder();
			errors.append(mensaje);
						
			User user = userService.findByUsername(authentication.getName());
			if (user.getIntentos() == null) {
				user.setIntentos(0);
			}
			
			user.setIntentos(user.getIntentos() + 1);
			
			errors.append(" - Intentos del login: " + user.getIntentos());
			
			if (user.getIntentos() >= 3) {
				String errorMaxIntentos = String.format("El usuario %s des-habilitado por máximos intentos.", user.getUsername());
				log.error(errorMaxIntentos);
				errors.append(" - " + errorMaxIntentos);
								
				user.setEnabled(false);
			}
			
			userService.update(user, user.getId());
			
			tracer.currentSpan().tag("error.mensaje", errors.toString());
			
		} catch (FeignException e) {
			log.error(String.format("El User %s no existe en el sistema", authentication.getName()));
			tracer.currentSpan().tag("error.mensaje", String.format("El User %s no existe en el sistema", authentication.getName()));
		}
	}

}
