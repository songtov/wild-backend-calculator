package com.example.demo;

import com.example.demo.application.Calculator;
import com.example.demo.infrastructure.Calculation;
import com.example.demo.infrastructure.FakeCalculationRepository;
import com.example.demo.infrastructure.InMemoryCalculationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CalculatorTest {
    FakeCalculationRepository calculationRepository;
    Calculator calculator;


    @BeforeEach
    void setUp() {
        calculationRepository = new FakeCalculationRepository();
        calculator = new Calculator(calculationRepository);
    }


    @Test
    void plus() {
        Calculation calculation = calculator.calculate(1, 1, "+");
        assertThat(calculation.getResult()).isEqualTo(2);
        assertThat(calculationRepository.isAdded()).isTrue();
    }


    @Test
    void minus() {
        Calculation calculation = calculator.calculate(9, 2, "-");
        assertThat(calculation.getResult()).isEqualTo(7);
    }


    @Test
    void multiply() {
        Calculation calculation = calculator.calculate(9, 2, "*");
        assertThat(calculation.getResult()).isEqualTo(18);
    }

    @Test
    void divide() {
        Calculation calculation = calculator.calculate(9, 3, "/");
        assertThat(calculation.getNumber1()).isEqualTo(9);
        assertThat(calculation.getResult()).isEqualTo(3);
    }

    @Test
    void divideByZero() {
        assertThatThrownBy(() -> {
            calculator.calculate(9, 0, "/");
        });

        assertThat(calculationRepository.isAdded()).isFalse();
    }
}
