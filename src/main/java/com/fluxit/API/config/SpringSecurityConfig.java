package com.fluxit.API.config;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.fluxit.API.security.JWTAuthorizationFilter;

/**
 * @author Martin Fernandez
 *
 */
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	// Create 2 users for demo
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.inMemoryAuthentication().withUser("username").password("{noop}password").roles("USER").and().withUser("admin")
				.password("{noop}password").roles("USER", "ADMIN");

	}

	// Secure the endpoins with HTTP Basic authentication
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		
				/* / HTTP Basic authentication
				.httpBasic().and().authorizeRequests()
				.antMatchers(HttpMethod.GET, "/books/**").hasRole("USER")
				.antMatchers(HttpMethod.POST, "/books").hasRole("ADMIN")
				.antMatchers(HttpMethod.PUT, "/books/**").hasRole("ADMIN")
				.antMatchers(HttpMethod.PATCH, "/books/**").hasRole("ADMIN")
				.antMatchers(HttpMethod.DELETE, "/books/**").hasRole("ADMIN")
				.and().csrf().disable().formLogin()
				.disable();*/
	
			http.csrf().disable()
				.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
				.authorizeRequests()
				.antMatchers(HttpMethod.POST, "/user").permitAll()
				.anyRequest().authenticated();

	}
}

/*
 * @Bean public UserDetailsService userDetailsService() { //ok for demo
 * User.UserBuilder users = User.withDefaultPasswordEncoder();
 * InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
 * manager.createUser(users.username("user").password("password").roles("USER").
 * build());
 * manager.createUser(users.username("admin").password("password").roles("USER",
 * "ADMIN").build()); return manager; }
 */