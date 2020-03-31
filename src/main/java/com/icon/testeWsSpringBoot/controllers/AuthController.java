package com.icon.testeWsSpringBoot.controllers;

import static org.springframework.http.ResponseEntity.ok;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.icon.testeWsSpringBoot.exception.BadRequestException;
import com.icon.testeWsSpringBoot.model.repository.IUsuarioRepository;
import com.icon.testeWsSpringBoot.model.security.AccountCredentialsVO;
import com.icon.testeWsSpringBoot.model.security.JWTTokenProvider;
import com.icon.testeWsSpringBoot.model.security.Usuario;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JWTTokenProvider jwtTokenProvider;

	@Autowired
	private IUsuarioRepository repo;

	@PostMapping("/signin")
	public ResponseEntity signin(@RequestBody AccountCredentialsVO data) {
		try {

			String username = data.getUsername();
			String pass = data.getPassword();

			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, pass));

			Usuario u = repo.findByNome(username);
			
			String token = "";
			
			if (u!=null) {
				token = jwtTokenProvider.createToken(username, u.getRoles());
			} else {
				throw new BadRequestException("Usuario "+username + " não foi encontrado.");
			}
			
			Map<Object,Object> model = new HashMap<Object, Object>();
			model.put("username", username);
			model.put("token", token);
			
			return ok(model);

		} catch (Exception e) {
			throw new BadRequestException("Usuario ou senha inválidos :: "+e.getMessage());
		}
	}

}
