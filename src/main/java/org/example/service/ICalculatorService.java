package org.example.service;

import org.example.domain.Calculation;
import org.example.domain.CalculationResult;

public interface ICalculatorService {
    CalculationResult addTwoNumbers(Calculation calculation);

    CalculationResult subTwoNumbers(Calculation calculation);

    CalculationResult divTwoNumbers(Calculation calculation);
}
