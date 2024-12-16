package com.example.demo.presentation;

import org.springframework.stereotype.Component;

@Component
public class HomeGetHandler extends ResourceMethodHandler {


    @Override
    public String handle(String content) {
        return "Hello, World!";
    }

    @Override
    public String key() {
        return "GET /";
    }
}
