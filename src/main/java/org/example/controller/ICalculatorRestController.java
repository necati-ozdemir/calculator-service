package org.example.controller;

import org.example.domain.Calculation;
import org.example.domain.CalculationResult;
import org.example.domain.CalculationString;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface ICalculatorRestController {
    @PostMapping(
            path = "/addition",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    CalculationResult addTwoNumbers(@RequestBody Calculation calculation);

    @PostMapping(
            path = "/subtraction",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    CalculationResult subTwoNumbers(@RequestBody Calculation calculation);

    @PostMapping(
            path = "/division",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    CalculationResult divTwoNumbers(@RequestBody Calculation calculation);

    @PostMapping(
            path = "/multiplication",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    CalculationResult multipleTwoNumbers(@RequestBody CalculationString calculation);
}
