package com.icon.testeWsSpringBoot.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.icon.testeWsSpringBoot.model.security.Permission;

@Repository
public interface IPermissionRepository extends CrudRepository<Permission, Long> {

	@Query("SELECT p from Permission p where p.description = :description")
	Permission findByDescription(@Param("description") String description);

}
