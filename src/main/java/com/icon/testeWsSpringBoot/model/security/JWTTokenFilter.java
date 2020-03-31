package com.icon.testeWsSpringBoot.model.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

public class JWTTokenFilter extends GenericFilterBean {
	
	@Autowired
	private JWTTokenProvider tokenProvider;

	public JWTTokenFilter(JWTTokenProvider tokenProvider2) {
		tokenProvider = tokenProvider2;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String token = tokenProvider.resolveToken((HttpServletRequest) request);
		if (token!=null && tokenProvider.validateToken(token)) {
			Authentication aut = tokenProvider.getAuthentication(token);
			if (aut !=null) {
				SecurityContextHolder.getContext().setAuthentication(aut);
			}
		}
		chain.doFilter(request, response);
	}

}
