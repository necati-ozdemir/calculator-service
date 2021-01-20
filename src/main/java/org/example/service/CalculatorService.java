package org.example.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.config.CalculatorProperties;
import org.example.domain.Calculation;
import org.example.domain.CalculationResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class CalculatorService implements ICalculatorService {
    private static final Logger logger = LoggerFactory.getLogger(CalculatorService.class);

    private final RestTemplate restTemplate;
    private final CalculatorProperties calculatorProperties;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public CalculatorService(CalculatorProperties calculatorProperties) {
        this.restTemplate = new RestTemplate();

        this.calculatorProperties = calculatorProperties;
    }

    @Override
    public CalculationResult addTwoNumbers(Calculation calculation) {
        return this.doRequestForResult(
                this.calculatorProperties.getAdditionServiceUrl(),
                calculation
        );
    }

    @Override
    public CalculationResult subTwoNumbers(Calculation calculation) {
        return this.doRequestForResult(
                this.calculatorProperties.getSubtractionServiceUrl(),
                calculation
        );
    }

    private CalculationResult doRequestForResult(String url, Calculation calculation) {
        return Optional.of(calculation)
                .map(this::prepareQuery)
                .map(query -> this.doRequest(url, query))
                .filter(responseEntity -> responseEntity.getStatusCodeValue() == 200)
                .map(ResponseEntity::getBody)
                .map(this::toCalculationResultObject)
                .orElseGet(() -> new CalculationResult("FAIL", 0));
    }

    private ResponseEntity<String> doRequest(String url, String query) {
        try {
            return this.restTemplate.getForEntity(
                    url + "?" + query,
                    String.class
            );
        } catch (Exception e) {
            logger.error("Error in get request for Url: " + url + " Message: " + e.getMessage());
            return null;
        }
    }

    private String prepareQuery(Calculation calculation) {
        return "one=" + calculation.getNumberOne() + "&two=" + calculation.getNumberTwo();
    }

    private CalculationResult toCalculationResultObject(String body) {
        try {
            return this.objectMapper.readValue(body, CalculationResult.class);
        } catch (JsonProcessingException e) {
            logger.error("Error in reading value with object mapper. Error Message: " + e.getMessage());
            return new CalculationResult("FAIL", 0);
        }
    }

    private HttpEntity<String> createHttpEntity(Calculation calculation) {
        try {
            return new HttpEntity<>(
                    this.objectMapper.writeValueAsString(calculation),
                    this.createHttpHeaders()
            );
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new HttpEntity<>(
                    calculation.toString(),
                    this.createHttpHeaders()
            );
        }
    }

    private HttpHeaders createHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
