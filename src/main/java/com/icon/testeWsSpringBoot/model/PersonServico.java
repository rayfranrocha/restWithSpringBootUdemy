package com.icon.testeWsSpringBoot.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icon.testeWsSpringBoot.exception.BadRequestException;
import com.icon.testeWsSpringBoot.model.repository.IPersonRepository;

@Service
public class PersonServico {

	@Autowired
	private IPersonRepository repository;

	public Person findById(Long id) {
		Person p = repository.findById(id).get();
		return p;
	}

	public void delete(Long id) {

		try {
			repository.findById(id).get();
		} catch (Exception e) {
			throw new BadRequestException("Exclusão cancelada. Nao existe nenhum objeto com id = " + id);
		}

		repository.delete(new Person(id));
	}

	public List<Person> findAll() {

		List<Person> ll = (List<Person>) repository.findAll();

		return ll;
	}

	public Person create(Person person) {
		if (person.getId() != null) {
			throw new BadRequestException("Inclusao cancelada. O ID deve ser nulo!");
		}

		repository.save(person);
		return findById(person.getId());
	}

	public Person update(Person person) {

		try {
			repository.findById(person.getId()).get();
		} catch (Exception e) {
			throw new BadRequestException("Atualização cancelada. Nao existe nenhum objeto com id = " + person.getId());
		}

		repository.save(person);
		return findById(person.getId());
	}
	
	@Transactional
	public Person enablePerson(Long id) {
		repository.enablePerson(id);
		return repository.findById(id).get();
	}
	@Transactional
	public Person disablePerson(Long id) {
		repository.disablePerson(id);
		return repository.findById(id).get();
	}

}
