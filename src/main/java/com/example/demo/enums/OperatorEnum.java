package com.example.demo.enums;

import lombok.Getter;

@Getter
public enum OperatorEnum {

    PLUS(0, "+"),
    SUBTRACTION(1, "-"),
    MULTIPLICATION(2, "×"),
    DIVISION(3, "÷"),
    ;

    private int code;

    private String operator;

    OperatorEnum(int code, String operator) {
        this.code = code;
        this.operator = operator;
    }
}
