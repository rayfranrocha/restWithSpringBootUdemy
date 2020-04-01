package com.icon.testeWsSpringBoot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.icon.testeWsSpringBoot.model.Person;
import com.icon.testeWsSpringBoot.model.PersonServico;

@RestController
@RequestMapping("/api/person")
public class PersonController {

	@Autowired
	private PersonServico personServico;

	@GetMapping("/{id}")
	public Person get(@PathVariable("id") Long id) throws Exception {
		return personServico.findById(id);
	}

	@GetMapping()
	public List<Person> getall() throws Exception {
		return personServico.findAll();
	}

	@PostMapping()
	public Person create(@RequestBody Person person) throws Exception {
		return personServico.create(person);
	}

	@PutMapping()
	public Person update(@RequestBody Person person) throws Exception {
		return personServico.update(person);
	}

	@PatchMapping("/enable/{id}")
	public Person enabledDisablePerson(@PathVariable("id") Long id) {
		Person p = personServico.enablePerson(id);
		return p;
	}

	@PatchMapping("/disable/{id}")
	public Person disableDisablePerson(@PathVariable("id") Long id) {
		Person p = personServico.disablePerson(id);
		return p;
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) throws Exception {
		personServico.delete(id);
		return ResponseEntity.ok().build();

	}

}
