package com.example.demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NumberTypeEnum {

    NATURAL_NUMBER(0, "自然数"),
    FRACTION(1, "分数"),
    ;

    private Integer code;

    private String message;
}
