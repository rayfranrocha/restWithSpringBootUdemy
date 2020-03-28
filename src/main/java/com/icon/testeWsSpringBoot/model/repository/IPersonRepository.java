package com.icon.testeWsSpringBoot.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.icon.testeWsSpringBoot.model.Person;

@Repository
public interface IPersonRepository extends CrudRepository<Person, Long>{

}
