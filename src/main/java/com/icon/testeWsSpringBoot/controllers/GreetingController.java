package com.icon.testeWsSpringBoot.controllers;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.icon.testeWsSpringBoot.Greeting;

@RestController
public class GreetingController {

	private static final String template = "Hello, %s!";

	private static final AtomicLong counter = new AtomicLong();

	@RequestMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "nome", defaultValue = "World") String nome) {
		return new Greeting(counter.incrementAndGet(), String.format(template, nome));
	}

}
