package com.icon.testeWsSpringBoot.math;

import org.springframework.stereotype.Service;

import com.icon.testeWsSpringBoot.exception.BadRequestException;
import com.icon.testeWsSpringBoot.utils.NumberConverter;

@Service
public class SimpleMath {

	public Double sum(String numberOne, String numberTwo) {

		if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
			throw new BadRequestException("Please set a numeric value!");
		}

		return NumberConverter.covertToDouble(numberOne) + NumberConverter.covertToDouble(numberTwo);
	}

	public Double subtraction(String numberOne, String numberTwo) {

		if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
			throw new BadRequestException("Please set a numeric value!");
		}

		return NumberConverter.covertToDouble(numberOne) - NumberConverter.covertToDouble(numberTwo);
	}

	public Double multiplication(String numberOne, String numberTwo) {

		if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
			throw new BadRequestException("Please set a numeric value!");
		}

		return NumberConverter.covertToDouble(numberOne) * NumberConverter.covertToDouble(numberTwo);
	}

	public Double division(String numberOne, String numberTwo) {

		if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
			throw new BadRequestException("Please set a numeric value!");
		}

		return NumberConverter.covertToDouble(numberOne) / NumberConverter.covertToDouble(numberTwo);
	}

	public Double mean(String numberOne, String numberTwo) {

		if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
			throw new BadRequestException("Please set a numeric value!");
		}

		return (NumberConverter.covertToDouble(numberOne) + NumberConverter.covertToDouble(numberTwo)) / 2;
	}

	public Double squareRoot(String numberOne) {

		if (!NumberConverter.isNumeric(numberOne)) {
			throw new BadRequestException("Please set a numeric value!");
		}

		return (Double) Math.sqrt(NumberConverter.covertToDouble(numberOne));
	}
}
