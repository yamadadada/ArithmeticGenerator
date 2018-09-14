package com.example.demo.enums;

import lombok.Getter;

@Getter
public enum NumberTypeEnum {

    NATURAL_NUMBER(0, "自然数"),
    FRACTION(1, "分数"),
    ;

    private Integer code;

    private String message;

    NumberTypeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
