package org.example.controller;

import org.example.domain.Calculation;
import org.example.domain.CalculationResult;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

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
}
