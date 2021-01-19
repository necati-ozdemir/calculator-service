package org.example.domain;

public class CalculationResult {
    String resultStatus;
    Integer resultValue;

    public CalculationResult(String resultStatus, Integer resultValue) {
        this.resultStatus = resultStatus;
        this.resultValue = resultValue;
    }

    public CalculationResult() {
    }

    public String getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(String resultStatus) {
        this.resultStatus = resultStatus;
    }

    public Integer getResultValue() {
        return resultValue;
    }

    public void setResultValue(Integer resultValue) {
        this.resultValue = resultValue;
    }
}
