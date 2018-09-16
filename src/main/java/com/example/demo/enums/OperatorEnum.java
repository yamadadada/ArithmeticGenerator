package com.example.demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OperatorEnum {

    PLUS(0, "+"),
    SUBTRACTION(1, "-"),
    MULTIPLICATION(2, "×"),
    DIVISION(3, "÷"),
    ;

    private int code;

    private String operator;
}
