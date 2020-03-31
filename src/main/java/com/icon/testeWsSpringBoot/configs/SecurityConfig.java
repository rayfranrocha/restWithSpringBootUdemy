package com.icon.testeWsSpringBoot.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.icon.testeWsSpringBoot.model.security.JWTConfigurer;
import com.icon.testeWsSpringBoot.model.security.JWTTokenProvider;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JWTTokenProvider tokenProvider;

	public SecurityConfig(JWTTokenProvider tokenProvider) {
		this.tokenProvider = tokenProvider;
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder r = new BCryptPasswordEncoder();
		return r;

	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().disable().csrf().disable().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
				.antMatchers("/auth/signin", "api-docs/**", "swagger-ui.html**", "/usuario").permitAll()
				.antMatchers("/api/**")
				.authenticated().antMatchers("/users").denyAll().and().apply(new JWTConfigurer(tokenProvider));
	}

}
