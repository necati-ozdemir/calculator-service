package org.example.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "calculator")
public class CalculatorProperties {
    private static final String ADDITION_SERVICE_DEFAULT_URL = "http://localhost:8081";
    private static final String SUBTRACTION_SERVICE_DEFAULT_URL = "http://localhost:8082";

    private String additionServiceUrl = ADDITION_SERVICE_DEFAULT_URL;
    private String subtractionServiceUrl = SUBTRACTION_SERVICE_DEFAULT_URL;

    public CalculatorProperties() {
    }

    public String getAdditionServiceUrl() {
        return additionServiceUrl;
    }

    public void setAdditionServiceUrl(String additionServiceUrl) {
        this.additionServiceUrl = additionServiceUrl;
    }

    public String getSubtractionServiceUrl() {
        return subtractionServiceUrl;
    }

    public void setSubtractionServiceUrl(String subtractionServiceUrl) {
        this.subtractionServiceUrl = subtractionServiceUrl;
    }
}
