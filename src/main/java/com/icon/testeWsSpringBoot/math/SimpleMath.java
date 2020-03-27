package com.icon.testeWsSpringBoot.math;

import com.icon.testeWsSpringBoot.exception.UnsuportedMathOperationException;
import com.icon.testeWsSpringBoot.utils.NumberConverter;

public class SimpleMath {

	public Double sum(String numberOne, String numberTwo) {

		if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
			throw new UnsuportedMathOperationException("Please set a numeric value!");
		}

		return NumberConverter.covertToDouble(numberOne) + NumberConverter.covertToDouble(numberTwo);
	}

	public Double subtraction(String numberOne, String numberTwo) {

		if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
			throw new UnsuportedMathOperationException("Please set a numeric value!");
		}

		return NumberConverter.covertToDouble(numberOne) - NumberConverter.covertToDouble(numberTwo);
	}

	public Double multiplication(String numberOne, String numberTwo) {

		if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
			throw new UnsuportedMathOperationException("Please set a numeric value!");
		}

		return NumberConverter.covertToDouble(numberOne) * NumberConverter.covertToDouble(numberTwo);
	}

	public Double division(String numberOne, String numberTwo) {

		if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
			throw new UnsuportedMathOperationException("Please set a numeric value!");
		}

		return NumberConverter.covertToDouble(numberOne) / NumberConverter.covertToDouble(numberTwo);
	}

	public Double mean(String numberOne, String numberTwo) {

		if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
			throw new UnsuportedMathOperationException("Please set a numeric value!");
		}

		return (NumberConverter.covertToDouble(numberOne) + NumberConverter.covertToDouble(numberTwo)) / 2;
	}

	public Double squareRoot(String numberOne) {

		if (!NumberConverter.isNumeric(numberOne)) {
			throw new UnsuportedMathOperationException("Please set a numeric value!");
		}

		return (Double) Math.sqrt(NumberConverter.covertToDouble(numberOne));
	}
}
