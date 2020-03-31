package com.icon.testeWsSpringBoot.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.icon.testeWsSpringBoot.model.repository.IPermissionRepository;
import com.icon.testeWsSpringBoot.model.repository.IUsuarioRepository;
import com.icon.testeWsSpringBoot.model.security.Permission;
import com.icon.testeWsSpringBoot.model.security.Usuario;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private IUsuarioRepository usuarioRepo;

	@Autowired
	private IPermissionRepository permissionRepo;

	@GetMapping
	public String gett() {
		return "hello usuario";
	}

	@GetMapping("/iniciarPermissions")
	public List<Permission> iniciarPermissions() {

		List<Permission> lp = (List<Permission>) permissionRepo.findAll();
		
		if (lp == null || lp.size() == 0) {
			Permission p1 = new Permission();
			p1.setDescription("ADMIN");

			permissionRepo.save(p1);

			Permission p2 = new Permission();
			p2.setDescription("USER");

			permissionRepo.save(p2);

			lp = (List<Permission>) permissionRepo.findAll();
		}
		
		return lp;
	}

	@PostMapping
	public Usuario inserir(@RequestBody Usuario u) {

		Usuario u2 = new Usuario();
		u2.setUserName(u.getUsername());

		BCryptPasswordEncoder bb = new BCryptPasswordEncoder(16);
		String r = bb.encode(u.getPassword());
		u2.setPassword(r);
		
		u2.setPermissions(new ArrayList<Permission>());
		
		Permission p = permissionRepo.findByDescription("USER");
		u2.getPermissions().add(p);

		usuarioRepo.save(u2);

		return usuarioRepo.findByNome(u2.getUsername());
	}

}
