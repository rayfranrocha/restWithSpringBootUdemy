package com.icon.testeWsSpringBoot.model.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.icon.testeWsSpringBoot.model.Person;

@Repository
public interface IPersonRepository extends CrudRepository<Person, Long> {

	@Modifying
	@Query("update Person p set p.enabled = true where p.id = :id")
	void enablePerson(@Param("id") Long id);

	@Modifying
	@Query("update Person p set p.enabled = false where p.id = :id")
	void disablePerson(@Param("id") Long id);

}
