package com.zuulgateway.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableResourceServer
@RestController
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8762"})
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	@RequestMapping("/publica")
	public String publico() {
		return "Pagina Publica";
	}

	@RequestMapping("/privada")
	public String privada() {
		return "Pagina Privada";
	}

	@RequestMapping("/admin")
	public String admin() {
		return "Pagina Administrador";
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/oauth/token", "/oauth/authorize**", "/publica").permitAll();
		
		http.requestMatchers().antMatchers("/privada", "/vehiculo/*").and().authorizeRequests()
				              .antMatchers("/privada", "/vehiculo/*").access("hasRole('USER')").and().requestMatchers()
				              .antMatchers("/admin").and().authorizeRequests().antMatchers("/admin").access("hasRole('ADMIN')");
		
		http.requestMatchers().antMatchers("/privada", "/puesto/*").and().authorizeRequests()
        					  .antMatchers("/privada", "/puesto/*").access("hasRole('USER')").and().requestMatchers()
                              .antMatchers("/admin").and().authorizeRequests().antMatchers("/admin").access("hasRole('ADMIN')");
		
		http.requestMatchers().antMatchers("/privada", "/reserva/*").and().authorizeRequests()
		  					  .antMatchers("/privada", "/reserva/*").access("hasRole('USER')").and().requestMatchers()
		  					  .antMatchers("/admin").and().authorizeRequests().antMatchers("/admin").access("hasRole('ADMIN')");
		
		
		
		
	}

}
