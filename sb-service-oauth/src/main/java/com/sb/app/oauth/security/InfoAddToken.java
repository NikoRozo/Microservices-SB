package com.sb.app.oauth.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.sb.app.commons.users.models.entity.User;
import com.sb.app.oauth.services.IUserService;

@Component
public class InfoAddToken implements TokenEnhancer{

	@Autowired
	private IUserService userService;
	
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		Map<String, Object> info = new HashMap<String, Object>();
		
		User user = userService.findByUsername(authentication.getName());
		
		info.put("nombre", user.getNombre());
		info.put("apellido", user.getApellido());
		info.put("email", user.getEmail());
		
		((DefaultOAuth2AccessToken)  accessToken).setAdditionalInformation(info);
		
		return accessToken;
	}

}
