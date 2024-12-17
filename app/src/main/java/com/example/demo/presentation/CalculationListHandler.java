package com.example.demo.presentation;

import com.example.demo.application.Calculator;
import com.example.demo.dto.CalculationListResponseDto;
import com.example.demo.dto.CalculationResponseDto;
import com.example.demo.infrastructure.Calculation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CalculationListHandler extends ResourceMethodHandler {

    private final Calculator calculator;

    private final ObjectMapper objectMapper;
    

    public CalculationListHandler(Calculator calculator, ObjectMapper objectMapper) {
        this.calculator = calculator;
        this.objectMapper = objectMapper;
    }


    @Override
    public String key() {
        return "GET /calculations";
    }

    @Override
    public String handle(String content) throws JsonProcessingException {

        List<Calculation> calculations = calculator.getCalculations();

        System.out.println(calculations);

        return objectMapper.writeValueAsString(
                new CalculationListResponseDto(
                        calculations.stream()
                                .map(calculation -> new CalculationResponseDto(
                                        calculation.getNumber1(),
                                        calculation.getNumber2(),
                                        calculation.getOperator(),
                                        calculation.getResult()
                                ))
                                .toList()
                ));

    }

}
