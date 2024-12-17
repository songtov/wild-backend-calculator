package com.example.demo.application;

import com.example.demo.infrastructure.Calculation;
import com.example.demo.infrastructure.InMemoryCalculationRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

public interface CalculationRepository {
    void add(Calculation calculation);

    List<Calculation> getCalculations();
}
