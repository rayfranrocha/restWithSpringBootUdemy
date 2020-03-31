package com.icon.testeWsSpringBoot.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.icon.testeWsSpringBoot.model.repository.IUsuarioRepository;

@Service
public class UsuarioServico implements UserDetailsService {

	@Autowired
	private IUsuarioRepository repo;

	public UsuarioServico(IUsuarioRepository repo) {
		super();
		this.repo = repo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserDetails u = repo.findByNome(username);

		if (u != null) {
			return u;
		} else {
			throw new UsernameNotFoundException("Não foi encontrado usuário com nome = '" + username + "'");
		}
	}
}
