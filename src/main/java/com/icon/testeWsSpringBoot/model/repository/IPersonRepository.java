package com.icon.testeWsSpringBoot.model.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.icon.testeWsSpringBoot.model.Person;

@Repository
public interface IPersonRepository extends JpaRepository<Person, Long> {

	@Modifying
	@Query("update Person p set p.enabled = true where p.id = :id")
	void enablePerson(@Param("id") Long id);

	@Modifying
	@Query("update Person p set p.enabled = false where p.id = :id")
	void disablePerson(@Param("id") Long id);
	
	@Query("select p from Person p where p.firstName like lower(concat('%',:firstName,'%'))")
	Page<Person> findPersonByName(@Param("firstName") String firstName, Pageable pageable);

}
