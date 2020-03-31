package com.icon.testeWsSpringBoot.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.icon.testeWsSpringBoot.model.security.Usuario;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {

	@Query("select u from Usuario u where u.userName = :nome")
	Usuario findByNome(@Param("nome") String nome);

}
