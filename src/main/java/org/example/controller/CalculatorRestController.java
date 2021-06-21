package org.example.controller;

import org.example.domain.Calculation;
import org.example.domain.CalculationResult;
import org.example.service.ICalculatorService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path = "/calculator")
public class CalculatorRestController implements ICalculatorRestController {

    private final ICalculatorService calculatorService;

    public CalculatorRestController(ICalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @Override
    @PostMapping(
            path = "/addition",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public CalculationResult addTwoNumbers(@RequestBody Calculation calculation) {
        return this.calculatorService.addTwoNumbers(calculation);
    }

    @Override
    @PostMapping(
            path = "/subtraction",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public CalculationResult subTwoNumbers(@RequestBody Calculation calculation) {
        return this.calculatorService.subTwoNumbers(calculation);
    }

    @PostMapping(
            path = "/division",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Override
    public CalculationResult divTwoNumbers(Calculation calculation) {
        return this.calculatorService.divTwoNumbers(calculation);
    }
}
