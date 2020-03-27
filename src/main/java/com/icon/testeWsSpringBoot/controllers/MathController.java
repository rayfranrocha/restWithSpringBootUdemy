package com.icon.testeWsSpringBoot.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.icon.testeWsSpringBoot.exception.UnsuportedMathOperationException;
import com.icon.testeWsSpringBoot.math.SimpleMath;
import com.icon.testeWsSpringBoot.utils.NumberConverter;

@RestController
public class MathController {

	private SimpleMath simpleMath = new SimpleMath();

	@RequestMapping(value = "/sum/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double sum(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo)
			throws Exception {

		return simpleMath.sum(numberOne, numberTwo);
	}

	@RequestMapping(value = "/subtraction/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double subtraction(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo)
			throws Exception {
		return simpleMath.subtraction(numberOne, numberTwo);
	}

	@RequestMapping(value = "/multiplication/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double multiplication(@PathVariable("numberOne") String numberOne,
			@PathVariable("numberTwo") String numberTwo) throws Exception {

		return simpleMath.multiplication(numberOne, numberTwo);

	}

	@RequestMapping(value = "/division/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double division(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo)
			throws Exception {
		return simpleMath.division(numberOne, numberTwo);
	}

	@RequestMapping(value = "/mean/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double mean(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo)
			throws Exception {
		return simpleMath.mean(numberOne, numberTwo);
	}

	@RequestMapping(value = "/squareRoot/{number}", method = RequestMethod.GET)
	public Double squareRoot(@PathVariable("number") String number) throws Exception {
		return simpleMath.squareRoot(number);
	}

}