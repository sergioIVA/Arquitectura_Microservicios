package com.zuulgateway.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin("*")
public class WebController {

	@Autowired 
	ConsumerTokenServices tokenServices;
	
	@RequestMapping({ "/", "index" })
	public String inicio() {
		return "index";
	}

	@RequestMapping("/webprivado")
	public String privado() {
		return "privado";
	}

	@RequestMapping("/webpublico")
	public String loginpub() {
		return "publico";
	}

	@RequestMapping("/webadmin")
	public String admin() {
		return "admin";
	}

	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	@DeleteMapping("/revoke-token")
	@ResponseBody
	public void revocarToken(HttpServletRequest request) {

		String authorization = request.getHeader("Authorization");
		if (authorization != null && authorization.contains("Bearer")) {
			String tokenId = authorization.substring("Bearer".length() + 1);
			tokenServices.revokeToken(tokenId);
		}

	}
}
