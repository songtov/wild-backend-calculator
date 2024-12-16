package com.example.demo.presentation;

import com.example.demo.application.Calculator;
import com.example.demo.dto.CalculationRequestDto;
import com.example.demo.dto.CalculationResponseDto;
import com.example.demo.infrastructure.Calculation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class CalculationCreateHandler extends ResourceMethodHandler {
    private final Calculator calculator = new Calculator();

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String handle(String content) throws JsonProcessingException {

        CalculationRequestDto requestDto =
                objectMapper.readValue(content, CalculationRequestDto.class);

        Calculation calculation = calculator.calculate(
                requestDto.getNumber1(),
                requestDto.getNumber2(),
                requestDto.getOperator());

        return objectMapper.writeValueAsString(
                new CalculationResponseDto(
                        calculation.getNumber1(),
                        calculation.getNumber2(),
                        calculation.getOperator(),
                        calculation.getResult()
                ));

    }

    @Override
    public String key() {
        return "POST /calculations";
    }

}
